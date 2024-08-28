package ru.academits.gerasimenko.arraylisthome;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArrayListHome {
    public static ArrayList<String> getFileLines(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ArrayList<String> lines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            return lines;
        } catch (IOException e) {
            System.out.println("File reading error: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        String filePath = "ArrayListHome/src/ru/academits/gerasimenko/arraylisthome/files/in.txt";
        ArrayList<String> lines = getFileLines(filePath);

        if (lines != null) {
            System.out.println("File lines:");
            lines.forEach(System.out::println);
            System.out.println();
        }

        ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 2, 2, 4, 3, 4, 5, 6, 7, 8));
        numbers.removeIf(number -> number % 2 == 0);

        System.out.println("List of numbers after removing even numbers:");

        for (Integer number : numbers) {
            System.out.print(number + " ");
        }

        System.out.println();

        ArrayList<Integer> integers = new ArrayList<>(List.of(1, 5, 2, 1, 3, 5));
        ArrayList<Integer> uniqueIntegers = new ArrayList<>();

        for (Integer integer : integers) {
            if (!uniqueIntegers.contains(integer)) {
                uniqueIntegers.add(integer);
            }
        }

        System.out.println("List of unique integers:");

        for (Integer uniqueInteger : uniqueIntegers) {
            System.out.print(uniqueInteger + " ");
        }

        System.out.println();
    }
}