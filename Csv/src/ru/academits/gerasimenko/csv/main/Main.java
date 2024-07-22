package ru.academits.gerasimenko.csv.main;

import ru.academits.gerasimenko.csv.CsvToHtmlConverter;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CsvToHtmlConverter.convert("source.csv");
    }
}