/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.util.ArrayList;
import tool.function.ValueCombinationFinder;

/**
 *
 * @author emiewag
 */
public class MoreFunTest {
      public static void main(String[] args) {
        System.out.println(" MoreFun Test  ");
        
        
        ArrayList a1 = new ArrayList();
        a1.add(11);
        a1.add(12);
        a1.add(13);
        
        ArrayList a2 = new ArrayList();
        a2.add(21);
        a2.add(22);
        a2.add(23);
        
        ArrayList a3 = new ArrayList();
        a3.add(31);
        a3.add(32);
        a3.add(33);
        
        ArrayList al = new ArrayList();
        al.add(a1);
        al.add(a1);
        al.add(a2);

        
        ValueCombinationFinder moreFun = new ValueCombinationFinder(al);
        moreFun.startCalculate(true);
        moreFun.startCalculate(false);
    }
    
}
