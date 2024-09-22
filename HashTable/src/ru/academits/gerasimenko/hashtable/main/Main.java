package ru.academits.gerasimenko.hashtable.main;

import ru.academits.gerasimenko.hashtable.HashTable;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>();
        hashTable.add(3);
        hashTable.add(2);
        hashTable.add(1);
        System.out.println("Hash table: " + hashTable);

        final int searchElement = 4;
        System.out.print("Element " + searchElement + " ");

        if (hashTable.contains(searchElement)) {
            System.out.println("is contained in the hash table.");
        } else {
            System.out.println("is not contained in the hash table.");
        }

        hashTable.remove(3);
        System.out.println("Hash table after removing element 3: " + hashTable);
        System.out.println();

        List<Integer> list1 = Arrays.asList(5, 3, 2, 2, 1, 1, 3, 3, 7, 6, 8);
        System.out.println("1-st list elements: " + list1);

        hashTable.addAll(list1);
        System.out.println("Hash table after adding all 1-st list elements: " + hashTable);
        System.out.println("Hash table elements count: " + hashTable.size());
        System.out.println();

        List<Integer> list2 = Arrays.asList(5, 2, 6);
        System.out.println("2-nd list elements: " + list2);

        if (hashTable.containsAll(list2)) {
            System.out.println("Hash table contains all elements from 2-nd list.");
        } else {
            System.out.println("Hash table not contains all elements from 2-nd list.");
        }

        hashTable.removeAll(list2);
        System.out.println("Hash table after removing all 2-nd list elements: " + hashTable);
        System.out.println("Hash table elements count: " + hashTable.size());

        Object[] array = hashTable.toArray(new Integer[4]);
        System.out.println("Hash table to array with smaller size: " + Arrays.toString(array));
        System.out.println();

        List<Integer> list3 = Arrays.asList(8, 1, 3);
        System.out.println("3-rd list elements: " + list3);

        hashTable.retainAll(list3);
        System.out.println("Hash table after retains all elements from 3-rd list: " + hashTable);
        System.out.println("Hash table elements count: " + hashTable.size());
        System.out.printf("%nAll elements of hash table: ");

        for (Integer element : hashTable) {
            System.out.print(element + " ");
        }
    }
}