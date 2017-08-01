package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        BufferedReader input = new BufferedReader(new FileReader(args[0]));
        while (input.ready()) {
            list.add(input.readLine());
        }
        input.close();

        BufferedWriter output = new BufferedWriter(new FileWriter(args[1]));
        for (String line : list) {
            String[] words = line.split(" ");
            for (int i = 0; i < words.length; i++) {
                if (!words[i].matches("^\\D*$")) {
                    output.write(words[i] + " ");
                }
            }
        }
        output.close();
    }
}
