package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        if (args[0].equals("-c")) {
            updateFile(fileName, args[1], args[2], args[3]);
        }
    }

    public static void updateFile(String filename, String productName, String price, String quantity) throws Exception {

        BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));

        List<String> lineList = new ArrayList<>();

        while (inputStream.ready()) {
            lineList.add(inputStream.readLine());
        }

        inputStream.close();

        String finalLine = lineList.get(lineList.size() - 1);

        int newId = 0;

        try {
            newId = Integer.parseInt(finalLine.substring(0, finalLine.indexOf(" "))) + 1;
        } catch (NumberFormatException e) { newId = Integer.parseInt(finalLine.substring(0,8)) + 1;
        }

        String newIdString = String.valueOf(newId);

        StringBuffer finalBuffer = new StringBuffer();

        finalBuffer.append(addSpaceToParam(newIdString, newIdString.length(), 8));
        finalBuffer.append(addSpaceToParam(productName, productName.length(), 30));
        finalBuffer.append(addSpaceToParam(price, price.length(), 8));
        finalBuffer.append(addSpaceToParam(quantity, quantity.length(), 4));

        lineList.add(finalBuffer.toString());

        FileOutputStream outputStream = new FileOutputStream(filename);

        for (String lineForWrite : lineList){
            outputStream.write(lineForWrite.getBytes());
            outputStream.write('\n');
        }

        //outputStream.write('\n');
        //outputStream.write(finalBuffer.toString().getBytes());

        outputStream.close();
    }

    public static String addSpaceToParam(String str, int stringLength, int maxStringLength) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(str);
        for (int i = 0; i < maxStringLength - stringLength; i++) {
            buffer.append(" ");
        }
        return buffer.toString();
    }
}
