package com.poker.util;

import java.util.List;
import java.util.stream.Collectors;

import com.poker.model.Hand;
import com.poker.model.PokerHandComparingResponse;
import com.poker.model.PokerHandSortingResponse;
import com.poker.model.PokerHandResponse;

public class EntityBuilder {

	public static PokerHandResponse from(Hand hand) {
		PokerHandResponse response = new PokerHandResponse();
		response.setHandValue(hand.getHandValue());
		response.setPokerHand(hand.toString());
		return response;
	}

	public static PokerHandSortingResponse from(List<Hand> hands) {
		List<PokerHandResponse> responseList = hands.stream().map(x -> EntityBuilder.from(x)).collect(Collectors.toList());
		PokerHandSortingResponse response = new PokerHandSortingResponse();
		response.setListOfHands(responseList);
		return response;
	}
	
	public static PokerHandComparingResponse from(Hand hand, String message) {
		PokerHandComparingResponse response = new PokerHandComparingResponse();
		response.setMessage(message);
		response.setWinningHand(hand);
		return response;
	}

}
