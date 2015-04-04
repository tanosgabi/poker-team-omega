/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.strategy;

import com.wcs.poker.gamestate.GameState;
import com.wcs.poker.gamestate.Player;
import java.util.List;

/**
 *
 * @author Timi
 */
public class PositionChecker {
    
    public Position checkPosition(Player player, GameState gameState) {
        List<Player> players = gameState.getPlayers();
        int size = players.size();
        int playersIndex = players.indexOf(player);
        int dealer = gameState.getDealer();
        
        if (playersIndex == dealer) {
            return Position.LATE;
        }
        
        if (playersIndex == (dealer + 1) % size || playersIndex == (dealer + 2) % size) {
            return Position.BLIND;
        }
        
        if (size > 7) {
            if (playersIndex == (dealer + 3) % size) {
                return Position.EARLY;
            }
            
            if (playersIndex == (dealer - 1) % size) {
                return Position.LATE;
            }
            
            return Position.MIDDLE;
        }
        
        return null;
    }
}
