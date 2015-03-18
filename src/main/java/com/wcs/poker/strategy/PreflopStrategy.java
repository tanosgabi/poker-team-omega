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
public class PreflopStrategy {
    public Integer calculateBet(GameState gameState) {
        List<Card> cards = gameState.getCurrentCards();
        Card card1 = cards.get(0);
        Card card2 = cards.get(1);
        
        if ((card1.isRankEqual(card2) && card1.rankMatches("A|K|Q|J|[7-9]"))
                || (card1.isRank("A") && card2.isRank("Q"))
                || (card1.isRank("Q") && card2.isRank("A"))) {
            return gameState.getMinimumBet();
        }
        else if (card1.getRank().equals(card2.getRank()) && card1.rankMatches("[2-6]")) {
            return gameState.getCall();
        }
        
        return 0;
    }
}
