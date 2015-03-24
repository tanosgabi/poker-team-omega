/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.strategy;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import java.util.List;

/**
 *
 * @author Gábor
 */
public class FlopStategy {
    public Integer calculateBet(GameState gameState) {
        List<Card> cards = gameState.getCurrentCards();
        Card card1 = cards.get(0);
        Card card2 = cards.get(1);
        CombinationChecker checker = new CombinationChecker();
        
        if (checker.isBigPair(card1, card2) || checker.isAceAndQueen(card1, card2)) {
            return gameState.getMinimumBet();
        }
        else if (checker.isSmallPair(card1, card2)) {
            return gameState.getCall();
        }
        
        return 0;
    }
}
