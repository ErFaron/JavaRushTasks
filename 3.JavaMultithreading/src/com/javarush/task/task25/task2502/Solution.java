package com.javarush.task.task25.task2502;

import javax.naming.directory.InvalidAttributesException;
import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() throws Exception {
            String[] array = loadWheelNamesFromDB();
            Wheel[] wheelArray = Wheel.values();
            List<String> list = Arrays.asList(array);


            if ( array.length != wheelArray.length){throw new Exception();}

            List<Wheel> temp = new ArrayList<>();
            for (Wheel wheel : Wheel.values()) {
                if (!list.contains(wheel.name())) {
                    throw new InvalidAttributesException();
                } else {temp.add(wheel);}
            }
            wheels = temp;
            //init wheels here
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) throws Exception{
        Car car = new Car();
    }
}
