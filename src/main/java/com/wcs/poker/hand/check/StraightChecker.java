/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.hand.check;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.hand.HandRank;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gábor
 */
public class StraightChecker implements HandChecker{

    private static final HandRank HAND_RANK=HandRank.STRAIGHT;
    Map<String, List<Card>> rankCounterMap = new HashMap();
    private List<Card> handCards = new LinkedList<>();
    
    @Override
    public boolean check(List<Card> cards) {
        for (String rank : Card.getAllRanks()) {
            rankCounterMap.put(rank, new LinkedList<Card>());
        }
        for (Iterator<Map.Entry<String, List<Card>>> iteratorMap = rankCounterMap.entrySet().iterator(); iteratorMap.hasNext();) {
            Map.Entry<String, List<Card>> nextMap = iteratorMap.next();
            for (Iterator<Card> iteratorCard = cards.iterator(); iteratorCard.hasNext();) {
                Card nextCard = iteratorCard.next();
                if(nextCard.isRank(nextMap.getKey())){
                    nextMap.getValue().add(nextCard);
                    //iteratorCard.remove();
                }
            }
        }
        rankCounterMap=sortByRank(rankCounterMap);
        for (Iterator<Map.Entry<String, List<Card>>> iterator = rankCounterMap.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<String, List<Card>> next = iterator.next();
            if(next.getValue().isEmpty()){
                handCards.clear();
            }else{
                handCards.add(next.getValue().get(0));
            }
            if(handCards.size()==5)
                return true;
        }
        if(handCards.size()==5)
            return true;
        else
            handCards.clear();
        return false;
    }

    private static Map<String, List<Card>> sortByRank(Map<String, List<Card>> unsortedRank) {
        List<Map.Entry<String, List<Card>>> list = new LinkedList<>(unsortedRank.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, List<Card>>>() {
            @Override
            public int compare(Map.Entry<String, List<Card>> o1, Map.Entry<String, List<Card>> o2) {
		return new RankComporatorDescending().compare(o1.getKey(),o2.getKey());
            }
        });
 
        list.add(list.get(0));
        
	Map<String, List<Card>> sortedMap = new LinkedHashMap<>();
	for (Iterator<Map.Entry<String, List<Card>>> it = list.iterator(); it.hasNext();) {
            Map.Entry<String, List<Card>> entry = it.next();
            if(!sortedMap.containsKey(entry.getKey())){
                sortedMap.put(entry.getKey(), entry.getValue());
            }else
            {
                sortedMap.put(entry.getKey()+"'", entry.getValue());
            }
	}
	return sortedMap;
    }
    
    @Override
    public List<Card> getResult() {
        return handCards;
    }

    @Override
    public HandRank getRank() {
        return HAND_RANK;
    }
    
}
