package com.poker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poker.model.PokerHandSortingInput;
import com.poker.model.PokerHandComparingInput;
import com.poker.model.PokerHandComparingResponse;
import com.poker.model.PokerHandInput;
import com.poker.model.PokerHandResponse;
import com.poker.model.PokerHandSortingResponse;
import com.poker.service.PokerHandService;

@RestController
@RequestMapping("/hand")
public class PokerHandController {

	@Autowired
	private PokerHandService pokerHandService;
	
	@PostMapping("")
	public ResponseEntity<PokerHandResponse> getHand(@RequestBody PokerHandInput pokerHandInput){
		return ResponseEntity.ok(pokerHandService.createHand(pokerHandInput.getInput()));	
	}
	
	@PostMapping("/sort")
	public ResponseEntity<PokerHandSortingResponse> sortHands(@RequestBody PokerHandSortingInput pokerHandSortingInput){
		return ResponseEntity.ok(pokerHandService.sortHands(pokerHandSortingInput.getInput()));
	}

	@PostMapping("/compare")
	public ResponseEntity<PokerHandComparingResponse> compareHands(@RequestBody PokerHandComparingInput pokerHandComparingInput){
		return ResponseEntity.ok(pokerHandService.compareHands(pokerHandComparingInput.getHandOne(),
				pokerHandComparingInput.getHandTwo()));
	}
}
