package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();

        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outpotStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outpotStream);
        System.setOut(stream);

        testString.printSomething();

        System.setOut(consoleStream);

        String result = outpotStream.toString();

        writeToFile(filename, result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }

    public static void writeToFile(String filename, String result) throws Exception{
        FileOutputStream output = new FileOutputStream(filename);
        output.write(result.getBytes());
        output.close();
        System.out.println(result);
    }
}

