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
public class FlopStrategy extends Strategy {

    public FlopStrategy(GameState gameState, CombinationChecker checker) {
        super(gameState, checker);
    }
    
    @Override
    public Integer calculateBet() {
        return new PreflopStrategy(gameState, checker).calculateBet();
    }
}
