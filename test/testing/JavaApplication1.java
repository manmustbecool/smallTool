package testing;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import tool.model.Fun;
import static tool.model.Fun.*;
import tool.model.Fun_2;

/**
 *
 * @author emiewag
 */
public class JavaApplication1 {

    private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

    public static void main(String[] args) {
        System.out.println(" FUN  ");
        test2();
    }
    
    
     public static void test2() {    
        Integer[] data = new Integer[3];
        data[0] = 3;
        data[1] = 2;
        data[2] = 1;
        runTest(data);

    }

    public static void test1() {    
        Integer[] data = new Integer[6];
        data[0] = 6;
        data[1] = 3;
        data[2] = 4;
        data[3] = 2;
        data[4] = 5;
        data[5] = 1;
        runTest(data);

    }
        
    public static void runTest(Integer[] data){  
        System.out.println("test data: " + Arrays.toString(data));
        long startTime = System.currentTimeMillis();

        int loop = 1;
        for (int i = 0; i < loop; i++) {
            int sum = 5;
            int size = 2;
            int duplicate = 2;
            Fun fun = new Fun(data, sum, size, duplicate);
            fun.startCalculate(sum);
//Fun_2 fun = new Fun_2(data,sum);
//            fun.startCalculate(sum, num);
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Used Time is Millis: " + elapsedTime / loop);

        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: " + bytesToMegabytes(memory));

    }
}
