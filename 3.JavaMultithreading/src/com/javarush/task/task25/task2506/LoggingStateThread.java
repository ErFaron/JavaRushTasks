package com.javarush.task.task25.task2506;


import static java.lang.Thread.State.TERMINATED;

public class LoggingStateThread extends Thread{

    Thread innTarget;

    public LoggingStateThread(Thread target){
        innTarget = target;
    }

    @Override
    public void run() {
        State st = innTarget.getState();
        System.out.println(st);
        while(st != State.TERMINATED){
            if(st != innTarget.getState()) {
                st = innTarget.getState();
                System.out.println(st);
            }
        }
    }
}