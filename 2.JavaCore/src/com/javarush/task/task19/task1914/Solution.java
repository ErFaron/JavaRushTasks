package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        testString.printSomething();
        System.setOut(consoleStream);
        String result = outputStream.toString();
        System.out.print(res(result));
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("8 + 5 = ");
        }
    }

    public static String res(String param){
        String[] array = param.split(" ");
        int intA = Integer.parseInt(array[0]);
        int intB = Integer.parseInt(array[2]);
        String result = null;
        switch (array[1]){
            case "+": result = String.valueOf(intA + intB);break;
            case "-": result = String.valueOf(intA - intB);break;
            case "*": result = String.valueOf(intA * intB);break;
        }
        String all = array[0] + " " + array[1] + " " + array[2] + " " + array[3] + " " + result;
        return all;
    }
}

