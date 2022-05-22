package org.example;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Main {
    public static void main(String[] args) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        Thread consumerThread = new Thread(new Consumer(connectionFactory));
        consumerThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread producerThread = new Thread(new Producer(connectionFactory));
        producerThread.start();
    }
}