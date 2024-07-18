package ru.academits.gerasimenko.list.main;

import ru.academits.gerasimenko.list.SinglyLinkedList;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> integerList = new SinglyLinkedList<>();
        integerList.add(0,3);
        integerList.addFirst(1);
        integerList.add(1, 2);
        System.out.println(integerList.remove(Integer.valueOf(4)));
        System.out.println(integerList);

        SinglyLinkedList<String> stringList = new SinglyLinkedList<>();

        System.out.println(integerList);

        LinkedList<Integer> origin = new LinkedList<>();

        integerList.reverse();
        System.out.println(integerList);

        System.out.println(integerList.remove(Integer.valueOf(6)));
        integerList.add(3, 0);
        System.out.println(integerList);
    }
}