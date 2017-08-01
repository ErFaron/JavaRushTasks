package com.javarush.task.task18.task1828;

/* 
Прайсы 2
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

        if (args[0].equals("-d")) {
            deleteLineFromFile(fileName, args[1]);
        } else if (args[0].equals("-u")) {
            updateFile(fileName, args[1], args[2], args[3], args[4]);
        }
    }

    public static void updateFile(String filename, String id, String productName, String price, String quantity) throws Exception {

        BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));

        List<String> listListForUpdate = new ArrayList<>();
        List<String> listForWrite = new ArrayList<>();

        while (inputStream.ready()) {
            listListForUpdate.add(inputStream.readLine());
        }

        inputStream.close();

        for (String oneLine : listListForUpdate) {
            int idForDel = 0;

            try {
                idForDel = Integer.parseInt(oneLine.substring(0, oneLine.indexOf(" ")));
            } catch (NumberFormatException e) {
                idForDel = Integer.parseInt(oneLine.substring(0, 8)) + 1;
            }

            String idForCompare = String.valueOf(idForDel);

            if (!idForCompare.equals(id)) {
                listForWrite.add(oneLine);
            }else{
                StringBuffer finalBuffer = new StringBuffer();

                finalBuffer.append(addSpaceToParam(id, id.length(), 8));
                finalBuffer.append(addSpaceToParam(productName, productName.length(), 30));
                finalBuffer.append(addSpaceToParam(price, price.length(), 8));
                finalBuffer.append(addSpaceToParam(quantity, quantity.length(), 4));

                listForWrite.add(finalBuffer.toString());

            }
        }

        FileOutputStream outputStream = new FileOutputStream(filename);

        for (String line : listForWrite) {
            outputStream.write(line.getBytes());
            outputStream.write('\n');
        }

        outputStream.close();
    }

    public static void deleteLineFromFile(String filename, String id) throws Exception {
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));

        List<String> listListForDelete = new ArrayList<>();
        List<String> listForWrite = new ArrayList<>();

        while (inputStream.ready()) {
            listListForDelete.add(inputStream.readLine());
        }

        inputStream.close();

        for (String oneLine : listListForDelete) {
            int idForDel = 0;

            try {
                idForDel = Integer.parseInt(oneLine.substring(0, oneLine.indexOf(" ")));
            } catch (NumberFormatException e) {
                idForDel = Integer.parseInt(oneLine.substring(0, 8)) + 1;
            }

            String idForCompare = String.valueOf(idForDel);

            if (!idForCompare.equals(id)) {
                listForWrite.add(oneLine);
            }
        }

        FileOutputStream outputStream = new FileOutputStream(filename);

        for (String line : listForWrite) {
            outputStream.write(line.getBytes());
            outputStream.write('\n');
        }

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
