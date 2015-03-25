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
public abstract class Strategy {
    protected GameState gameState;
    protected List<Card> cards;
    protected CombinationChecker checker;

    public Strategy(GameState gameState, CombinationChecker checker) {
        this.gameState = gameState;
        this.cards = gameState.getCurrentCards();
        this.checker = checker;
    }
    
    public Integer calculateBet() {
        return 0;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public CombinationChecker getChecker() {
        return checker;
    }

    public void setChecker(CombinationChecker checker) {
        this.checker = checker;
    }
}
