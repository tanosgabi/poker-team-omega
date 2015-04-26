/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.hand;

import com.wcs.poker.gamestate.Card;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Gábor
 */
public class Sort {
    private HandRank handRank=null;
    
    //adott szamjegyű lapból hány darab van
    private int[] cardPiece;
    //adott figurábol mennyi van
    private int[] cardSuit;
    
    private int pairs;
    private int drills;
    private int pokers;
    private int straights;
    private int flushes;
    
    Comparator<Card> comparator = new Comparator<Card>() {
        public int compare(Card c1, Card c2) {
            int myRankValue=-1;
            int otherValue=-1;
            for (int i=0;i<Card.ranks.length;i++) {
                if(c1.isRank(Card.ranks[i]))
                    myRankValue=i;
                if(c2.isRank(Card.ranks[i]))
                    otherValue=i;
            }
            if(myRankValue>otherValue)
                return -1;
            else if(myRankValue<otherValue)
                return +1;
            else
                return 0;
        }
    };
    
    private void cardCount(List<Card> cards)
    {
        cardPiece= new int[Card.getAllRanks().length];
        cardSuit= new int[Card.getSuits().length];
        for (Card card : cards) {
            for (int i=0;i<Card.getAllRanks().length;i++) {
                if(card.isRank(Card.getAllRanks()[i]))
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
        for (int d : cardSuit) {
            if(d>=5)flushes++;
        }
    }
    
    public void countAndSet(List<Card> cards)
    {
        this.cardCount(cards);
        this.countPairDrillPoker();
        this.countStraight();
        this.countFlush();
        if(flushes>0 && straights>0){
            handRank=HandRank.STRAIGHT_FLUSH;
        }else if(pokers>0){
            handRank=HandRank.FOUR_OF_A_KIND;
        }else if(drills>0 & pairs>0|| drills>1){
            handRank=HandRank.FULL_HOUSE;
        }else if(flushes>0){
            handRank=HandRank.FLUSH;
        }else if(straights>0){
            handRank=HandRank.STRAIGHT;
        }else if(drills>0){
            handRank=HandRank.THREE_OF_A_KIND;
        }else if(pairs>1){
            handRank=HandRank.TWO_PAIRS;
        }else if(pairs>0){
            handRank=HandRank.PAIR;
        }else{
            handRank=HandRank.HIGH_CARD;
        }
    }

    private int maxIndexSuit()
    {
        int index=0;
        for(int i=1;i<cardSuit.length;i++)
            if(cardSuit[index]<=cardSuit[i])
                index=i;
        return index;
    }
    
    private int maxSuit()
    {
        int maxsuit=cardSuit[0];
        for(int i=1;i<cardSuit.length;i++)
            if(maxsuit<=cardSuit[i])
                maxsuit=cardSuit[i];
        return maxsuit;
    }
    
    private int maxIndexPiece()
    {
        int index=0;
        for(int i=1;i<cardPiece.length;i++)
            if(cardPiece[index]<=cardPiece[i])
                index=i;
        return index;
    }
    
    private int maxPiece()
    {
        int maxpiece=cardPiece[0];
        for(int i=1;i<cardPiece.length;i++)
            if(maxpiece<=cardPiece[i])
                maxpiece=cardPiece[i];
        return maxpiece;
    }
    
    public List<Card> SortingbySuitList(List<Card> cards){
        List<Card> sortedCards= new ArrayList<>();
        cardCount(cards);
        if(maxSuit()==1){
            Collections.sort(cards, comparator);
            return cards;
        }
        for(int i=cards.size()-1;i>=0;i--) {
            if(cards.get(i).isSuit(Card.suits[maxIndexSuit()]))
            {
                sortedCards.add(cards.get(i));
                cards.remove(i);
            }
        }
        Collections.sort(sortedCards, comparator);
        if(cards.size()>=1)
            sortedCards.addAll(SortingbySuitList(cards));
        return sortedCards;
    }
            

    public List<Card> SortingbyRankList(List<Card> cards){
        List<Card> sortedCards= new ArrayList<>();
        cardCount(cards);
        if(maxPiece()==1){
            Collections.sort(cards, comparator);
            return cards;
        }
        for(int i=cards.size()-1;i>=0;i--) {
            if(cards.get(i).isSuit(Card.ranks[maxIndexPiece()]))
            {
                sortedCards.add(cards.get(i));
                cards.remove(i);
            }
        }
        if(cards.size()>=1)
            sortedCards.addAll(SortingbySuitList(cards));
        return sortedCards;
    }
}
