# PokerHand

This is a Spring Boot project.
Its REST API consist of three endpoints.

1. Takes a String representation of the Hand e.g "4D 4C 4H 7H 8D" and returns its value.
This example obeys the following rules:

-The possible Card 'Values' are:
VALUES = ['2','3','4','5','6','7','8','9','T', 'J', 'Q', 'K', 'A']
Where T = 10, J = 'Jack', Q = 'Queen', K = 'King' and A is 'Ace'

-The possible 'Suits' are,
SUITS = ['C', 'D', 'H', 'S']
Where C = 'Clubs', D = 'Diamonds', H = 'Hearts' and S = 'Spades'.

A Hand contains 5 cards and is immutable and initializable only from that string.
The Hand is able to deternime its value, eg. "TS, JS, QS, KS, AS" is a Royal Flush.
The Hand can be sorted and be comparable in consist way with values in VALUES above. 

2. Given a list of Strings like above, the end point returns a list of sorted by valus Hands

3. Given two hands, the end point returns the winning hand. (in the initial commit no case of draw was considered yet)
