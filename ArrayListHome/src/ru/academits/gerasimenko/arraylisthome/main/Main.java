package ru.academits.gerasimenko.arraylisthome.main;

import java.util.ArrayList;
import java.util.List;

import static ru.academits.gerasimenko.arraylisthome.ArrayListHome.*;

public class Main {
    public static void main(String[] args) {
        final String filePath = "ArrayListHome/src/ru/academits/gerasimenko/arraylisthome/files/in.txt";
        ArrayList<String> lines = getFileLines(filePath);

        if (lines != null) {
            System.out.println("File lines:");
            lines.forEach(System.out::println);
            System.out.println();
        }

        ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 2, 2, 4, 3, 4, 5, 6, 7, 8));
        removeEvenNumbers(numbers);

        System.out.print("List of numbers after removing even numbers: ");

        for (Integer number : numbers) {
            System.out.print(number + " ");
        }

        System.out.println();

        ArrayList<Integer> integers = new ArrayList<>(List.of(1, 5, 2, 1, 3, 5));
        ArrayList<Integer> uniqueIntegers = getUniqueNumbers(integers);

        System.out.print("List of unique integers: ");

        for (Integer uniqueInteger : uniqueIntegers) {
            System.out.print(uniqueInteger + " ");
        }

        System.out.println();
    }
}