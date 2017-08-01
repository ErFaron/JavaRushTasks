package com.javarush.task.task20.task2017;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* 
Десериализация
*/
public class Solution {
    public A getOriginalObject(ObjectInputStream objectStream) throws IOException {
        A a = null;
        Object x = null;
        try {
            x = objectStream.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return null;
        } finally {
            if (x instanceof A) {
                a = (A) x;
                return a;
            } else {
                return null;
            }
        }
    }

    public class A implements Serializable {
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) {

    }
}
