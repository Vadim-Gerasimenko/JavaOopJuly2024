package ru.academits.gerasimenko.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerConsumerManager {
    private final int producersCount;
    private final int consumersCount;
    private final int capacity;
    private final Queue<String> queue = new LinkedList<>();
    private final AtomicInteger itemNumber = new AtomicInteger(0);

    public ProducerConsumerManager(int producersCount, int consumersCount, int capacity) {
        this.producersCount = producersCount;
        this.consumersCount = consumersCount;
        this.capacity = capacity;
    }

    public void start() {
        for (int i = 1; i <= producersCount; i++) {
            Thread thread = new Thread(() -> {
                Thread currentThread = Thread.currentThread();

                try {
                    while (!currentThread.isInterrupted()) {
                        Thread.sleep(1000);

                        String item = "Item #" + itemNumber.incrementAndGet();

                        synchronized (queue) {
                            while (queue.size() >= capacity) {
                                queue.wait();
                            }

                            queue.add(item);
                            System.out.printf("Produced by thread %d: %s. Size = %d%n",
                                    currentThread.getId(), item, queue.size());

                            queue.notifyAll();
                        }
                    }
                } catch (InterruptedException ignored) {
                }
            });

            thread.start();
        }

        for (int i = 1; i <= consumersCount; i++) {
            Thread thread = new Thread(() -> {
                Thread currentThread = Thread.currentThread();

                try {
                    while (!currentThread.isInterrupted()) {
                        Thread.sleep(1000);

                        synchronized (queue) {
                            while (queue.isEmpty()) {
                                queue.wait();
                            }

                            String item = queue.remove();
                            System.out.printf("Taken by thread %d: %s. Size = %d%n",
                                    currentThread.getId(), item, queue.size());

                            queue.notifyAll();
                        }
                    }
                } catch (InterruptedException ignored) {
                }
            });

            thread.start();
        }
    }
}
