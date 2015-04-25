package com.wcs.poker.hand;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.hand.check.HandChecker;
import com.wcs.poker.hand.check.HandCheckerProvider;
import java.util.List;

/**
 *
 * @author thomas
 */
public class HandRankingService {

    public Hand evaulate(List<Card> cards) {
        if (cards.size() >= 8 || cards.size() <= 4) {
            throw new IllegalArgumentException("");
        }

        HandCheckerProvider handCheckerProvider = new HandCheckerProvider();
        
        for (HandChecker handChecker : handCheckerProvider.getCheckers()) {
            if (handChecker.check(cards)) {
                return new Hand(handChecker.getRank(), handChecker.getResult());
            }
        }

        return null;
    }
}
