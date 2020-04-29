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

	// recursive method
	public static void mergeSort(int arr[], int leftStart, int rightEnd) {
		//check if done
		if(leftStart < rightEnd) {
			//calculate middle of array
			int middle = (leftStart + rightEnd)/2;
			
			//sort left array
			mergeSort(arr, leftStart, middle);
			//sort right array
			mergeSort(arr, middle+1, rightEnd);
			//merge two arrays
			merge(arr, leftStart, middle, rightEnd);
		}
	}

	// merge left and right arrays
	private static void merge(int arr[], int leftStart, int middle, int rightEnd) {
		//calculate left array size
		int lSize = middle - leftStart + 1;
		//calculate right array size
		int rSize = rightEnd - middle;
		
		//left array
		int[] arrL = new int[lSize];
		//right array
		int[] arrR = new int[rSize];
		
		//copy into left array
		for(int i = 0; i < lSize; i++)arrL[i]=arr[leftStart + i];
		//copy into right array
		for(int i = 0; i < rSize; i++)arrR[i] = arr[middle + 1 + i];
		
		/**
		 * MERGE SORTED LEFT & RIGHT INTO ORIGINAL
		 * */
		
		//pointers for left and right
		int lPtr = 0, rPtr = 0;
		//pointer for original array
		int index = leftStart;
		
		//one of the arrays will have unprocessed data due to this condition
		while(lPtr < lSize && rPtr < rSize) {
			current = new Animation();
			current.setState("pivot");
			current.setValues(new int[] {index});
			animations.add(current);
			if(arrL[lPtr] <= arrR[rPtr]) {
				current = new Animation();
				current.setState("overwrite");
				current.setValues(new int[] {index, arrL[lPtr]});
				animations.add(current);
				arr[index] = arrL[lPtr++];
			}
			else {
				current = new Animation();
				current.setState("overwrite");
				current.setValues(new int[] {index, arrR[rPtr]});
				animations.add(current);
				arr[index] = arrR[rPtr++];
			}
			index++;
		}
		
		/**
		 * ADD LEFTOVER DATA
		 * */
		
		while(lPtr < lSize) {
			current = new Animation();
			current.setState("overwrite");
			current.setValues(new int[] {index, arrL[lPtr]});
			animations.add(current);
			arr[index++] = arrL[lPtr++];
		}
		
		while(rPtr < rSize) {
			current = new Animation();
			current.setState("overwrite");
			current.setValues(new int[] {index, arrR[rPtr]});
			animations.add(current);
			arr[index++] = arrR[rPtr++];
		}
	}

	public static List<Animation> getMergeSortAnimations() {
		return animations;
	}

}
