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
public class ThreeOfAKindChecker implements HandChecker {
    
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
            if (entry.getValue().size() >= 3) {
                addThreeBest(entry.getValue());
                addTwoOtherCards(cards);
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
        return HandRank.THREE_OF_A_KIND;
    }

    private void addThreeBest(List<Card> cards) {
        Collections.sort(cards);
        for (int i = 0; i < 3; i++) {
            handCards.add(cards.get(i));
        }
    }

    private void addTwoOtherCards(List<Card> cards) {
        if (handCards.size() == 3) {
            List<Card> temp = new ArrayList<>();

            for (Card card : cards) {
                if (!handCards.contains(card)) {
                    temp.add(card);
                }
            }
            Collections.sort(temp);
            handCards.add(temp.get(0));
            handCards.add(temp.get(1));
        }
    }
    
}
