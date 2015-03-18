
package com.wcs.poker.gamestate;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Card {

    @Expose
    private String rank;
    @Expose
    private String suit;
    
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

}
