package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine(), file2 = reader.readLine();
        reader.close();

        BufferedReader inputReader = new BufferedReader(new FileReader(file1));
        BufferedWriter ouput = new BufferedWriter(new FileWriter(file2));
        while (inputReader.ready()) {
            ouput.write(inputReader.readLine().replaceAll("\\.", "!"));
        }
        inputReader.close();
        ouput.close();
    }
}
