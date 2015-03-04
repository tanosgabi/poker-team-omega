/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.leanpoker.player;

import com.wcs.poker.gamestate.GameState;
import com.wcs.poker.gamestate.Player;

/**
 *
 * @author poker15
 */
public class CurrentState {
    public Player getCurrentPlayer(GameState gameState)
    {
        return gameState.getPlayers().get(gameState.getInAction());
    }
    
}
