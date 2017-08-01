package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Solution {
    public static void main(String[] args) throws Exception {
        if (args[0].equals("-e")){encrypt(args[1], args[2]);}
        if (args[0].equals("-d")){decrypt(args[1], args[2]);}
    }

    public static void encrypt (String inputFile, String outputFile) throws Exception{
        FileInputStream inputStream = new FileInputStream(inputFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        while (inputStream.available()>0){
            int data = inputStream.read() + 1;
            outputStream.write(data);
        }
        inputStream.close();
        outputStream.close();
    }

    public static void decrypt(String inputFile, String outputFile) throws Exception{
        FileInputStream inputStream = new FileInputStream(inputFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        while (inputStream.available()>0){
            int data = inputStream.read() - 1;
            outputStream.write(data);
        }
        inputStream.close();
        outputStream.close();
    }
}
