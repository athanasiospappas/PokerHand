package com.poker.model;

import java.util.List;

public class PokerHandSortingResponse {

	private List<PokerHandResponse> listOfHands;

	public List<PokerHandResponse> getListOfHands() {
		return listOfHands;
	}

	public void setListOfHands(List<PokerHandResponse> listOfHands) {
		this.listOfHands = listOfHands;
	}
}
