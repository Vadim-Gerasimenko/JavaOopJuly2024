package ru.academits.gerasimenko.producerconsumer.main;

import ru.academits.gerasimenko.producerconsumer.ProducerConsumerManager;

public class Main {
    public static void main(String[] args) {
        ProducerConsumerManager producerConsumerManager = new ProducerConsumerManager(4, 3, 10);
        producerConsumerManager.start();
    }
}