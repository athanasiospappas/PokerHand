package com.poker.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.poker.model.Hand;
import com.poker.model.PokerHandComparingResponse;
import com.poker.model.PokerHandSortingResponse;
import com.poker.model.PokerHandResponse;
import com.poker.util.EntityBuilder;

@Service
public class PokerHandService {
	
	public PokerHandResponse createHand(String input) {
		Hand hand = Hand.fromString(input);
		return EntityBuilder.from(hand);
	}

	public PokerHandSortingResponse sortHands(List<String> input) {
		List<Hand> hands = input.stream().map(x -> Hand.fromString(x)).collect(Collectors.toList());
		Collections.sort(hands);
		return EntityBuilder.from(hands);
	}

	public PokerHandComparingResponse compareHands(String handOne, String handTwo) {
		Hand hand1 = Hand.fromString(handOne);
		Hand hand2 = Hand.fromString(handTwo);
		return handleComparison(hand1, hand2);
	}

	private PokerHandComparingResponse handleComparison(Hand hand1, Hand hand2) {
		int compare = hand1.compareTo(hand2);
		String message = "";
		Hand winningHand;
		if(compare > 0) {
			message += "Hand 1 ";
			winningHand = hand1;
		}else if(compare < 0) {
			message += "Hand 2 ";
			winningHand = hand2;
		}else {
			message = "Both hands ";
			winningHand = hand1;
		}
		message += "win(s)!";
		return EntityBuilder.from(winningHand, message);
	}
}
