package com.revature.util;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Animation;

public class InsertionSortHelper {
	private static List<Animation> animations = null;
	private static Animation current = null;

	public static void resetAnimations() {
		animations = new ArrayList<Animation>();
	}

	public static void insertionSort(int[] arr) {
		for(int i = 1; i < arr.length; i++) {
			//check if element is less than element to its left
			if(arr[i] <= arr[i-1]) {
				//value to compare to (element that is out of order)
				int key = arr[i];
				current = new Animation();
				current.setState("pivot");
				current.setValues(new int[] {i});
				animations.add(current);
				//start placement pointer at element that is greater than key
				int placementPtr = i-1;
				//swap elements while element at placement pointer is greater than key
				while(placementPtr>=0 && arr[placementPtr]>=key) {
					current = new Animation();
					current.setState("swap");
					current.setValues(new int[] {placementPtr, placementPtr+1});
					animations.add(current);
					//swap with element which was originally at "i"
					swap(arr, placementPtr, placementPtr+1);
					placementPtr--;
				}
				/**
				 * place the key in its correct placement
				 * +1 because the while loop will leave the placementPtr one behind
				 * where it should be (-- each iteration).
				 * */
				arr[placementPtr+1] = key;
			}
		}
		
	}
	
	public static void swap(int[] arr, int one, int two) {
		int temp = arr[one];
		arr[one] = arr[two];
		arr[two] = temp;
	}

	public static List<Animation> getInsertionSortAnimations() {
		return animations;
	}

}
