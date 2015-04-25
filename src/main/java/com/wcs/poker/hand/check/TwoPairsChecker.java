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
public class TwoPairsChecker implements HandChecker {

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

        int countPairs = 0;

        for (Map.Entry<String, List<Card>> entry : rankCounterMap.entrySet()) {
            if (countPairs <= 2 && entry.getValue().size() >= 2) {
                addPair(entry.getValue());
                countPairs++;
            }
        }

        if (handCards.size() == 4) {
            addFifthCard(cards);
        }

        return (handCards.size() == 5);
    }

    @Override
    public List<Card> getResult() {
        return handCards;
    }

    @Override
    public HandRank getRank() {
        return HandRank.TWO_PAIRS;
    }

    private void addPair(List<Card> cards) {
        for (int i = 0; i < 2; i++) {
            handCards.add(cards.get(i));
        }
    }

    private void addFifthCard(List<Card> cards) {
        for (Card card : cards) {
            if (!handCards.contains(card)) {
                handCards.add(card);
            }
        }
    }

}
