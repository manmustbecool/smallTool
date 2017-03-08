/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool.function;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * find all value combinations based on a given set of key
 * 
 */
public class ValueCombinationFinder {

    ArrayList<ArrayList<Integer>> inputList;
    HashSet<HashSet> outputList; // only will be used for without duplicate
    ArrayList<String> outputListString;
    
    public static final boolean debug = false;

    public ValueCombinationFinder(ArrayList<ArrayList<Integer>> al) {
        this.inputList = al;
        this.outputList = new HashSet<>();
        this.outputListString = new ArrayList<>();
    }

    public void startCalculate(boolean duplicate) {
        if (duplicate) {
            calculateWduplicate(0, new LinkedList<>());
        } else {
            calculateWOutDuplicate(0, new LinkedHashSet<>());
        }

    }

    private void calculateWOutDuplicate(int index, HashSet<Integer> s) {
        for (Integer a : inputList.get(index)) {
            if (!s.contains(a)) {
                HashSet<Integer> temp = new LinkedHashSet<>(s);
                temp.add(a);
                if (index == inputList.size() - 1) {
                    if (!outputList.contains(temp)) {
                        outputList.add(temp);
                        String r = temp.toString();
                        // System.out.println(r);
                        outputListString.add(r);
                    }
                } else {
                    calculateWOutDuplicate(index + 1, temp);
                }
            }
        }
    }

    private void calculateWduplicate(int index, LinkedList<Integer> s) {
        for (Integer a : inputList.get(index)) {
            LinkedList<Integer> temp = new LinkedList<>(s);
            temp.add(a);
            if (index == inputList.size() - 1) {
                String r = temp.toString();
                // System.out.println(r);
                outputListString.add(r);
            } else {
                calculateWduplicate(index + 1, temp);
            }

        }
    }

    public ArrayList<String> getResList() {
        return outputListString;
    }

}
