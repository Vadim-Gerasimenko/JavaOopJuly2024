package ru.academits.gerasimenko.arraylisthome;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            final String filePath = "ArrayListHome/src/ru/academits/gerasimenko/arraylisthome/files/in.txt";
            ArrayList<String> lines = getFileLines(filePath);

            if (!lines.isEmpty()) {
                System.out.println("File lines:");
                lines.forEach(System.out::println);

                System.out.println();
            } else {
                System.out.println("File is empty.");
            }
        } catch (IOException e) {
            System.out.println("File reading error: " + e.getMessage());
        }

        ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 1, 1, 3, 5, 7, 2, 2, 4, 3, 4, 5, 6, 7, 8));
        removeEvenNumbers(numbers);

        System.out.print("List of numbers after removing even numbers: ");

        for (Integer number : numbers) {
            System.out.print(number + " ");
        }

        System.out.println();

        ArrayList<Integer> uniqueNumbers = getUniqueNumbers(numbers);

        System.out.print("List of unique numbers: ");

        for (Integer number : uniqueNumbers) {
            System.out.print(number + " ");
        }
    }

    public static ArrayList<String> getFileLines(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ArrayList<String> lines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            return lines;
        }
    }

    public static void removeEvenNumbers(ArrayList<Integer> numbers) {
        for (int i = numbers.size() - 1; i >= 0; --i) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            }
        }
    }

    public static <N> ArrayList<N> getUniqueNumbers(ArrayList<N> numbers) {
        ArrayList<N> uniqueNumbers = new ArrayList<>(numbers.size());

        for (N number : numbers) {
            if (!uniqueNumbers.contains(number)) {
                uniqueNumbers.add(number);
            }
        }

        return uniqueNumbers;
    }
}