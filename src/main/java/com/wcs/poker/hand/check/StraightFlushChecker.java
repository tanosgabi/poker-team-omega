/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.hand.check;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.hand.HandRank;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gábor
 */
public class StraightFlushChecker implements HandChecker{
   
    private static final HandRank HAND_RANK=HandRank.STRAIGHT_FLUSH;
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
                checkStraight(entry.getValue());
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
        return HAND_RANK;
    }

    private void checkStraight(List<Card> value) {
        StraightChecker straightChecker=new StraightChecker();
        straightChecker.check(value);
        handCards=straightChecker.getResult();
    }
    
}

