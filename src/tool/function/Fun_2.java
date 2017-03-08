package tool.function;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class Fun_2 {

    private LinkedList<String> finalResult = new LinkedList();
    private Integer[] data;
    private HashSet<Integer> dataHS;
    private Integer min;
    private static final boolean debug = false;

    public Fun_2(Integer[] data, int sum) {

        // remove values greater than sum
        List<Integer> temp = new LinkedList();
        for (Integer d : data) {
            if (d <= sum) {
                temp.add(d);
            }
        }
        data = temp.toArray(new Integer[temp.size()]);

        Arrays.sort(data, Collections.reverseOrder());

        if (debug) {
            System.out.println(Arrays.toString(data));
        }

        this.data = data;
        this.dataHS = new HashSet<>(Arrays.asList(data));
        this.min = data[data.length - 1];
    }

    public void startCalculate(int sum, int num) {
        calculate(sum, "", num);
        System.out.println("finalResult.size(): " + finalResult.size());
    }

    public void calculate(int sum, String result, int num) {
        //System.out.println("sum : " + sum + "  reslut : " + result.toString() + " num : " + num);
        if (num > 1) {
            num = num - 1;
            for (Integer v : data) {
                int newSum = sum - v;
                if (newSum >= min) {
                    calculate(newSum, result + ", " + v, num);
                }
            }
        } else if (dataHS.contains(sum)) {
            result = result + ", " + sum;
            //System.out.println(result.toString());
            finalResult.add(result);
        } else {
            result = null;
        }

    }

    public LinkedList<String> getFinalResult() {
        return finalResult;
    }

}
