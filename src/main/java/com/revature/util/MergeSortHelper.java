package com.revature.util;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Animation;

public class MergeSortHelper {
	private static List<Animation> animations = null;
	private static Animation current = null;
	
	public static void resetAnimations() {
		animations = new ArrayList<Animation>();
	}
	
	//init method
	public static void mergeSort(int[] arr) {
		mergeSort(arr, new int[arr.length], 0, arr.length-1);
	}
	
	//recursive method
	private static void mergeSort(int[] arr, int[] temp, int leftStart, int rightEnd) {
		//check if done
		if(leftStart >= rightEnd)return;
		int middle = (leftStart + rightEnd)/2;
		mergeSort(arr, temp, leftStart, middle);
		mergeSort(arr, temp, middle+1, rightEnd);
		mergeHalves(arr, temp, leftStart, rightEnd);
	}
	
	private static void mergeHalves(int[] arr, int[] temp, int leftStart, int rightEnd) {
		int leftEnd = (rightEnd + leftStart)/2;
		int rightStart = leftEnd + 1;
		int arrSize = rightEnd - leftStart + 1;
		
		int left = leftStart;
		int right = rightStart;
		int index = leftStart;
		
		//while still in bounds copy over the smaller element
		while(left <= leftEnd && right <= rightEnd) {
			current = new Animation();
			current.setState("pointer");
			current.setValues(new int[] {index});
			animations.add(current);
			if(arr[left] <= arr[right]) {
				temp[index] = arr[left];
				current = new Animation();
				current.setState("swap");
				current.setValues(new int[] {left, right});
				animations.add(current);
				left++;
			} else {
				temp[index] = arr[right];
				current = new Animation();
				current.setState("swap");
				current.setValues(new int[] {left, right});
				animations.add(current);
				right++;
			}
			index++;
		}
		
		//only one of the sides will have remaining elements due to while loop condition
		//copy left and right halves into temp array
		System.arraycopy(arr, left, temp, index, leftEnd - left + 1);
		current = new Animation();
		current.setState("mergeHalf");
		current.setValues(new int[] {left, index, leftEnd - left + 1});
		animations.add(current);
		current = new Animation();
		current.setState("consolidate");
		current.setValues(temp);
		animations.add(current);
		System.arraycopy(arr, right, temp, index, rightEnd - right + 1);
		current = new Animation();
		current.setState("mergeHalf");
		current.setValues(new int[] {right, index, rightEnd - right + 1});
		animations.add(current);
		current = new Animation();
		current.setState("consolidate");
		current.setValues(temp);
		animations.add(current);
		//copy everything back into original array
		System.arraycopy(temp, leftStart, arr, leftStart, arrSize);
		current = new Animation();
		current.setState("mergeFull");
		current.setValues(arr);
		animations.add(current);
	}

	public static List<Animation> getMergeSortAnimations() {
		return animations;
	}

}
