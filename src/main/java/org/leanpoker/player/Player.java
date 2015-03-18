package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import com.wcs.poker.strategy.PreflopStrategy;
import java.util.List;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(GameState gameState) {
        PreflopStrategy preflopStrategy = new PreflopStrategy();
        return preflopStrategy.calculateBet(gameState);
    }

    public static void showdown(GameState gameState) {
    }
}
