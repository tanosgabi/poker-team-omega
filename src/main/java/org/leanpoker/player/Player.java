package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import com.wcs.poker.strategy.*;
import java.util.List;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(GameState gameState) {
        PreflopStrategy preflopStrategy = new PreflopStrategy();
        FlopStategy flopStategy= new FlopStategy();
        TurnStrategy turnStrategy= new TurnStrategy();
        RiverStrategy riverStrategy= new RiverStrategy();
        
        if(gameState.getCommunityCards().size()==0)
            return preflopStrategy.calculateBet(gameState);
        if(gameState.getCommunityCards().size()==3)
            return flopStategy.calculateBet(gameState);
        if(gameState.getCommunityCards().size()==4)
            return turnStrategy.calculateBet(gameState);
        return riverStrategy.calculateBet(gameState);
    }

    public static void showdown(GameState gameState) {
    }
}
