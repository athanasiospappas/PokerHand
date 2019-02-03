package com.poker.model;

import java.util.Objects;

public class Card implements Comparable<Card> {
   
    private final String suit;
    //intValue is used for sorting and charValue for printing
    private int intValue;
    private char charValue;
    
    public Card(char value, char suit) {
        this.charValue = value;
        if (value >= '2' && value<= '9') {
            this.intValue = Character.getNumericValue(value);
	} else {
            switch (value) {
                case 'T':
                    this.intValue = 10;
                    break;
                case 'J':
                    this.intValue = 11;
                    break;
                case 'Q':
                    this.intValue = 12;
                    break;
                case 'K':
                    this.intValue = 13;
                    break;
                case 'A':
                    this.intValue = 14;
                    break;
                default:
                    break;
            }
	}	
	this.suit = Character.toString(suit);
    }
    
    public int getIntValue() {
	return intValue;
    }
    
    public String getSuit() {
	return suit;
    }
    
    public String getCharvalue() {
	return Character.toString(charValue);
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Card card = (Card) other;
        
        return this.intValue == card.intValue && 
                this.suit.equals(card.suit);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.suit);
        hash = 37 * hash + this.intValue;
        hash = 37 * hash + this.charValue;
        return hash;
    }
    
    @Override
    public String toString(){
        return getCharvalue() + getSuit();
    }

    @Override
    public int compareTo(Card other) {
        return this.intValue - other.intValue;
    }
  
}