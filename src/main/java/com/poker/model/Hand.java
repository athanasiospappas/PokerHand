package com.poker.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.poker.util.PokerHandHelper;

public final class Hand implements Comparable<Hand>{
    
    final static String VALUES = "AKQJT98765432";
    final static String SUITS = "HDSC";
    final static Map<Integer, String> rankMapping;
    
    private List<Card> cards;
    private int ranking = 0;
    private String handValue;
    
    public String getHandValue() {
		return handValue;
	}

	public void setHandValue(String handValue) {
		this.handValue = handValue;
	}

	//helper Maps
    Map<String, List<Card>> suitsMap;
    Map<Integer, List<Card>> valuesMap;
    
    static {
        rankMapping = PokerHandHelper.createMapping();
    }
    
    private Hand() {}
    
    private Hand(List<Card> cards){
        this.cards = cards;
    }
    
    public static Hand fromString(String input){
        final String[] hand = input.split(" ");      
        checkCards(hand);
        List<Card> list = new ArrayList<>();
  
        for(String card : hand){
            Card c = new Card(card.charAt(0), card.charAt(1));
            list.add(c);
        }
        
        Collections.sort(list);
        Hand aHand = new Hand(list);
        aHand.createMaps(list);
        aHand.evalRank();
        return aHand;
    }
	
	/**
	* Two helper maps are created ot help us with determing the value
	* of the hand. 
	* One map is per int value of the card and the other per suit.
	**/
	private void createMaps(List<Card> list) {
        suitsMap = cards.stream().collect(Collectors.groupingBy(Card::getSuit));
        valuesMap = cards.stream().collect(Collectors.groupingBy(Card::getIntValue));
    }
    
    /**
     * Various checks to validate the hand
     * @param hand 
     */
    private static void checkCards(final String[] hand){
        if(hand.length != 5){
            throw new RuntimeException("Invalid hand: A hand can have only 5 cards");
        }
        
        if (new HashSet<>(Arrays.asList(hand)).size() != hand.length){
            throw new RuntimeException("Invalid hand: Duplicate cards");
        }
        
        for (String card : hand) {
            if (VALUES.indexOf(card.charAt(0)) == -1){
                throw new RuntimeException("Invalid hand: non existing value");
            }
 
            if (SUITS.indexOf(card.charAt(1)) == -1){
                throw new RuntimeException("Invalid hand: non existing suit");
            }
        }
    }
    
    /**
     * Evaluates the hand
     * 
     */
    private void evalRank(){
        if(isPair()){
        	setRanking(2);
        }else if(isTwoPair()){
        	setRanking(3);
        }else if(isThreeOfAKind()){
        	setRanking(4);
        }else if(isStraight()){
        	setRanking(5);
        }else if(isFlush()){
        	setRanking(6);
        }else if(isFullHouse()){
        	setRanking(7);
        }else if(isFourOfAKind()){
        	setRanking(8);
        }else if(isStraightFlush()){
        	setRanking(9);
        }else if(isRoyalFlush()){
        	setRanking(10);
        }else {
        	setRanking(1);
        }
                             
    }
    
    /**
     * If the map per value consists of
     * exactly 4 values, then it is a Pair
     * 
     * @return 
     */
    private boolean isPair(){
        return valuesMap.values().size() == 4;
    }
    
    /**
     * If the map per value
     * has three values and no three cards of the same value,
     * then it is a Two Pair
     * @return 
     */
    private boolean isTwoPair(){
        final boolean threeValues = valuesMap.entrySet().size() == 3;
        if(!threeOfKind() && threeValues){
            return true;
        }
        return false;
    }
    
    /**
     * If the map per value
     * has three values, one of which has a list of three cards
	 * (meaning the other two have one card each)
     * then it is a Three of A Kind
     * @return 
     */
    private boolean isThreeOfAKind(){
        final boolean threeValues = valuesMap.entrySet().size() == 3;
        if(threeOfKind() && threeValues) {
            return true;
        }
        return false;
    }
    
    /**
     * If the map per suit
     * has all suits and the cards have consecutive values,
     * then it is a Straight
     * @return 
     */
    public boolean isStraight(){
        final boolean diffValues = cards.get(cards.size() - 1).getIntValue() - cards.get(0).getIntValue() == 4;
        final boolean diffSuits = suitsMap.entrySet().size() > 1;
        if(diffValues && diffSuits){
            return true;
        }
        return false;
    }
    
    /**
     * If the map per suit
     * has only one suit but with no consecutive value cards
     * then it is a Flush
     * @return 
     */
    private boolean isFlush(){
        if(isJustFlush()){
            final int diff = cards.get(cards.size() - 1).getIntValue() - cards.get(0).getIntValue();
            final boolean isFlush = diff != 4;
            if(isFlush){
                return isFlush;
            }
        }
        return false;
    }
    
    /**
     * If the map per values
     * has one of list of three cards and 
	 * one list of two cards
     * then it is a Full House
     * @return 
     */
    private boolean isFullHouse(){
        final boolean threeofKind = valuesMap.values().stream().anyMatch((list) -> (list.size() == 3));
        final boolean twoOfKind = valuesMap.values().stream().anyMatch((list) -> (list.size() == 2));
        if(threeofKind && twoOfKind){
            return true;
        }
        return false;
    }
    
    /**
     * If the map per values
     * has a list of four cards
     * then it is Four of a kind
     * @return 
     */
    private boolean isFourOfAKind(){
        return valuesMap.values().stream().anyMatch((list) -> (list.size() == 4));
    }
    
    /**
     * If the it's a flush
     * with consecutive value cards not starting in 10
     * then it is a Straight Flush
     * @return 
     */
    private boolean isStraightFlush(){
        if(isJustFlush()){
            final int diff = cards.get(cards.size() - 1).getIntValue() - cards.get(0).getIntValue();
            final boolean straightFlush = diff == 4 && (cards.get(0).getIntValue() != 10);
            if(straightFlush){
                return straightFlush;
            }
        }
        
        return false;
    }
    
    /**
     * If the it's a flush
     * with consecutive value cards starting in 10
     * then it is a Royal Flush
     * @return 
     */
    private boolean isRoyalFlush(){
        if(isJustFlush()){
            final int diff = cards.get(cards.size() - 1).getIntValue() - cards.get(0).getIntValue();
            final boolean royalFlush = diff == 4 && (cards.get(0).getIntValue() == 10);
            if(royalFlush){
                return royalFlush;
            }
        }
        return false;
    }
    
    /**
     * @param other
     * @return 
     */
    @Override
    public int compareTo(Hand other) {
        return this.ranking - other.ranking;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        cards.forEach((c) -> {
            sb.append(c).append(" ");
        });
        sb.append("]");
        return sb.toString();
    }
    
    private void setRanking(int ranking){
        this.ranking = ranking;
        handValue = rankMapping.get(ranking);
    }
    
    /**
     * Checks if there is only one suit in the hand
     * 
     * @return 
     */
    private boolean isJustFlush(){
        return suitsMap.values().stream().anyMatch((list) -> (list.size() == 5));
    }
    
    /**
    * Checks if there are three cards or the same value
    * 
    * @return 
    */
    private boolean threeOfKind(){
        return valuesMap.values().stream().anyMatch((list) -> (list.size() == 3));
    }
    
}
