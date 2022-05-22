package org.example;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer implements Runnable {
    ActiveMQConnectionFactory connectionFactory = null;

    public Consumer(ActiveMQConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void run() {
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination topicDestination = session.createTopic("CLIMATE");

            MessageConsumer messageConsumer = session.createConsumer(topicDestination);

            Message message = messageConsumer.receive();

            TextMessage textMessage = (TextMessage) message;

            System.out.println(textMessage.getText());

            session.close();
            connection.close();
        } catch (JMSException jmse) {
            System.out.println("Exception: " + jmse.getMessage());
        }
    }
}
