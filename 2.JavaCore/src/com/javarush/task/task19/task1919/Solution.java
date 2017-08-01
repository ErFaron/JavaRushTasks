package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
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
        printMap(treeMap);
    }

    public static void addToMap(String lastName, Double val) {

        if (treeMap.containsKey(lastName)) {
            treeMap.put(lastName, (treeMap.get(lastName)) + val);
        } else {
            treeMap.put(lastName, val);
        }
    }

    public static void printMap(TreeMap<String, Double> map) {
        for (Map.Entry<String, Double> pair : map.entrySet()) {
            System.out.println(pair.getKey() + " " + pair.getValue());
        }
    }
}