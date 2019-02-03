package com.poker.util;

import java.util.HashMap;
import java.util.Map;

public class PokerHandHelper {
	
	public static Map<Integer, String> createMapping() {
		Map<Integer, String> rankMapping = new HashMap<>();
		rankMapping.put(1, "High card");
        rankMapping.put(2, "One pair");
        rankMapping.put(3, "Two pair");
        rankMapping.put(4, "Three of a kind");
        rankMapping.put(5, "Straight");
        rankMapping.put(6, "Flush");
        rankMapping.put(7, "Full house");
        rankMapping.put(8, "Four of a kind");
        rankMapping.put(9, "Straight flush");
        rankMapping.put(10, "Royal flush");
		
        return rankMapping;
	}

}
