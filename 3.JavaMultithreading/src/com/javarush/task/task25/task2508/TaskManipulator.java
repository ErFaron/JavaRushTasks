package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {

    Thread innThread;

    @Override
    public void run() {
        try {
            while (!innThread.isInterrupted()) {
                Thread.sleep(0);
                System.out.println(innThread.getName());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void start(String threadName) {
        innThread = new Thread(this, threadName);
        innThread.start();
    }

    @Override
    public void stop() {
        innThread.interrupt();
    }
}
