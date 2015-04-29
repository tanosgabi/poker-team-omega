/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.strategy;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.hand.HandRank;
import com.wcs.poker.hand.HandRankingService;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Timi
 */
public class CombinationChecker {
    List<Card> otherCards=new LinkedList<>();
    List<HandRank> listHandRank=new LinkedList<>();
    
    public CombinationChecker() {
        listHandRank.add(HandRank.ROYAL_FLUSH);
        listHandRank.add(HandRank.STRAIGHT_FLUSH);
        listHandRank.add(HandRank.FOUR_OF_A_KIND);
        listHandRank.add(HandRank.FULL_HOUSE);
        listHandRank.add(HandRank.FLUSH);
        listHandRank.add(HandRank.STRAIGHT);
        listHandRank.add(HandRank.THREE_OF_A_KIND);
        listHandRank.add(HandRank.TWO_PAIRS);
        listHandRank.add(HandRank.PAIR);
        listHandRank.add(HandRank.HIGH_CARD);
    }
    
    
    public boolean isBigPair(Card card1, Card card2) {
        return card1.isRankEqual(card2) && card1.rankMatches("A|K|Q|J|[7-9]");
    }
    
    public boolean isAceAndQueen(Card card1, Card card2) {
        return (card1.isRank("A") && card2.isRank("Q")) || (card1.isRank("Q") && card2.isRank("A"));
    }
    
    public boolean isSmallPair(Card card1, Card card2) {
        return card1.isRankEqual(card2) && card1.rankMatches("[2-6]");
    }
    
//    public double turnOdds(List<Card> cards,HandRank myrank)
//    {
//        double odds=0;
//        otherCardsSet(cards);
//        for (Card otherCard : otherCards) {
//            cards.add(otherCard);
//            if(winnerHandRank.contains(new HandRankingService().evaulate(cards).getRank())){
//                odds++;
//            }
//            cards.remove(otherCard);
//        }
//        return odds/(otherCards.size());
//    }

    public double odds(List<Card> cards,HandRank myRank,List<Card> myCard)
    {
        int myIndex=listHandRank.indexOf(myRank);
        double odds=0;
        otherCardsSet(cards,myCard);
        for (int i = 0; i < otherCards.size()-1; i++) {
            Card get = otherCards.get(i);
            cards.add(get);
            for (int j = i+1; j < otherCards.size(); j++) {
                Card get1 = otherCards.get(j);
                cards.add(get1);
                if(listHandRank.indexOf(new HandRankingService().evaulate(cards).getRank())<=myIndex){
                    odds++;
                }
                cards.remove(get1);
            }
            cards.remove(get);
        }
        return odds/((otherCards.size()*(otherCards.size()-1))/2);
    }
    
    
    private void otherCardsSet(List<Card> cards1,List<Card> cards2) {
        for (String allRank : Card.getAllRanks()) {
            for (String suit : Card.getSuits()) {
                otherCards.add(new Card(allRank, suit));
            }
        }
        cards1.addAll(cards2);
        for (Card card : cards1) {
            if(otherCards.contains(card)){
                otherCards.remove(card);
            }
        }
        cards1.removeAll(cards2);
    }
}
