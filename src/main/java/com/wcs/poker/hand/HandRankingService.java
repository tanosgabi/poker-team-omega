package com.wcs.poker.hand;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.CardOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thomas
 */
public class HandRankingService {
    
    private List<Card> handCards;

    public Hand evaulate(List<Card> cards) {
        if (cards.size() >= 8 || cards.size() <= 4) {
            throw new IllegalArgumentException("");
        }

        if (containsRoyalFlush(cards)) {
            return new Hand(HandRank.ROYAL_FLUSH, handCards);
        }
        
        if (containsStraightFlush(cards)) {
            return new Hand(HandRank.STRAIGHT_FLUSH, handCards);
        }

        return null;
    }

    private List<Card> orderCards(List<Card> cards, CardOrder order) {
        for (Card card : cards) {
            card.setOrder(order);
        }
        Collections.sort(cards);
        return cards;
    }
    
    private String getRelevantSuit(List<Card> cards, int limit) {
//        Map suitCounterMap = new HashMap();
//        
//        for (String suit : Card.getSuits()) {
//            suitCounterMap.put(suit, 0);
//        }
//        
//        for (Card card : cards) {
//            for (Iterator it = suitCounterMap.keySet().iterator(); it.hasNext();) {
//                Object key = it.next();
//                if (suitCounterMap.get(key) == card.getSuit()) {
//                    suitCounterMap.put(key, suitCounterMap.get(key) + 1);
//                    break;
//                }
//            }
//        }
        
        int[] countSuits = {0, 0, 0, 0};
        String suit = null;
        
        for (Card card : cards) {
            switch (card.getSuit()) {
                case "clubs" : countSuits[0]++;
                    break;
                case "spades" : countSuits[1]++;
                    break;
                case "hearts" : countSuits[2]++;
                    break;
                case "diamonds" : countSuits[3]++;
                    break;
            }
        }
        for (int i = 0; i < countSuits.length; i++) {
            if (countSuits[i] >= limit) {
                suit = Card.getSuits()[i];
            }
        }
        
        return suit;
    }

    private boolean containsRoyalFlush(List<Card> cards) {
        String suit = getRelevantSuit(cards, 5);
        
        if (suit == null) {
            return false;
        }

        String[] ranks = {"10", "J", "Q", "K", "A"};
        List<Card> temp = new ArrayList<>();
        
        for (String rank : ranks) {
            for (Card card : cards) {
                if (card.getSuit().equals(suit) && card.getRank().equals(rank)) {
                    temp.add(card);
                    break;
                }
            }
        }
        if (temp.size() == 5) {
            handCards = temp;
            return true;
        }
        
        return false;
    }

    private boolean containsStraightFlush(List<Card> cards) {
        String suit = getRelevantSuit(cards, 5);
        
        if (suit == null) {
            return false;
        }
        
        List<Card> temp = new ArrayList<>();
        
        cards = orderCards(cards, CardOrder.BY_RANK);
        for (Card card : cards) {
            if (card.getSuit().equals(suit)) {
                temp.add(card);
            }
        }
        
        // !!! FÉLKÉSZ !!!
        
        return false;
    }

}
