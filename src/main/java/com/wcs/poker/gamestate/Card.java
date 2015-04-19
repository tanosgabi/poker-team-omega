
package com.wcs.poker.gamestate;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Card implements Comparable<Card> {

    private static final String[] ranks={"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    private static final String[] suits={"clubs","spades","hearts","diamonds"};
    
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
    

}
