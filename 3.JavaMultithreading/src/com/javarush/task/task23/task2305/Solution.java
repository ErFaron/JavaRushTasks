package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {

        Solution[] arrOfSoll = new Solution[2];
        arrOfSoll[0] = new Solution();
        arrOfSoll[1] = new Solution();

        Solution solution = new Solution();
        arrOfSoll[0].innerClasses[0] = solution.new InnerClass();
        arrOfSoll[0].innerClasses[1] = solution.new InnerClass();
        arrOfSoll[1].innerClasses[0] = solution.new InnerClass();
        arrOfSoll[1].innerClasses[1] = solution.new InnerClass();

        return arrOfSoll;
    }

    public static void main(String[] args) {

    }
}
