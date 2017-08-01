package com.javarush.task.task18.task1825;

/* 
Собираем файл
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> fileList = new ArrayList<>();
        String outputFile = "";

        while (true) {
            String fileName = reader.readLine();
            if (fileName.equals("end")) {
                break;
            }
            fileList.add(fileName);
            outputFile = fileName.substring(0, fileName.indexOf(".part"));
        }

        Collections.sort(fileList);

        FileOutputStream outputStream = new FileOutputStream(outputFile, true);

        for (String fileForInput : fileList) {
            BufferedReader inputStreamBufferedReader = new BufferedReader(new FileReader(fileForInput));
            while (inputStreamBufferedReader.ready()) {
                int data = inputStreamBufferedReader.read();
                outputStream.write(data);
            }
            inputStreamBufferedReader.close();
        }
        reader.close();
        outputStream.close();
    }
}

