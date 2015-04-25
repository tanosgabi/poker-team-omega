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
public class FullHouseChecker implements HandChecker {
    
    private List<Card> handCards = new LinkedList<>();

    @Override
    public boolean check(List<Card> cards) {
        Collections.sort(cards);
        Map<String, List<Card>> rankCounterMap = new HashMap();
        for (Card card : cards) {
            final String rank = card.getRank();
            List<Card> rankCards = rankCounterMap.get(rank);
            if (rankCards == null) {
                rankCards = new LinkedList<>();
                rankCounterMap.put(rank, rankCards);
            }
            rankCards.add(card);
        }
        
        String rankOfThree = null;

        for (Map.Entry<String, List<Card>> entry : rankCounterMap.entrySet()) {
            if (entry.getValue().size() >= 3) {
                addThree(entry.getValue());
                rankOfThree = entry.getKey();
                break;
            }
        }
        
        for (Map.Entry<String, List<Card>> entry : rankCounterMap.entrySet()) {
            if (entry.getValue().size() >= 2 && !entry.getKey().equals(rankOfThree)) {
                addPair(entry.getValue());
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
        return HandRank.FULL_HOUSE;
    }

    private void addThree(List<Card> cards) {
        for (int i = 0; i < 3; i++) {
            handCards.add(cards.get(i));
        }
    }

    private void addPair(List<Card> cards) {
        for (int i = 0; i < 2; i++) {
            handCards.add(cards.get(i));
        }
    }
    
}
