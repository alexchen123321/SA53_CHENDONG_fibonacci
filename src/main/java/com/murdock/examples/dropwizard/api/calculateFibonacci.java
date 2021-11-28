package com.murdock.examples.dropwizard.api;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;

public class calculateFibonacci {
    private int n;
    private int[] cacheArr;
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }


    public  calculateFibonacci(int n)
    {
        this.n = n;
        System.out.println(n);

    }

    public int[] FibonacciArr(){
//        System.out.println("fibonacciarr:" + n);
          int[] fib = new int[n];
//        System.out.println("fibonacciarr len:" + fib.length);
         if(n == 0){
             fib[0] =0;
             return fib ;
         }
         if(n == 1) {
             fib[0] =0;
             fib[1] =1;
         }
        fib[0] =0;
        fib[1] =1;
         for(int i = 2; i < n ;i++){
             fib[i] =  fib[i-1] + fib[i-2];
         }
         this.cacheArr = fib;
        return fib;
    }

    public int[] SortedFibArr(){
        int[] sortedArr = new int[cacheArr.length];
        ArrayUtils.reverse(cacheArr);
        int count = 0;
        for(int i= 0; i < cacheArr.length;i++){
            if(cacheArr[i] %2 ==0 ) {
                sortedArr[count] = cacheArr[i];
                cacheArr[i] = Integer.MIN_VALUE;
                count++;
            }
        }
        for(int i = 0 ; i< n ; i++){
            if(cacheArr[i] %2 == 1 && cacheArr[i] > 0){
                sortedArr[count] = cacheArr[i];
                count++;
            }
        }
//        System.out.println("sort arr reverse:" + Arrays.toString(sortedArr));
        return sortedArr;
    }


}


