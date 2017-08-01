package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {
    }

    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            Person person = null;
            while (fileScanner.nextLine() != null) {
                String line = fileScanner.nextLine();
                String array[] = line.split(" ");
                Date bd = null;
                String bdString = array[3] + array[4] + array[5];
                SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
                try {
                    bd = format.parse(bdString);
                } catch (ParseException e) {
                }
                person = new Person(array[1], array[2], array[0], bd);
            }
            return person;
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
