package com.javarush.task.task19.task1907;

/*
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();

        StringBuffer strBuffer = new StringBuffer();
        FileReader input = new FileReader(file);
        while (input.ready()) {
            strBuffer.append((char) input.read());
        }
        input.close();
        String finalSLine = strBuffer.toString();
        int count = 0;
        String array[] = finalSLine.split("\\p{P}|[\\t\\n\\r\\s]");
        for (int i = 0; i < array.length; i++) {
            String test = array[i];
            if (test.equals("world")) {
                count++;
            }
        }
        System.out.println(count);
    }
}