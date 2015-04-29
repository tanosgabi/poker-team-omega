/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.strategy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wcs.poker.gamestate.Card;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Timi
 */
public class CombinationCheckerTest {
    
    CombinationChecker checker;
    Card card1;
    Card card2;

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
     * Test of turnOdds method, of class CombinationChecker.
     */
//    @Test
//    public void testTurnOddsWeCantWin() throws IOException {
//        double expResult = 0.0;
//        double result = checker.turnOdds(loadCards("turnWeCantWin.json"));
//        //System.out.println(result);
//        assertEquals(expResult, result, 0.0);
//    }
//
//    @Test
//    public void testTurnOddsOneCardAndWeHaveFull() throws IOException {
//        double expResult = 4.0/46;
//        double result = checker.turnOdds(loadCards("turnWeHaveCanFull.json"));
//        //System.out.println("weHaveFull: "+result);
//        assertEquals(expResult, result, 0.0001);
//    }
//    @Test
//    public void testTurnOddsWeWin() throws IOException {
//        double expResult = 1.0;
//        double result = checker.turnOdds(loadCards("turnWeWin.json"));
//        //System.out.println("weWin: "+result);
//        assertEquals(expResult, result, 0.0);
//    }
//    /**
//     * Test of flopOdds method, of class CombinationChecker.
//     */
//    @Test
//    public void testFlopOddsWeCantWin() throws IOException {
//        double expResult = 0.0;
//        double result = checker.flopOdds(loadCards("flopWeCantWin.json"));
//        assertEquals(expResult, result, 0.0);
//    }
//    @Test
//    public void testFlopOddsWeWin() throws IOException {
//        double expResult = 1.0;
//        double result = checker.flopOdds(loadCards("flopWeWin.json"));
//        assertEquals(expResult, result, 0.0);
//    }
//    @Test
//    public void testFlopOddsWeCanWin() throws IOException {
//        double expResult = 5.0*9/(47*46/2);
//        double result = checker.flopOdds(loadCards("flopWeCanWin.json"));
//        System.out.println("weCanWin: "+result+"  "+expResult);
//        assertEquals(expResult, result, 0.0);
//    }
    
    private List<Card> loadCards(String name) throws IOException {
        InputStream resource = getClass().getResourceAsStream(name);
        String json = IOUtils.toString(resource);
        
        Type cardListType = new TypeToken<List<Card>>(){}.getType();
        return new Gson().fromJson(json, cardListType);
    }
}
