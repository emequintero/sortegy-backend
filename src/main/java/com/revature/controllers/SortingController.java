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
import com.revature.util.QuickSortHelper;

@RestController
@CrossOrigin
@RequestMapping("/sort")
public class SortingController {
	ObjectMapper mapper = new ObjectMapper();
	
	@PostMapping("/quick")
	public ResponseEntity<String> quickSort(@RequestBody int[]arr) throws JsonProcessingException{
		QuickSortHelper.quickSort(arr, 0, arr.length-1);
		List<Animation> animations = QuickSortHelper.getQuickSortAnimations();
		return ResponseEntity.ok().body(
				"{\"sortedArr\" : " + mapper.writeValueAsString(arr) +
				"\"animations\" : " + mapper.writeValueAsString(animations) + "}"
		);
	}
}
