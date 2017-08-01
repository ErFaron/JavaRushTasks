package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();

        BufferedReader input = new BufferedReader(new FileReader(file));

        while (input.ready()){
            StringBuffer buffer = new StringBuffer();
            buffer.append(input.readLine());
            System.out.println(buffer.reverse());
        }

        input.close();
    }
}
