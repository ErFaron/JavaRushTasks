package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(byteArrayOutputStream.toByteArray());
        String string = new BigInteger(1, messageDigest.digest()).toString(16);
        StringBuilder stringBuilder = new StringBuilder(32);
        for(int i = 0, count = 32 - string.length(); i < count; i++){
            stringBuilder.append("0");
        }
        if(stringBuilder.append(string).toString().equals(md5)){return true;}
        return false;
    }
}
