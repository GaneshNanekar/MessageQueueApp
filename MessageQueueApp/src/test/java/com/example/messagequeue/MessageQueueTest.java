package com.example.messagequeue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageQueueTest {

    @Test
    public void testProduceConsume() throws InterruptedException {
        MessageQueue queue = new MessageQueue();
        queue.produce("Test Message");
        assertEquals("Test Message", queue.consume());
    }

    @Test
    public void testConsumerSuccessCount() throws InterruptedException {
        MessageQueue queue = new MessageQueue();
        Consumer consumer = new Consumer(queue);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        queue.produce("Test Message");
        Thread.sleep(200); // Ensure consumer processes

        queue.stop(); // Stop queue to terminate consumer
        consumerThread.join();

        assertEquals(1, Consumer.getSuccessCount());
    }

    @Test
    public void testConsumerErrorCount() {
        assertEquals(0, Consumer.getErrorCount());
    }
}
