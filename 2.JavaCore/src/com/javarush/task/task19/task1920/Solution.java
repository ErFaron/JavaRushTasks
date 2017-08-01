package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Solution {

    static TreeMap<String, Double> treeMap = new TreeMap<>();

    public static void main(String[] args) throws Exception {

        BufferedReader input = new BufferedReader(new FileReader(args[0]));

        while (input.ready()) {
            String oneLine = input.readLine();
            String[] array = oneLine.split(" ");


            for (int i = 0; i < array.length; i++) {
                String lastName = null;
                Double val = 0.0;
                if ((i % 2) == 0) {
                    lastName = array[i];
                    val = Double.parseDouble(array[i + 1]);
                    addToMap(lastName, val);
                }
            }
        }

        input.close();

        double maxValue = findMaxValue(treeMap);

        printResult(treeMap, maxValue);
    }

    public static void addToMap(String lastName, Double val) {

        if (treeMap.containsKey(lastName)) {
            treeMap.put(lastName, (treeMap.get(lastName)) + val);
        } else {
            treeMap.put(lastName, val);
        }
    }

    public static void printResult(TreeMap<String, Double> map, Double maxValue) {
        for (Map.Entry<String, Double> pair : map.entrySet()) {
            if (pair.getValue().equals(maxValue)) {
                System.out.println(pair.getKey());
            }
        }
    }

    public static double findMaxValue(TreeMap<String, Double> mapForFind) {
        double tempMax = 0.0;
        for (Map.Entry<String, Double> pair : mapForFind.entrySet()) {
            if (pair.getValue() > tempMax) {
                tempMax = pair.getValue();
            }
        }
        return tempMax;
    }
}