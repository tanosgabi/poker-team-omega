package com.wcs.poker.hand;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.CardOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        
        Hand hand = null;

        if (isRoyalFlush(cards)) {
            hand = new Hand(HandRank.ROYAL_FLUSH, handCards);
        }

        return hand;
    }

    private List<Card> orderCards(List<Card> cards, CardOrder order) {
        for (Card card : cards) {
            card.setOrder(order);
        }
        Object[] cardArray = cards.toArray();
        Arrays.sort(cardArray);
        cards.clear();
        for (Object card : cardArray) {
            cards.add((Card) card);
        }
        return cards;
    }

    private boolean isRoyalFlush(List<Card> cards) {
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
            if (countSuits[i] >= 5) {
                suit = Card.getSuits()[i];
            }
        }
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

}
