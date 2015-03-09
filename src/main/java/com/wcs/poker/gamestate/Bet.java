/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.gamestate;

/**
 *
 * @author Timi
 */
public class Bet {
    public Integer getCall(GameState gameState) {
        return gameState.getCurrentBuyIn() - gameState.getPlayers().get(gameState.getInAction()).getBet();
    }
}
