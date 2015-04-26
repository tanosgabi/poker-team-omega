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
    List<HandRank> winnerHandRank=new LinkedList<>();
    
    public CombinationChecker() {
        winnerHandRank.add(HandRank.ROYAL_FLUSH);
        winnerHandRank.add(HandRank.STRAIGHT_FLUSH);
        winnerHandRank.add(HandRank.FOUR_OF_A_KIND);
        winnerHandRank.add(HandRank.FULL_HOUSE);
        winnerHandRank.add(HandRank.FLUSH);
        winnerHandRank.add(HandRank.STRAIGHT);
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
    
    public double turnOdds(List<Card> cards)
    {
        double odds=0;
        otherCardsSet(cards);
        for (Card otherCard : otherCards) {
            cards.add(otherCard);
            if(winnerHandRank.contains(new HandRankingService().evaulate(cards).getRank())){
                odds++;
            }
            cards.remove(otherCard);
        }
        return odds/(otherCards.size());
    }

    public double flopOdds(List<Card> cards)
    {
        double odds=0;
        otherCardsSet(cards);
        for (int i = 0; i < otherCards.size()-1; i++) {
            Card get = otherCards.get(i);
            cards.add(get);
            for (int j = i+1; j < otherCards.size(); j++) {
                Card get1 = otherCards.get(j);
                cards.add(get1);
                if(winnerHandRank.contains(new HandRankingService().evaulate(cards).getRank())){
                    odds++;
                }
                cards.remove(get1);
            }
            cards.remove(get);
        }
        return odds/((otherCards.size()*(otherCards.size()-1))/2);
    }
    
    
    private void otherCardsSet(List<Card> cards) {
        for (String allRank : Card.getAllRanks()) {
            for (String suit : Card.getSuits()) {
                otherCards.add(new Card(allRank, suit));
            }
        }
        for (Card card : cards) {
            if(otherCards.contains(card)){
                otherCards.remove(card);
            }
        }
    }
}
