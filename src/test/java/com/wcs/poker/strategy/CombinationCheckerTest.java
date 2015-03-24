/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.strategy;

import com.wcs.poker.gamestate.Card;
import java.util.ArrayList;
import java.util.List;
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
    
    CombinationChecker checker;
    Card card1;
    Card card2;
    List<Card> cards;
    HandRank expResult;
    HandRank result;
    
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
        checker = new CombinationChecker();
        card1 = new Card();
        card2 = new Card();
        cards=new ArrayList<>();
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

    /**
     * Test of countAndSet method, of class CombinationChecker.
     */
    /*@Test
    public void testCountAndSet() {
        System.out.println("countAndSet");
        List<Card> cards = null;
        CombinationChecker instance = new CombinationChecker();
        instance.countAndSet(cards);
        
    }*/

    /**
     * Test of getHandRank method, of class CombinationChecker.
     */
    @Test
    public void testGetHandRankIsNull() {
        System.out.println("getHandRank");
        expResult = null;
        result = checker.getHandRank();
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testGetHandRankIsHighCard() {
        cards.add(new Card("5", "clubs"));
        cards.add(new Card("6", "clubs"));
        checker.countAndSet(cards);
        expResult = HandRank.highCard;
        result = checker.getHandRank();
        assertEquals(expResult, result);
    }
    @Test
    public void testGetHandRankIsPair() {
        cards.add(new Card("5", "clubs"));
        cards.add(new Card("6", "clubs"));
        cards.add(new Card("6", "spades"));
        checker.countAndSet(cards);
        expResult = HandRank.pair;
        result = checker.getHandRank();
        assertEquals(expResult, result);
    }
    @Test
    public void testGetHandRankIsTwoPair() {
        cards.add(new Card("5", "clubs"));
        cards.add(new Card("6", "clubs"));
        cards.add(new Card("5", "spades"));
        cards.add(new Card("6", "spades"));
        checker.countAndSet(cards);
        expResult = HandRank.twoPair;
        result = checker.getHandRank();
        assertEquals(expResult, result);
    }
    @Test
    public void testGetHandRankIsDrill() {
        cards.add(new Card("5", "clubs"));
        cards.add(new Card("6", "clubs"));
        cards.add(new Card("5", "spades"));
        cards.add(new Card("5", "hearts"));
        checker.countAndSet(cards);
        expResult = HandRank.drill;
        result = checker.getHandRank();
        assertEquals(expResult, result);
    }
    @Test
    public void testGetHandRankIsStraight() {
        cards.add(new Card("5", "clubs"));
        cards.add(new Card("6", "clubs"));
        cards.add(new Card("7", "spades"));
        cards.add(new Card("8", "hearts"));
        cards.add(new Card("9", "hearts"));
        checker.countAndSet(cards);
        expResult = HandRank.straight;
        result = checker.getHandRank();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetHandRankIsFlush() {
        cards.add(new Card("5", "spades"));
        cards.add(new Card("6", "spades"));
        cards.add(new Card("2", "spades"));
        cards.add(new Card("8", "spades"));
        cards.add(new Card("10", "spades"));
        checker.countAndSet(cards);
        expResult = HandRank.flush;
        result = checker.getHandRank();
        assertEquals(expResult, result);
    }
    @Test
    public void testGetHandRankIsFullHouse() {
        cards.add(new Card("5", "clubs"));
        cards.add(new Card("5", "spades"));
        cards.add(new Card("5", "hearts"));
        cards.add(new Card("10", "spades"));
        cards.add(new Card("10", "hearts"));
        checker.countAndSet(cards);
        expResult = HandRank.fullHouse;
        result = checker.getHandRank();
        assertEquals(expResult, result);
    }
    @Test
    public void testGetHandRankIsPoker() {
        cards.add(new Card("5", "clubs"));
        cards.add(new Card("5", "spades"));
        cards.add(new Card("5", "hearts"));
        cards.add(new Card("5", "diamonds"));
        cards.add(new Card("10", "hearts"));
        checker.countAndSet(cards);
        expResult = HandRank.poker;
        result = checker.getHandRank();
        assertEquals(expResult, result);
    }
    @Test
    public void testGetHandRankIsStraightFlush() {
        cards.add(new Card("5", "diamonds"));
        cards.add(new Card("6", "diamonds"));
        cards.add(new Card("7", "diamonds"));
        cards.add(new Card("8", "diamonds"));
        cards.add(new Card("9", "diamonds"));
        checker.countAndSet(cards);
        expResult = HandRank.straightFlush;
        result = checker.getHandRank();
        assertEquals(expResult, result);
    }
}
