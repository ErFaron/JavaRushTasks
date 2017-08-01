package com.javarush.task.task19.task1924;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();

        List<String> list = new ArrayList<>();
        BufferedReader input = new BufferedReader(new FileReader(file));
        while (input.ready()) {
            String oneLine = input.readLine();
            String[] array = oneLine.split(" ");
            List<String> tempList = new ArrayList<>();
            for (int i = 0; i < array.length; i++) {
                if (array[i].matches("\\d+") && Integer.parseInt(array[i]) >= 0 &&
                        Integer.parseInt(array[i]) < 13) {
                    tempList.add(map.get(Integer.parseInt(array[i])));
                } else {
                    tempList.add(array[i]);
                }
            }
            for (String lineForPrint : tempList) {
                System.out.print(lineForPrint + " ");
            }
            System.out.println();
        }
        input.close();
    }
}
