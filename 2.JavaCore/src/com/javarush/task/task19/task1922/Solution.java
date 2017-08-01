package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();

        List<String> list = new ArrayList<>();
        BufferedReader input = new BufferedReader(new FileReader(file));
        while (input.ready()) {
            list.add(input.readLine());
        }
        input.close();

        for (String line : list) {
            String[] array = line.split(" ");
            int count = 0;
            for (int i = 0; i < words.size(); i++) {
                for (int j = 0; j < array.length; j++) {
                    if (array[j].equals(words.get(i))) {
                        count++;
                    }
                }
            }
            if (count == 2) {
                System.out.println(line);
            }
        }
    }
}
