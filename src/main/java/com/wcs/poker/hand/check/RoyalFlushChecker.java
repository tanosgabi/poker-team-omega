/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wcs.poker.hand.check;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.hand.HandRank;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author poker08
 */
public class RoyalFlushChecker implements HandChecker {
    
    private List<Card> handCards = new LinkedList<>();

    @Override
    public boolean check(List<Card> cards) {
        Map<String, List<Card>> suitCounterMap = new HashMap();
        for (Card card : cards) {
            final String suit = card.getSuit();
            List<Card> suitCards = suitCounterMap.get(suit);
            if (suitCards == null) {
                suitCards = new LinkedList<>();
                suitCounterMap.put(suit, suitCards);
            }
            suitCards.add(card);
        }

        for (Map.Entry<String, List<Card>> entry : suitCounterMap.entrySet()) {
            if (entry.getValue().size() >= 5) {
                checkRoyalFlushRank(entry.getValue());
                break;
            }
        }
        
        return (!handCards.isEmpty());
    }

    @Override
    public List<Card> getResult() {
        return handCards;
    }

    @Override
    public HandRank getRank() {
        return HandRank.ROYAL_FLUSH;
    }
    
    private void checkRoyalFlushRank(List<Card> cards) {
        String[] ranks = {"A", "K", "Q", "J", "10"};
        
        for (String rank : ranks) {
            Iterator<Card> iterator = cards.iterator();
            while (iterator.hasNext()) {
                Card next = iterator.next();
                if (rank.equals(next.getRank())) {
                    handCards.add(next);
                    iterator.remove();
                }
            }
        }
        
        if (handCards.size() != 5) {
            handCards.clear();
        }
    }
    
}
