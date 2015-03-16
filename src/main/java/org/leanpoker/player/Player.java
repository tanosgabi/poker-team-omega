package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import java.util.List;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(GameState gameState) {
        List<Card> cards = gameState.getCurrentCards();
        Card card1 = cards.get(0);
        Card card2 = cards.get(1);
        
        if ((card1.getRank().equals(card2.getRank()) && card1.getRank().matches("A|K|Q|J|[7-9]"))
                || (card1.getRank().equals("A") && card2.getRank().equals("Q"))
                || (card1.getRank().equals("Q") && card2.getRank().equals("A"))) {
            return gameState.getMinimumBet();
        }
        else if (card1.getRank().equals(card2.getRank()) && card1.getRank().matches("[2-6]")) {
            return gameState.getCall();
        }
        
        return 0;
    }

    public static void showdown(GameState gameState) {
    }
}
