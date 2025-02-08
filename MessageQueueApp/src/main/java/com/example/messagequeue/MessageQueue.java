package com.example.messagequeue;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private final Queue<String> queue = new LinkedList<>();
    private boolean isRunning = true;

    public synchronized void produce(String message) {
        queue.add(message);
        notify();
    }

    public synchronized String consume() throws InterruptedException {
        while (queue.isEmpty() && isRunning) {
            wait();
        }
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized void stop() {
        isRunning = false;
        notifyAll(); // Ensure waiting consumers exit
    }
}
