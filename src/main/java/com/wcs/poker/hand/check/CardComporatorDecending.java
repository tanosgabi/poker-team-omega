/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.hand.check;

import com.wcs.poker.gamestate.Card;
import java.util.Comparator;

/**
 *
 * @author Gábor
 */
public class CardComporatorDecending implements Comparator<Card>{

    @Override
    public int compare(Card c1, Card c2) {
        int myRankValue=-1;
        int otherValue=-1;
        for (int i=Card.getAllRanks().length-1;i>=0;i--) {
            if(c1.isRank(Card.getAllRanks()[i])){
                myRankValue=i;
                break;
            }
        }
        for (int i=Card.getAllRanks().length-1;i>=0;i--) {
            if(c2.isRank(Card.getAllRanks()[i])){
                otherValue=i;
                break;
            }
        }
        if(myRankValue>otherValue)
            return -1;
        else if(myRankValue<otherValue)
            return +1;
        else
            return 0;
    }   
}
