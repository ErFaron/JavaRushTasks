package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String,String> countries = new HashMap<String,String>();
    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {
//        DataAdapter dataAdapter = new DataAdapter(new Customer() {
//            @Override
//            public String getCompanyName() {
//                return "JavaRush Ltd.";
//            }
//            @Override
//            public String getCountryName() {
//                return "Ukraine";
//            }
//        }, new Contact() {
//            @Override
//            public String getName() {
//                return "Ivanov, Ivan";
//            }
//            @Override
//            public String getPhoneNumber() {
//                return "+38(050)123-45-67";
//            }
//        });
//        System.out.println(dataAdapter.getCountryCode());
//        System.out.println(dataAdapter.getCompany());
//        System.out.println(dataAdapter.getContactFirstName());
//        System.out.println(dataAdapter.getContactLastName());
//        System.out.println(dataAdapter.getDialString());
    }

    public static class DataAdapter implements RowItem{
        private Customer customer;
        private Contact contact;
        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }

        @Override
        public String getCountryCode() {
            String res="";
            Set<Map.Entry<String,String>> entrySet = countries.entrySet();
            String desire = customer.getCountryName();
            for (Map.Entry<String,String> pair : entrySet)
            {
                if (desire.equals(pair.getValue()))
                    res = pair.getKey();
            }
            return res;
        }

        @Override
        public String getCompany() {
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            String[] array = contact.getName().split(" ");
            return array[1];
        }

        @Override
        public String getContactLastName() {
            String[] array = contact.getName().split(",");
            return array[0];
        }

        @Override
        public String getDialString() {
            String tempStr = contact.getPhoneNumber().replaceAll("[()-]", "");
            String result = "callto://" + tempStr;
            return result;
        }
    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }
}