package ru.academits.gerasimenko.list.main;

import ru.academits.gerasimenko.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> integersList = new SinglyLinkedList<>();
        System.out.println("List: " + integersList);
        System.out.println("List size: " + integersList.getSize());
        System.out.println();

        integersList.addFirst(4);
        integersList.addFirst(2);
        System.out.println("List after adding two elements to the beginning: " + integersList);
        System.out.println("List size: " + integersList.getSize());
        System.out.println();

        integersList.add(1, 3);
        System.out.println("List after adding element to first index: " + integersList);
        System.out.println();

        System.out.println("List first element value: " + integersList.getFirst());
        System.out.println("Value of list element at index 2: " + integersList.get(2));
        System.out.println();

        System.out.println("Value of list element at index 1 before set: " + integersList.set(1, 5));
        System.out.println("New value: " + integersList.get(1));
        System.out.println("List: " + integersList);
        System.out.println();

        System.out.println("Value of deleted list element at index 2: " + integersList.remove(2));
        System.out.println("List: " + integersList);
        System.out.println("Value of deleted first element: " + integersList.removeFirst());
        System.out.println("List: " + integersList);
        System.out.println();

        integersList.add(0, 4);
        integersList.add(1, 6);
        integersList.add(2, 5);
        System.out.println("List after adding new elements: " + integersList);

        integersList.reverse();
        System.out.println("List after reverse: " + integersList);
        System.out.println();

        System.out.println("Trying to delete element with value 5");

        if (integersList.remove(Integer.valueOf(5))) {
            System.out.println("Element successfully deleted.");
        } else {
            System.out.println("This element is not in the list.");
        }

        System.out.println("List: " + integersList);
        System.out.println("List size: " + integersList.getSize());
        System.out.println();

        SinglyLinkedList<Integer> integersListCopy = integersList.copy();
        System.out.println("Copy of integers list: " + integersListCopy);
        System.out.println("List copy size: " + integersListCopy.getSize());
    }
}