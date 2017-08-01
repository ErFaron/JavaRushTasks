package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            while (reader.ready()) {
                String line = reader.readLine();
                String[] array = line.split(" ");

                int year = 0;
                int month = 0;
                int day = 0;

                try {
                    year = Integer.parseInt(array[array.length - 1]);
                    month = Integer.parseInt(array[array.length - 2]);
                    day = Integer.parseInt(array[array.length - 3]);
                } catch (Exception e) {

                }

                String temp = "";
                for (int i = 0; i < array.length - 3; i++) {
                    temp = temp + array[i] + " ";
                }

                Date date = (new GregorianCalendar(year, month - 1, day)).getTime();

                PEOPLE.add(new Person(temp.trim(), date));
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
