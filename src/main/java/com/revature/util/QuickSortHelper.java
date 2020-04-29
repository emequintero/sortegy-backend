package com.revature.util;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Animation;

public class QuickSortHelper {
	private static List<Animation> animations = null;
	private static Animation current = null;
	
	public static void quickSort(int[] arr, int start, int end) {
		//no more left to sort
		if(start >= end)return;
		else {
			//sort array based on pivot value and return pivot index
			int index = partition(arr, start, end);
			current = new Animation();
			current.setState("pivot");
			current.setValues(new int[] {index});
			animations.add(current);
			//sort half before pivot
			quickSort(arr, start, index-1);
			//sort half after pivot
			quickSort(arr, index+1, end);
		}
	}
	
	private static int partition(int[] arr, int start, int end) {
		//start sorting from start point
		int pivotIndex = start;
		//set pivot to last value in array
		int pivotValue = arr[end];
		for(int i = start; i < end; i++) {
			/***
			 * if current element is less than pivot value
			 * swap index of pivot and the current element 
			 * smaller than pivot value are to the left
			 * larger than pivot value are to the right
			 * */
			if(arr[i] < pivotValue) {
				swap(arr, i, pivotIndex);
				pivotIndex++;
			}
		}
		/***
		 * swap index of pivot and end
		 * the placement for the pivot value (used to compare during loop) 
		 * is the resulting pivot index.
		 * */
		swap(arr, pivotIndex, end);
		return pivotIndex;
	}
	
	private static void swap(int[] arr, int one, int two) {
		current = new Animation();
		current.setState("swap");
		current.setValues(new int[] {one,two});
		animations.add(current);
		int temp = arr[one];
		arr[one] = arr[two];
		arr[two] = temp;
	}
	
	public static List<Animation> getQuickSortAnimations() {
		return animations;
	}
	
	public static void resetAnimations() {
		animations = new ArrayList<Animation>();
	}
}
