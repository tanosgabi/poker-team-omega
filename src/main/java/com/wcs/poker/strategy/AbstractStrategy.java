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
 * @author Timi
 */
public abstract class AbstractStrategy implements PokerStrategy {
    protected GameState gameState;
    protected List<Card> cards;
    protected CombinationChecker checker;

    public AbstractStrategy(GameState gameState, CombinationChecker checker) {
        this.gameState = gameState;
        this.cards = gameState.getCurrentCards();
        this.checker = checker;
    }
}
