package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine(), file2 = reader.readLine();
        reader.close();

        List<String> allLine = new ArrayList<>();
        BufferedReader input = new BufferedReader(new FileReader(file1));
        while (input.ready()) {
            allLine.add(input.readLine());
        }
        input.close();

        List<Integer> inlList = new ArrayList<>();
        for (String line : allLine) {
            String[] array = line.split(" ");

            for (int i = 0; i < array.length; i++) {
                try {
                    int x = Integer.parseInt(array[i]);
                    inlList.add(x);
                }catch (Exception e){}
            }
        }

        BufferedWriter output = new BufferedWriter(new FileWriter(file2));
        for( Integer oneInt : inlList){
            output.write(String.valueOf(oneInt));
            output.write(" ");
        }
        output.close();
    }
}
