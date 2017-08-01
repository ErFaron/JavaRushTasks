package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine(), file2 = reader.readLine();
        reader.close();

        BufferedReader input = new BufferedReader(new FileReader(file1));
        BufferedWriter output = new BufferedWriter(new FileWriter(file2));
        while (input.ready()){
            output.write(input.readLine().replaceAll("\\p{Punct}|[\n]",""));
        }
        input.close();
        output.close();
    }
}
