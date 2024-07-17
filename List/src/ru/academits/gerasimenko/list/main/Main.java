package ru.academits.gerasimenko.list.main;

import ru.academits.gerasimenko.list.SinglyLinkedList;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> integerList = new SinglyLinkedList<>();
        integerList.addFirst(3);
        integerList.addFirst(2);
        System.out.println(integerList.set(1, 1));
        System.out.println(integerList.get(1));
        System.out.println(integerList.getFirst());

        LinkedList<Integer> origin = new LinkedList<>();
    }
}