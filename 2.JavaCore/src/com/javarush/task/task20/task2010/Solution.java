package com.javarush.task.task20.task2010;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
Как сериализовать что-то свое?
*/
public class Solution {
    public static class Object implements Serializable{
        public String string1;
        public String string2;
    }

    public static int countStrings;

    public static void serializableStatic(ObjectOutputStream objectOutputStream) throws Exception{
        objectOutputStream.write(countStrings);
    }

    public static void deserializableStatic(ObjectInputStream objectInputStream) throws Exception{
        countStrings = objectInputStream.read();
    }

    public static class String implements Serializable {
        private final int number;

        public String() {
            number = ++countStrings;
        }

        public void print() {
            System.out.println("string #" + number);
        }
    }

    public static void main(String[] args) {
    }
}
