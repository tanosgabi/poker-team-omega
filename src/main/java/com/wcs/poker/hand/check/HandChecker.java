/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wcs.poker.hand.check;

import com.wcs.poker.gamestate.Card;
import java.util.List;

/**
 *
 * @author poker08
 */
public interface HandChecker {
    boolean check(List<Card> cards);
    List<Card> getResult();
}
