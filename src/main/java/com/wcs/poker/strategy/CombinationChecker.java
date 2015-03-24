/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.strategy;

import com.wcs.poker.gamestate.Card;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Timi
 */
public class CombinationChecker {
    
    //a legjobb eset
    private HandRank handRank=HandRank.highCard;
    
    //adott szamjegyű lapból hány darab van
    private int[] cardPiece;
    //adott figurábol mennyi van
    private int[] cardSuit;
    
    private int pairs;
    private int drills;
    private int pokers;
    private int straights;
    private int flushes;
    
    
    
    
    public boolean isBigPair(Card card1, Card card2) {
        return card1.isRankEqual(card2) && card1.rankMatches("A|K|Q|J|[7-9]");
    }
    
    public boolean isAceAndQueen(Card card1, Card card2) {
        return (card1.isRank("A") && card2.isRank("Q")) || (card1.isRank("Q") && card2.isRank("A"));
    }
    
    public boolean isSmallPair(Card card1, Card card2) {
        return card1.isRankEqual(card2) && card1.rankMatches("[2-6]");
    }
    
    
    private void cardCount(List<Card> cards)
    {
        cardPiece= new int[Card.getSequence().length];
        cardSuit= new int[Card.getSuits().length];
        for (Card card : cards) {
            for (int i=0;i<Card.getSequence().length;i++) {
                if(card.isRank(Card.getSequence()[i]))
                    cardPiece[i]++;
            }
            for (int i=0;i<Card.getSuits().length;i++) {
                if(card.isSuit(Card.getSuits()[i]))
                    cardSuit[i]++;
            }
        }
    }
    
    private void countPairDrillPoker()
    {
        pairs=0;
        drills=0;
        pokers=0;
        for (int d : cardPiece) {
            if(d==2)pairs++;
            if(d==3)drills++;
            if(d==4)pokers++;
        }
    }
    
    private void countStraight()
    {
        straights=0;
        boolean seged;
        for (int i = 0; i < cardPiece.length-5; i++) {
            seged=true;
            for (int j = 0; j < 5; j++) {
                if(cardPiece[i+j]==0)
                    seged=false;
            }
            if(seged)straights++;
        }
    }
    
    private void countFlush()
    {
        flushes=0;
        boolean seged;
        for (int i = 0; i < cardSuit.length-5; i++) {
            seged= true;
            for (int j = 0; j < 5; j++) {
                if(cardSuit[i+j]==0)
                    seged=false;
            }
            if(seged)flushes++;
        }
    }
    
    public void countAndSet(List<Card> cards)
    {
        this.cardCount(cards);
        this.countPairDrillPoker();
        this.countStraight();
        this.countFlush();
        if(flushes>0 && straights>0)
            handRank=HandRank.straightFlush;
        else if(pokers>0)
            handRank=HandRank.poker;
        else if(drills>0 & pairs>0)
            handRank=HandRank.fullHouse;
        else if(flushes>0)
            handRank=HandRank.flush;
        else if(straights>0)
            handRank=HandRank.straight;
        else if(drills>0)
            handRank=HandRank.drill;
        else if(pairs>1)
            handRank=HandRank.twoPair;
        else if(pairs>0)
            handRank=HandRank.pair;
        else
            handRank=HandRank.highCard;
    }
    
    
    
    public HandRank getHandRank() {
        return handRank;
    }
        
}
