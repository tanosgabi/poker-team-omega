package com.wcs.poker.hand;

import com.wcs.poker.gamestate.Card;
import java.util.List;

/**
 *
 * @author thomas
 */
public class HandRankingService {

    public Hand evaulate(List<Card> loadCards) {
        if(loadCards.size()>=8 ||loadCards.size()<=4)
            throw new IllegalArgumentException("");
        return null;
    }
    
    
}
