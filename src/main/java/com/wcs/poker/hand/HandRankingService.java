package com.wcs.poker.hand;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.CardOrder;
import com.wcs.poker.hand.check.RoyalFlushChecker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thomas
 */
public class HandRankingService {

    private List<Card> handCards = new LinkedList<>();

    public Hand evaulate(List<Card> cards) {
        if (cards.size() >= 8 || cards.size() <= 4) {
            throw new IllegalArgumentException("");
        }
        
        RoyalFlushChecker royalFlushChecker = new RoyalFlushChecker();

        if (royalFlushChecker.check(cards)) {
            return new Hand(HandRank.ROYAL_FLUSH, royalFlushChecker.getResult());
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
        Map<String, Integer> suitCounterMap = new HashMap();
        for (Card card : cards) {
            final String suit = card.getSuit();
            Integer count = suitCounterMap.get(suit);
            if (count == null) {
                count = 1;
            } else {
                count += 1;
            }
            suitCounterMap.put(suit, count);
        }

        for (Map.Entry<String, Integer> entry : suitCounterMap.entrySet()) {
            if (entry.getValue() >= limit) {
                return entry.getKey();
            }
        }

        return null;

//        Map<String, Integer> suitCounterMap = new HashMap();
//        
//        for (String suit : Card.getSuits()) {
//            suitCounterMap.put(suit, 0);
//        }
//        
//        for (Card card : cards) {
//            Iterator<String> iterator = suitCounterMap.keySet().iterator();
//            while (iterator.hasNext()) {
//                String next = iterator.next();
//                if (next == card.getSuit()) {
//                    suitCounterMap.put(next, suitCounterMap.get(next) + 1);
//                    break;
//                }
//            }
//        }
//        
//        int[] countSuits = {0, 0, 0, 0};
//        String suit = null;
//        
//        for (Card card : cards) {
//            switch (card.getSuit()) {
//                case "clubs" : countSuits[0]++;
//                    break;
//                case "spades" : countSuits[1]++;
//                    break;
//                case "hearts" : countSuits[2]++;
//                    break;
//                case "diamonds" : countSuits[3]++;
//                    break;
//            }
//        }
//        for (int i = 0; i < countSuits.length; i++) {
//            if (countSuits[i] >= limit) {
//                suit = Card.getSuits()[i];
//            }
//        }
//        
//        return suit;
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
