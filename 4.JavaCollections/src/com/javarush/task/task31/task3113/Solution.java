package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

/* 
Что внутри папки?
*/
public class Solution {
    static int countDirectory = 0;
    static int countFiles = 0;
    static long bytes = 0;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String pathToDirectory = scanner.nextLine();

        Path path = Paths.get(pathToDirectory);

        if (!Files.isDirectory(path)) {
            System.out.println(path + " - не папка");
        } else {
            Files.walkFileTree(path, new MyVisitor());

            System.out.println("Всего папок - " + (countDirectory-1));
            System.out.println("Всего файлов - " + countFiles);
            System.out.println("Общий размер - " + bytes);
        }
    }

    public static class MyVisitor extends SimpleFileVisitor{
        @Override
        public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
            countFiles++;
            bytes += attrs.size();
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
            countDirectory++;
            return FileVisitResult.CONTINUE;
        }
    }
}
