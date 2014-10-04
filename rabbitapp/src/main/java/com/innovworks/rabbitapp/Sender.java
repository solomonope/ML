/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovworks.rabbitapp;

/**
 *
 * @author Folorunsho Solomon
 */
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.IOException;

public class Sender {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,
                false, false, false, null);
        String message = "Haven,Good intentions";

        channel.basicPublish(
                "", QUEUE_NAME, null, message.getBytes());
        System.out.println(
                " [x] Sent '" + message + "'");
        channel.close();
    connection.close();

    }

}
