package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader input = new BufferedReader(new FileReader(args[0]));
        BufferedWriter output = new BufferedWriter(new FileWriter(args[1]));

        StringBuffer buffer = new StringBuffer();
        while (input.ready()) {
            String[] array = input.readLine().split(" ");
            for (int i = 0; i < array.length; i++) {
                if (array[i].length() > 6) {
                    buffer.append(array[i] + ",");
                }
            }
        }
        String finalLine;
        if (buffer.toString().length() > 0) {
            finalLine = buffer.toString().substring(0, buffer.toString().length() - 1);
            output.write(finalLine);
        }
        input.close();
        output.close();
    }
}
