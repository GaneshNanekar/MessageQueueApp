package com.example.messagequeue;

import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable {
    private final MessageQueue queue;
    private static final AtomicInteger successCount = new AtomicInteger(0);
    private static final AtomicInteger errorCount = new AtomicInteger(0);

    public Consumer(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = queue.consume();
                if (message == null) break; // Stop consuming if queue is stopped
                System.out.println("Consumed: " + message);
                successCount.incrementAndGet();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                errorCount.incrementAndGet();
                break; // Exit on interruption
            }
        }
    }

    public static int getSuccessCount() {
        return successCount.get();
    }

    public static int getErrorCount() {
        return errorCount.get();
    }
}
