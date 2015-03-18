/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.strategy;

import com.wcs.poker.gamestate.Card;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Timi
 */
public class CombinationCheckerTest {
    
    CombinationChecker checker = new CombinationChecker();
    Card card1 = new Card();
    Card card2 = new Card();
    
    public CombinationCheckerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testIsBigPair() {
        card1.setRank("Q");
        card2.setRank("Q");
        assertTrue(checker.isBigPair(card1, card2));
        card1.setRank("J");
        assertFalse(checker.isBigPair(card1, card2));
        card1.setRank("4");
        card2.setRank("4");
        assertFalse(checker.isBigPair(card1, card2));
    }

    @Test
    public void testIsAceAndQueen() {
        card1.setRank("A");
        card2.setRank("Q");
        assertTrue(checker.isAceAndQueen(card1, card2));
        card1.setRank("Q");
        assertFalse(checker.isAceAndQueen(card1, card2));
    }

    @Test
    public void testIsSmallPair() {
        card1.setRank("4");
        card2.setRank("4");
        assertTrue(checker.isSmallPair(card1, card2));
        card1.setRank("3");
        assertFalse(checker.isSmallPair(card1, card2));
        card1.setRank("10");
        card2.setRank("10");
        assertFalse(checker.isSmallPair(card1, card2));
    }
    
}
