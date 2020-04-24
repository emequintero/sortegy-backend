package com.revature.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.revature.models.Animation;

public class QuickSortHelper {
	private static List<Animation> animations = new ArrayList<Animation>();
	
	public static void quickSort(int[] arr, int start, int end) {
		//no more left to sort
		if(start >= end)return;
		else {
			//sort array based on pivot value and return pivot index
			int index = partition(arr, start, end);
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
		Animation current = new Animation();
		for(int i = start; i < end; i++) {
			/***
			 * if current element is less than pivot value
			 * swap index of pivot and the current element 
			 * smaller than pivot value are to the left
			 * larger than pivot value are to the right
			 * */
			if(arr[i] < pivotValue) {
				current.setState("swap");
				current.setValues(Arrays.asList(new Integer[] {i,pivotIndex}));
				animations.add(current);
				swap(arr, i, pivotIndex);
				pivotIndex++;
			} else {
				current.setState("none");
				current.setValues(Arrays.asList(new Integer[] {i}));
				animations.add(current);
			}
		}
		/***
		 * swap index of pivot and end
		 * the placement for the pivot value (used to compare during loop) 
		 * is the resulting pivot index.
		 * */
		current.setState("swap");
		current.setValues(Arrays.asList(new Integer[] {pivotIndex, end}));
		animations.add(current);
		swap(arr, pivotIndex, end);
		return pivotIndex;
	}
	
	private static void swap(int[] arr, int one, int two) {
		int temp = arr[one];
		arr[one] = arr[two];
		arr[two] = temp;
	}
	
	public static List<Animation> getQuickSortAnimations() {
		return animations;
	}
}
