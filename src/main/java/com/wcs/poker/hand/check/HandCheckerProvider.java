/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.hand.check;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Timi
 */
public class HandCheckerProvider {
    private List<HandChecker> checkers = new ArrayList<>();

    public HandCheckerProvider() {
        checkers.add(new RoyalFlushChecker());
    }

    public List<HandChecker> getCheckers() {
        return checkers;
    }

    public void setCheckers(List<HandChecker> handCheckers) {
        this.checkers = handCheckers;
    }
}
