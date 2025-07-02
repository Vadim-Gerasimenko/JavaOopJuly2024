package ru.academits.gerasimenko.threadpool.main;

import ru.academits.gerasimenko.threadpool.ThreadPool;

public class Main {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(4);

        for (int i = 1; i <= 100; i++) {
            int j = i;
            threadPool.submit(() -> System.out.println(j));
        }

        threadPool.start();
    }
}