package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String>{
    private int x;

    public BinaryRepresentationTask(int x) {
        this.x = x;
        compute();
    }

    @Override
    protected String compute() {
        boolean bul = false;
        if(bul){
            fork();
            join();
        }
        return binaryRepresentationMethod(x);
    }

    static String binaryRepresentationMethod(int x) {
        int a = x % 2;
        int b = x / 2;
        String result = String.valueOf(a);
        if (b > 0) {
            return binaryRepresentationMethod(b) + result;
        }
        return result;
    }
}
