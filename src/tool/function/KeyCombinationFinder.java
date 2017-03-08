package tool.function;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * find all Key combinations which give a final value (sum)
 *
 */
public class KeyCombinationFinder {

    private LinkedList<LinkedList<Integer>> outputList = new LinkedList();
    private LinkedList<String> outputListString = new LinkedList();
    
    private Integer[] data;
    private Integer keySize;
    private Integer maxDuplicatedKey;

    private Integer dataMin; // mininal value in data

    // set the debug flag
    public static final boolean debug = false;

    
    public KeyCombinationFinder(Integer[] data, int sum, int keySize, int maxDuplicate) {

        // remove values greater than the sum
        List<Integer> temp = new LinkedList();
        for (Integer d : data) {
            if (d <= sum) {
                temp.add(d);
            }
        }
        data = temp.toArray(new Integer[temp.size()]);

        // reverse sort
        Arrays.sort(data, Collections.reverseOrder());

        if (debug) {
            System.out.println(Arrays.toString(data));
        }

        this.keySize = keySize;
        this.maxDuplicatedKey = maxDuplicate;
        this.data = data;
        this.dataMin = data[data.length - 1];
    }

    public void startCalculate(int sum) {
        // initial values to start calculate
        calculate(sum, new LinkedList<>(), 0, 0, 0);
        // end of calculation
        System.out.println("finalResult.size(): " + outputList.size());
    }

    private void calculate(int sum, LinkedList<Integer> result, int index, int currentSize, int duplicate) {
        if (debug) {
            System.out.println("sum : " + sum + "  reslut : " + result.toString()
                    + " index : " + index + " currentSize : " + currentSize + " duplicate : " + duplicate);
        }

        if (duplicate > maxDuplicatedKey) {
            result = null;
        } else {
            if (currentSize < keySize - 1) {
                for (int i = index; i < data.length; i++) {
                    Integer v = data[i];
                    int newSum = sum - v;
                    if (newSum >= dataMin) {
                        LinkedList<Integer> newResult = new LinkedList<>(result);
                        newResult.add(v);
                        if (index == i) {
                            calculate(newSum, newResult, i, currentSize + 1, duplicate + 1);
                        } else {
                            calculate(newSum, newResult, i, currentSize + 1, 1);
                        }
                    }
                }
            } else {
                for (int i = index; i < data.length; i++) {
                    if (data[i] == sum) {
                        if (i == index && duplicate + 1 > maxDuplicatedKey) {
                            result = null;
                            break;
                        }
                        result.add(sum);
                        if (debug) {
                            System.out.println(result.toString());
                        }
                        outputList.add(result);
                        // outputListString.add(result.toString());
                        break;
                    }
                }
            }
        }

    }

    public LinkedList<LinkedList<Integer>> getOutputList() {
        return outputList;
    }

    public LinkedList<String> getOutputListString() {
        return outputListString;
    }

}
