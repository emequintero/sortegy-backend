package com.revature.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Animation;
import com.revature.util.BubbleSortHelper;
import com.revature.util.MergeSortHelper;
import com.revature.util.QuickSortHelper;

@RestController
@CrossOrigin
@RequestMapping(value="/sort",produces="application/json")
public class SortingController {
	ObjectMapper mapper = new ObjectMapper();
	
	@PostMapping("/quick")
	public ResponseEntity<String> quickSort(@RequestBody int[]arr) throws JsonProcessingException{
		//reset array list for animations in helper class
		QuickSortHelper.resetAnimations();
		//sort array
		QuickSortHelper.quickSort(arr, 0, arr.length-1);
		//get animation list from helper class
		List<Animation> animations = QuickSortHelper.getQuickSortAnimations();
		return ResponseEntity.ok().body(
				"{\"sortedArr\" : " + mapper.writeValueAsString(arr) + "," +
				"\"animations\" : " + mapper.writeValueAsString(animations) + "}"
		);
	}
	
	@PostMapping("/bubble")
	public ResponseEntity<String> bubbleSort(@RequestBody int[]arr) throws JsonProcessingException{
		//reset array list for animations in helper class
		BubbleSortHelper.resetAnimations();
		//sort array
		BubbleSortHelper.bubbleSort(arr);
		//get animation list from helper class
		List<Animation> animations = BubbleSortHelper.getBubbleSortAnimations();
		return ResponseEntity.ok().body(
				"{\"sortedArr\" : " + mapper.writeValueAsString(arr) + "," +
				"\"animations\" : " + mapper.writeValueAsString(animations) + "}"
		);
	}
	
	@PostMapping("/merge")
	public ResponseEntity<String> mergeSort(@RequestBody int[]arr) throws JsonProcessingException{
		//reset array list for animations in helper class
		MergeSortHelper.resetAnimations();
		//sort array
		MergeSortHelper.mergeSort(arr, 0, arr.length-1);
		//get animation list from helper class
		List<Animation> animations = MergeSortHelper.getMergeSortAnimations();
		return ResponseEntity.ok().body(
				"{\"sortedArr\" : " + mapper.writeValueAsString(arr) + "," +
				"\"animations\" : " + mapper.writeValueAsString(animations) + "}"
		);
	}
}
