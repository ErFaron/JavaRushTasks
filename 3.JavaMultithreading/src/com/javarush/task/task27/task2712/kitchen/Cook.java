package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.List;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private LinkedBlockingQueue<Order> queue;
    String name;
    private volatile boolean stop = false;
    private volatile boolean caught = false;

    public Cook(String name) {
        this.name = name;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order) {
        ConsoleHelper.writeMessage("Start cooking - " + order);
        Order order1 = (Order) order;
        String tabletName = order1.getTablet().toString();
        String cookName = name;
        int cookingTimeSeconds = order1.getTotalCookingTime();
        List<Dish> cookingDishs = order1.getDishes();
        CookedOrderEventDataRow cookedOrderEventDataRow = new CookedOrderEventDataRow(tabletName, cookName,
                cookingTimeSeconds, cookingDishs);
        StatisticManager.getInstance().register(cookedOrderEventDataRow);
        setChanged();
        notifyObservers(order);
        try {
            Thread.sleep(order1.getTotalCookingTime() * 10);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void run() {

        while (!stop) {
            try {
                startCookingOrder(queue.take());
            } catch (InterruptedException e) {
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                caught = true;
            }
            if (caught && queue.isEmpty()) {
                stop = true;
            }
        }
    }
}
