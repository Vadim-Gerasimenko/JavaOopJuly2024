package ru.academits.gerasimenko.threadpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ThreadPool {
    private final int threadsCount;
    private final Queue<Runnable> queue = new LinkedList<>();
    private final List<Thread> threads = new ArrayList<>();

    public ThreadPool(int threadsCount) {
        this.threadsCount = threadsCount;
    }

    public void submit(Runnable runnable) {
        synchronized (queue) {
            queue.add(runnable);
            queue.notifyAll();
        }
    }

    public void start() {
        for (int i = 1; i <= threadsCount; i++) {
            Thread thread = new Thread(() -> {
                Thread currentThread = Thread.currentThread();

                try {
                    while (!currentThread.isInterrupted()) {
                        Runnable runnable;

                        synchronized (queue) {
                            while (queue.isEmpty()) {
                                queue.wait();
                            }

                            runnable = queue.remove();
                        }

                        System.out.println("Task taken by thread #" + currentThread.getId());
                        runnable.run();
                    }
                } catch (InterruptedException ignored) {
                }
            });

            threads.add(thread);

            thread.start();
        }
    }

    public void stop() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
