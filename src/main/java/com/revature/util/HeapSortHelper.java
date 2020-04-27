package com.revature.util;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Animation;
import com.revature.models.Heap;

public class HeapSortHelper {
	private static List<Animation> animations = null;
	private static Animation current = null;
	private static Heap maxHeap = null;

	public static void resetAnimations() {
		animations = new ArrayList<Animation>();
	}

	public static void heapSort(int[] arr) {
		maxHeap = new Heap(arr.length);
		//add all elements from array to heap
		//heap is design to restore max heap every time an element is added
		for (int i = 0; i < arr.length; i++) {
			maxHeap.add(arr[i]);
		}
		//keep track if array is sorted
		boolean isSorted = false;
		//pointer for new root placement
		int newPlacementPtr = arr.length-1;
		//sort the array by extracting the root (max value)
		while(!isSorted) {
			isSorted = false;
			//extract max element
			int newRoot = maxHeap.poll();
			current = new Animation();
			current.setState("root");
			current.setValues(new int[] {newRoot});
			animations.add(current);
			//check if we're at the last element in array
			if(newPlacementPtr == 0) {
				//array is now sorted
				isSorted = true;
				//put the value of last element into the beginning of the array
				arr[newPlacementPtr] = newRoot;
			}
			else {
				//set the value of the new root (max value) in the appropriate place
				arr[newPlacementPtr] = newRoot;
			}
			current = new Animation();
			current.setState("overwrite");
			current.setValues(new int[] {newPlacementPtr, newRoot});
			animations.add(current);
			//continue moving left
			newPlacementPtr--;
		}
		
	}

	public static List<Animation> getHeapSortAnimations() {
		return animations;
	}

}
