package com.javarush.task.task30.task3004;

import java.util.concurrent.ForkJoinPool;

/* 
Fork/Join
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String result1 = BinaryRepresentationTask.binaryRepresentationMethod(6);
        System.out.println(result1);

        System.out.println();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        String result2 = forkJoinPool.invoke(new BinaryRepresentationTask(6));
        System.out.println(result2);
    }

}
