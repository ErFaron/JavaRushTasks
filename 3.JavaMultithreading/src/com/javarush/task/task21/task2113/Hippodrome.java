package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    private List<Horse> horses;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    static Hippodrome game;

    public static void main(String[] args) throws Exception {
        List<Horse> horseList = new ArrayList<>();
        horseList.add(new Horse("Adel", 3, 0));
        horseList.add(new Horse("Bekki", 3, 0));
        horseList.add(new Horse("Candy", 3, 0));
        game = new Hippodrome(horseList);

        game.run();

        game.printWinner();
    }

    public void run() throws Exception {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        Horse winner = horses.get(0);
        for (int i = 1; i < horses.size(); i++) {
            if (winner.distance < horses.get(i).distance) {
                winner = horses.get(i);
            }
        }
        return winner;
    }

    public void printWinner() {
        System.out.print("Winner is " + getWinner().getName() + "!");
    }
}
