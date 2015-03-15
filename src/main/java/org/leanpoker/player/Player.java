package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(GameState gameState) {
        Card card1 = gameState.getCurrentPlayer().getHoleCards().get(0);
        Card card2 = gameState.getCurrentPlayer().getHoleCards().get(1);
        if (card1.getRank().equals(card2.getRank())) {
            if (card1.getRank().matches("[1-9]")) {
                return gameState.getCall();
            }
            else {
                return gameState.getMinimumBet();
            }
        }
        
        return 0;
    }

    public static void showdown(GameState gameState) {
    }
}
