package com.revature.util;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Animation;

public class BubbleSortHelper {
	private static List<Animation> animations = null;
	private static Animation current = null;
	
	public static void resetAnimations() {
		animations = new ArrayList<Animation>();
	}

	public static void bubbleSort(int[] arr) {
		boolean isSorted = false;
		int lastSorted = arr.length-1;
		while(!isSorted) {
			isSorted = true;
			for(int i = 0; i < lastSorted; i++) {
				current = new Animation();
				current.setState("pivot");
				current.setValues(new int[] {i});
				animations.add(current);
				if(arr[i] > arr[i+1]) {
					swap(arr,i,i+1);
					isSorted = false;
				}
			}
			lastSorted--;
		}
	}

	public static List<Animation> getBubbleSortAnimations() {
		return animations;
	}
	
	private static void swap(int[] arr, int one, int two) {
		current = new Animation();
		current.setState("swap");
		current.setValues(new int[] {one, two});
		animations.add(current);
		int temp = arr[one];
		arr[one] = arr[two];
		arr[two] = temp;
	}
	
}
