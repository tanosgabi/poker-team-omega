/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.hand.check;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.hand.HandRank;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Timi
 */
public class HighCardChecker implements HandChecker {
    
    private List<Card> handCards = new LinkedList<>();

    @Override
    public boolean check(List<Card> cards) {
        Collections.sort(cards);
        for (int i = 0; i < 5; i++) {
            handCards.add(cards.get(i));
        }
        
        return (handCards.size() == 5);
    }

    @Override
    public List<Card> getResult() {
        return handCards;
    }

    @Override
    public HandRank getRank() {
        return HandRank.HIGH_CARD;
    }
    
}
