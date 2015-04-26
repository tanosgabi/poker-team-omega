/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.hand.check;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.hand.HandRank;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Timi
 */
public class FlushChecker implements HandChecker {
    
    private List<Card> handCards = new LinkedList<>();

    @Override
    public boolean check(List<Card> cards) {
        Collections.sort(cards);
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
                addFiveBest(entry.getValue());
                break;
            }
        }
        
        return (handCards.size() == 5);
    }

    @Override
    public List<Card> getResult() {
        return handCards;
    }

    @Override
    public HandRank getRank() {
        return HandRank.FLUSH;
    }

    private void addFiveBest(List<Card> cards) {
        Collections.sort(cards,new CardComporatorDecending());
        for (int i = 0; i < 5; i++) {
            handCards.add(cards.get(i));
        }
    }
    
}
