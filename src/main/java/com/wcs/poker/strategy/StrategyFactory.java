/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.strategy;

import com.wcs.poker.gamestate.GameState;

/**
 *
 * @author Timi
 */
public class StrategyFactory {
    
    public Strategy createStrategy(GameState gameState, int phase, CombinationChecker checker) {
        switch (phase) {
            case 0: return new PreflopStrategy(gameState, checker);
            case 3: return new FlopStrategy(gameState, checker);
            case 4: return new TurnStrategy(gameState, checker);
            case 5: return new RiverStrategy(gameState, checker);
        }
        return null;
    }
}
