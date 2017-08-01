package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        InputStream inputStream = new FileInputStream(fileName);

        load(inputStream);

        inputStream.close();
        //implement this method - реализуйте этот метод
    }

    public void save(OutputStream outputStream) throws Exception {

        Properties prop = new Properties();
        prop.putAll(properties);
        prop.store(outputStream,"");
        outputStream.flush();
        //implement this method - реализуйте этот метод
    }

    public void load(InputStream inputStream) throws Exception {

        Properties prop = new Properties();
        prop.load(inputStream);
        Map<String, String> map = (Map)prop;
        properties.putAll(map);

        //implement this method - реализуйте этот метод
    }

    public static void main(String[] args) throws Exception {
    }
}
