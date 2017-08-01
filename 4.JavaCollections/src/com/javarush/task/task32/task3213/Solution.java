package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader, -3));  //Hello Amigo
    }

    public static String decode(StringReader reader, int key) throws IOException {

        if (reader == null) {
            return "";
        }

        BufferedReader br = new BufferedReader(reader);
        String s = br.readLine();

        char[] line = s.toCharArray();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < line.length; i++) {
            char z = (char) (line[i] + key);
            sb.append(z);
        }
        return sb.toString();
    }
}
