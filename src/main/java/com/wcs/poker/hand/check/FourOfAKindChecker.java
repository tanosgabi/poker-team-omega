/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.hand.check;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.hand.HandRank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Timi
 */
public class FourOfAKindChecker implements HandChecker {
    
    private List<Card> handCards = new LinkedList<>();

    @Override
    public boolean check(List<Card> cards) {
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

        for (Map.Entry<String, List<Card>> entry : rankCounterMap.entrySet()) {
            if (entry.getValue().size() >= 4) {
                for (Card card : entry.getValue()) {
                    handCards.add(card);
                }
                addFifthCard(cards);
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
        return HandRank.FOUR_OF_A_KIND;
    }

    private void addFifthCard(List<Card> cards) {
        List<Card> temp = new ArrayList<>();
        
        for (Card card : cards) {
            if (!handCards.contains(card)) {
                temp.add(card);
            }
        }
        Collections.sort(temp);
        handCards.add(temp.get(0));
    }
    
}
