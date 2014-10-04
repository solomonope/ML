/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovworks.rabbitapp.manager;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Folorunsho Solomon
 */
public class RabbitMqManager implements ShutdownListener {

    private static final Logger logger = Logger.getLogger(RabbitMqManager.class.getSimpleName());
    private final ConnectionFactory connectionFactory;
    private final ScheduledExecutorService scheduledExceutorService;
    private volatile Connection connection;

    /**
     *
     * @param connectionFactory
     */
    public RabbitMqManager(final ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        scheduledExceutorService = Executors.newSingleThreadScheduledExecutor();
        connection = null;

    }

    public void start() {
        try {
            connection = connectionFactory.newConnection();
            connection.addShutdownListener(this);
        } catch (final IOException ex) {
            logger.log(Level.SEVERE, null, ex);
            asyncWaitAndReconnect();
        }
    }

    public void shutdownCompleted(ShutdownSignalException shutdownSignalException) {
        if (!shutdownSignalException.isInitiatedByApplication()) {
            connection = null;
            asyncWaitAndReconnect();
        }
    }

    public void stop() {
        this.scheduledExceutorService.shutdown();
        if (connection != null) {
            try {
                connection.close();
            } catch (IOException ex) {
                Logger.getLogger(RabbitMqManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Channel createChannel() {
        try {
            return connection == null ? null : connection.createChannel();
        } catch (IOException ex) {
            Logger.getLogger(RabbitMqManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void closeChannel(final Channel channel) {
        if (channel != null && channel.isOpen()) {
            try {
                channel.close();
            } catch (IOException ex) {
                Logger.getLogger(RabbitMqManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void asyncWaitAndReconnect() {
        this.scheduledExceutorService.schedule(new Runnable() {
            @Override
            public void run() {
                start();
            }
        }, 15, TimeUnit.DAYS);
    }
}
