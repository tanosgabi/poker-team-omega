/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.strategy;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import com.wcs.poker.hand.Hand;
import com.wcs.poker.hand.HandRank;
import com.wcs.poker.hand.HandRankingService;
import java.util.List;

/**
 *
 * @author Gábor
 */
public class TurnStrategy extends AbstractStrategy {
    final Integer MULTIPLIER=2;
    final double BET_ODDS=0.99;
    final double MINIMUM_BET_ODDS=0.70;
    final double CALL_ODDS=0.60;
    
    
    public TurnStrategy(GameState gameState, CombinationChecker checker) {
        super(gameState, checker);
    }
    
    @Override
    public Integer calculateBet() {
        List<Card> myCard=gameState.getCurrentCards();
        myCard.addAll(gameState.getCommunityCards());
        HandRank myHandRank= new HandRankingService().evaulate(myCard).getRank();
        myCard.removeAll(gameState.getCommunityCards());
        if((double)gameState.getCurrentPlayer().getBet()/(double)gameState.getCurrentPlayer().getStack()>0.3){
            return gameState.getCall();
        }else if(checker.odds(gameState.getCommunityCards(),myHandRank,myCard)>BET_ODDS){
                return gameState.getMinimumBet()*MULTIPLIER;
            }else if(checker.odds(gameState.getCommunityCards(),myHandRank,myCard)>MINIMUM_BET_ODDS){
                return gameState.getMinimumBet();
            }else if(checker.odds(gameState.getCommunityCards(),myHandRank,myCard)>CALL_ODDS){
                return gameState.getCall();
            }else{
                return 0;
            }
    }
}
