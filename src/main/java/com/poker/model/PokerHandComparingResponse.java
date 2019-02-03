package com.poker.model;

public class PokerHandComparingResponse {
	
	private Hand winningHand;
	private String message;
	
	public Hand getWinningHand() {
		return winningHand;
	}
	public void setWinningHand(Hand winningHand) {
		this.winningHand = winningHand;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
