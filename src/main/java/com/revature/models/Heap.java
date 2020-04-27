package com.revature.models;
import java.util.Arrays;

public class Heap {
	private int capacity;
	private int size = 0;
	int[] arr;
	
	public Heap(int capacity) {
		this.capacity = capacity;
		arr = new int[this.capacity];
	}
	
	/**
	 * GET INDEX OF CHILDREN/PARENT
	 * */
	private int getLeftChildIndex(int parentIndex) {
		return 2 * parentIndex + 1;
	}

	private int getRightChildIndex(int parentIndex) {
		return 2 * parentIndex + 2;
	}

	private int getParentIndex(int childIndex) {
		return (childIndex - 1) / 2;
	}
	
	/**
	 * CHECK IF NODE HAS CHILDREN/PARENT
	 * */
	private boolean hasLeftChild(int index) {
		return getLeftChildIndex(index) < size;
	}

	private boolean hasRightChild(int index) {
		return getRightChildIndex(index) < size;
	}

	private boolean hasParent(int index) {
		return getParentIndex(index) >= 0;
	}
	
	/**
	 * GET NODE'S CHILDREN/PARENT VALUE
	 * */
	private int leftChild(int index) {
		return arr[getLeftChildIndex(index)];
	}

	private int rightChild(int index) {
		return arr[getRightChildIndex(index)];
	}

	private int parent(int index) {
		return arr[getParentIndex(index)];
	}
	
	//swap two values
	private void swap(int one, int two) {
		int temp = arr[one];
		arr[one] = arr[two];
		arr[two] = temp;
	}

	// checks if array is full and creates new array of twice its size if true
	private void ensureExtraCapacity() {
		if (size == capacity) {
			arr = Arrays.copyOf(arr, capacity * 2);
			capacity *= 2;
		}
	}
	
	//check root element (max element)
	public int peek() {
		if (size == 0)
			throw new IllegalStateException();
		return arr[0];
	}
	
	//remove root element (max) and restore heap (bubble down if out of order)
	public int poll() {
		if (size == 0)
			throw new IllegalStateException();
		int curRootValue = arr[0];
		arr[0] = arr[size - 1];
		size--;
		heapifyDown();
		return curRootValue;
	}
	
	//add element at the end and restore heap (bubble up if out of order)
	public void add(int item) {
		ensureExtraCapacity();
		arr[size] = item;
		size++;
		heapifyUp();
	}

	public void heapifyUp() {
		// start at last element added
		int index = size - 1;
		/**
		 * check if element has parent && check if parent is less than element at
		 * current index
		 */
		while (hasParent(index) && parent(index) < arr[index]) {
			// swap parent with element at current index (bubble up)
			swap(getParentIndex(index), index);
			// set index to new placement of element (its previous parent node)
			index = getParentIndex(index);
		}
	}

	public void heapifyDown() {
		//start at root element
		int index = 0;
		//check if element at index has children (no left child means no right child)
		while(hasLeftChild(index)) {
			//assume left child is larger
			int largerChildIndex = getLeftChildIndex(index);
			//check if has right child and check if right child is larger
			if(hasRightChild(index) && rightChild(index) > leftChild(index)) {
				//if right child is larger change the larger child index
				largerChildIndex = getRightChildIndex(index);
			}
			/**
			 * check if heap is in order
			 * this is a max heap so the current element should be larger
			 * than the larger child
			 * */
			if(arr[index] > arr[largerChildIndex]) {
				break;
			}
			else {
				//swap current element with its smaller child (bubble down)
				swap(index, largerChildIndex);
				//set new index to where the smaller child was
				index = largerChildIndex;
			}
		}
	}

}
