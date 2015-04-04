/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.strategy;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;

/**
 *
 * @author Timi
 */
public class PreflopStrategy extends AbstractStrategy {

    public PreflopStrategy(GameState gameState, CombinationChecker checker) {
        super(gameState, checker);
    }
    
    @Override
    public Integer calculateBet() {
        Card card1 = cards.get(0);
        Card card2 = cards.get(1);
        
        if (checker.isBigPair(card1, card2) || checker.isAceAndQueen(card1, card2)) {
            return gameState.getMinimumBet();
        }
        else if (checker.isSmallPair(card1, card2)) {
            return gameState.getCall();
        }
        
        return 0;
    }
}
