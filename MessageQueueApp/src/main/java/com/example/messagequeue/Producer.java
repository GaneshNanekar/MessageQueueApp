package com.example.messagequeue;

public class Producer implements Runnable {
    private final MessageQueue queue;
    private final int numberOfMessages;

    public Producer(MessageQueue queue, int numberOfMessages) {
        this.queue = queue;
        this.numberOfMessages = numberOfMessages;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfMessages; i++) {
            String message = "Message " + i;
            queue.produce(message);
            System.out.println("Produced: " + message);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        queue.stop(); // Notify consumer to stop
    }
}
