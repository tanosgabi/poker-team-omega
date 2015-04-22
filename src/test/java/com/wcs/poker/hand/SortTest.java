/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.hand;

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
 * @author Gábor
 */
public class SortTest {
    List<Card> cards;
    
    public SortTest() {
        cards=new ArrayList<>();
        cards.add(new Card("8","clubs"));
        cards.add(new Card("A","hearts"));
        cards.add(new Card("K","hearts"));
        cards.add(new Card("10","hearts"));
        cards.add(new Card("A","clubs"));
        cards.add(new Card("J","hearts"));
        cards.add(new Card("Q","hearts"));
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

    /**
     * Test of SortingbySuitList method, of class Sort.
     */
    @Test
    public void testSortingbySuitList() {
        List<Card> cards2=new ArrayList<>();
        cards2.add(new Card("A","hearts"));
        cards2.add(new Card("K","hearts"));
        cards2.add(new Card("Q","hearts"));
        cards2.add(new Card("J","hearts"));
        cards2.add(new Card("10","hearts"));
        cards2.add(new Card("A","clubs"));
        cards2.add(new Card("8","clubs"));
        System.out.println("SortingbySuitList");
        Sort instance = new Sort();
        List<Card> expResult = cards2;
        List<Card> result = instance.SortingbySuitList(cards);
        int jani=5;
        assertEquals(expResult, result);
    }

    /**
     * Test of SortingbyRankList method, of class Sort.
     */
//    @Test
//    public void testSortingbyRankList() {
//        System.out.println("SortingbyRankList");
//        List<Card> cards = null;
//        Sort instance = new Sort();
//        List<Card> expResult = null;
//        List<Card> result = instance.SortingbyRankList(cards);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
