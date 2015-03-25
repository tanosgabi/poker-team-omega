package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import com.wcs.poker.strategy.*;
import java.util.List;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(GameState gameState) {
        int phase = gameState.getCommunityCards().size();
        CombinationChecker checker = new CombinationChecker();
        StrategyFactory factory = new StrategyFactory();
        Strategy strategy = factory.createStrategy(gameState, phase, checker);
        return strategy.calculateBet();
    }

    public static void showdown(GameState gameState) {
    }
}
