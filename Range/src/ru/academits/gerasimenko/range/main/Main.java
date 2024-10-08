package ru.academits.gerasimenko.range.main;

import ru.academits.gerasimenko.range.Range;

public class Main {
    public static void main(String[] args) {
        System.out.println("Часть 1.");

        Range range1 = new Range(-100, 0);
        System.out.println("Заданный диапазон: " + range1);

        System.out.println("Длина диапазона: " + range1.getLength());
        System.out.println();

        System.out.println("Доступ к полям объекта при помощи геттеров:");
        System.out.println("Начало диапазона: " + range1.getFrom());
        System.out.println("Конец диапазона: " + range1.getTo());
        System.out.println();

        range1.setFrom(1.4);
        range1.setTo(25.7);

        System.out.println("Диапазон после изменения границ: " + range1);
        System.out.println("Длина диапазона: " + range1.getLength());

        double number = 123;

        if (range1.isInside(number)) {
            System.out.printf("Число %f принадлежит диапазону ", number);
        } else {
            System.out.printf("Число %f не принадлежит диапазону ", number);
        }

        System.out.println(range1);
        System.out.println();

        System.out.println("Часть 2.");
        System.out.println("Первый диапазон: " + range1);

        Range range2 = new Range(25.7, 32);
        System.out.println("Второй диапазон: " + range2);
        System.out.println();

        Range intersectionRange = range1.getIntersection(range2);

        if (intersectionRange == null) {
            System.out.println("Диапазоны не пересекаются.");
        } else {
            System.out.println("Пересечение:" + intersectionRange);
        }

        System.out.println();

        Range[] unionRanges = range1.getUnion(range2);

        if (unionRanges.length == 1) {
            System.out.println("Диапазон объединения:");
        } else {
            System.out.println("Диапазоны объединения:");
        }

        for (Range range : unionRanges) {
            System.out.println(range);
        }

        System.out.println();

        Range[] differenceRanges = range1.getDifference(range2);

        if (differenceRanges.length == 0) {
            System.out.println("Разность указанных диапазонов является пустым множеством.");
            return;
        }

        if (differenceRanges.length == 1) {
            System.out.println("Диапазон разности:");
        } else {
            System.out.println("Диапазоны разности:");
        }

        for (Range range : differenceRanges) {
            System.out.println(range);
        }
    }
}