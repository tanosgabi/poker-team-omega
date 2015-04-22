
package com.wcs.poker.gamestate;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import java.util.List;
import java.util.Objects;

@Generated("org.jsonschema2pojo")
public class Card implements Comparable<Card> {

    public static final String[] ranks={"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    public static final String[] suits={"clubs","spades","hearts","diamonds"};
    
    @Expose
    private String rank;
    @Expose
    private String suit;
    
    private CardOrder order;

    public Card() {
    }
 
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }
    
    public boolean isSuitEqual(Card other){
        if (other.getSuit().equals(this.suit)) {
            return true;
        }
        return false;
    }
    
    public boolean isSuit(String suit) {
        if (suit.equals(this.suit)) {
            return true;
        }
        return false;
    }
    
    public boolean isRankEqual(Card other) {
        if (other.getRank().equals(this.rank)) {
            return true;
        }
        return false;
    }
    
    public boolean isRank(String rank) {
        if (rank.equals(this.rank)) {
            return true;
        }
        return false;
    }
    
    public boolean rankMatches(String regexp) {
        if (rank.matches(regexp)) {
            return true;
        }
        return false;
    }

    /**
     * 
     * @return
     *     The rank
     */
    public String getRank() {
        return rank;
    }

    /**
     * 
     * @param rank
     *     The rank
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * 
     * @return
     *     The suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     * 
     * @param suit
     *     The suit
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

    public static String[] getSequence() {
        return ranks;
    }

    public static String[] getSuits() {
        return suits;
    }

    public CardOrder getOrder() {
        return order;
    }

    public void setOrder(CardOrder order) {
        this.order = order;
    }

    @Override
    public int compareTo(Card o) {
        switch (order) {
            case BY_RANK: {
                if ("A".equals(this.rank)) {
                    return -10;
                }
                if ("K".equals(this.rank) && !"A".equals(o.getRank())) {
                    return -10;
                }
                return this.rank.compareTo(o.getRank());
            }
            case BY_SUIT: return this.suit.compareTo(o.getSuit());
            default: return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (!this.isRank(other.rank)) {
            return false;
        }
        if (!this.isSuit(other.suit)) {
            return false;
        }
        return true;
    }
    
    
    
    public static boolean SameCardList(List<Card> card1,List<Card> card2)
    {
        if(card1.size()!=card2.size())
            return false;
        boolean is;
        for (Card cards1 : card1) {
            is=false;
            for (Card cards2 : card2) {
                if(cards2.isRankEqual(cards1) && cards2.isSuitEqual(cards1))
                    is=true;
            }
            if(!is)
                return false;
        }
        return true;
    }
}
