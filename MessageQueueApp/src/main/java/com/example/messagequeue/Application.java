package com.example.messagequeue;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of messages to produce: ");
        int numberOfMessages = scanner.nextInt();

        MessageQueue queue = new MessageQueue();
        Thread producerThread = new Thread(new Producer(queue, numberOfMessages));
        Thread consumerThread = new Thread(new Consumer(queue));

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join(1000); // Allow consumer to process for a while
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Total messages processed successfully: " + Consumer.getSuccessCount());
        System.out.println("Total errors encountered: " + Consumer.getErrorCount());
    }
}
