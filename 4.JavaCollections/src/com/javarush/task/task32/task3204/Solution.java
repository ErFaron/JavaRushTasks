package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Random random = new Random();
        int randomDigits = random.ints(48, 57).
                limit(1).findFirst().getAsInt();
        int randomToUpperCase = random.ints(65, 90).
                limit(1).findFirst().getAsInt();
        int randomToLowerCase = random.ints(97, 122).
                limit(1).findFirst().getAsInt();
        byteArrayOutputStream.write((char) (randomDigits));
        byteArrayOutputStream.write((char) (randomToUpperCase));
        byteArrayOutputStream.write((char) (randomToLowerCase));
        for (int i = 0; i < 5; i++) {
            int ran = random.ints(1, 4).limit(1).findFirst().getAsInt();
            char ch = ' ';
            switch (ran) {
                case 1:
                    ch = (char) (randomDigits);
                    break;
                case 2:
                    ch = (char) (randomToLowerCase);
                    break;
                case 3:
                    ch = (char) (randomToUpperCase);
                    break;
            }
            byteArrayOutputStream.write(ch);
        }
        return byteArrayOutputStream;
    }
}