package com.javarush.task.task25.task2510;

/* 
Поживем - увидим
*/
public class Solution extends Thread {

    public Solution() {
        Thread thread = new Solution();
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if (e instanceof Error) {
                    System.out.println("Нельзя дальше работать");
                }
            }
        });
        thread.start();
    }

    public static void main(String[] args) {
    }
}
