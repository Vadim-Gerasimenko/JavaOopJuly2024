package ru.academits.gerasimenko.arraylisthome;

import java.io.*;
import java.util.ArrayList;

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

    public static void removeEvenNumbers(ArrayList<Integer> numbers) {
        int numbersCount = numbers.size();

        for (int i = 0; i < numbersCount; ++i) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
                --numbersCount;
                --i;
            }
        }
    }

    public static ArrayList<Integer> getUniqueNumbers(ArrayList<Integer> numbers) {
        ArrayList<Integer> uniqueIntegers = new ArrayList<>(numbers.size());

        for (Integer number : numbers) {
            if (!uniqueIntegers.contains(number)) {
                uniqueIntegers.add(number);
            }
        }

        return uniqueIntegers;
    }
}