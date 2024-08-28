package ru.academits.gerasimenko.arraylist.main;

import ru.academits.gerasimenko.arraylist.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> integersArrayList1 = new ArrayList<>();
        integersArrayList1.add(5);
        integersArrayList1.addFirst(0);
        integersArrayList1.add(2, 3);

        System.out.println("1-st list: " + integersArrayList1);
        System.out.println();

        ArrayList<Integer> integersArrayList2 = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        System.out.println("2-nd list: " + integersArrayList2);
        System.out.println();

        ArrayList<Integer> integersArrayList3 = new ArrayList<>(30);
        integersArrayList3.addAll(integersArrayList1);
        integersArrayList3.addAll(2, integersArrayList2);

        System.out.println("3-rd list: " + integersArrayList3);
        System.out.println("Size: " + integersArrayList3.size());
        System.out.println();

        System.out.println("Traversing a 3-rd list through an iterator (forEach): ");

        for (Integer integer : integersArrayList3) {
            System.out.print(integer + " ");
        }

        System.out.printf("%n%n");

        System.out.println("1-st list to array: " + Arrays.toString(integersArrayList1.toArray()));
        System.out.println();

        Integer removedElement = 4;
        System.out.printf("Trying to remove element \"%d\" from 3-rd list:%n", removedElement);

        if (integersArrayList3.remove(removedElement)) {
            integersArrayList3.trimToSize();
            System.out.println("Element successfully removed.");
        } else {
            System.out.println("The element was not removed because it is not in the list.");
        }

        System.out.println("3-rd list: " + integersArrayList3);
    }
}