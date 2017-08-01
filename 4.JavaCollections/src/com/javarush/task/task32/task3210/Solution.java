package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws Exception{
        RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
        long number = Long.parseLong(args[1]); //позиция в файле
        raf.seek(number);
        byte[] buffer = new byte[args[2].length()];
        raf.read(buffer, 0, buffer.length);
        String resText = new String(buffer);
        raf.seek(raf.length());
        if(resText.equals(args[2])){
            raf.write("true".getBytes());
        }else{
            raf.write("false".getBytes());
        }
    }
}
