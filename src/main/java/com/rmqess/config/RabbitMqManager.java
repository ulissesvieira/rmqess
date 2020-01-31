package com.rmqess.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;
import com.rmqess.service.ChannelCallable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RabbitMqManager implements ShutdownListener {
   private final static Logger LOG = LoggerFactory.getLogger(RabbitMqManager.class);

   private final ConnectionFactory factory;
   private final ScheduledExecutorService executor;
   private volatile Connection connection;

   private String localAddress;

   public RabbitMqManager(final ConnectionFactory factory) {
      this.factory = factory;
      executor = Executors.newSingleThreadScheduledExecutor();
      connection = null;

      localAddress = factory.getHost() + ":" + factory.getPort();
   }

   public void start() {
      try {
         connection = factory.newConnection();
         connection.addShutdownListener(this);
         LOG.info("Connected to " + localAddress);
      }
      catch (final Exception ex) {
         LOG.error("Faild to connect to " + localAddress, ex);
         asyncWaitAndReconnect();
      }
   }

   @Override
   public void shutdownCompleted(ShutdownSignalException cause) {
      // reconect only on unexpected errors
      if (!cause.isInitiatedByApplication()) {
         LOG.error("Lost connection to " + localAddress, cause);

         connection = null;
         asyncWaitAndReconnect();
      }
   }

   private void asyncWaitAndReconnect() {
      executor.schedule(new Runnable() {

         @Override
         public void run() {
           start(); 
         }
         
      }, 15, TimeUnit.SECONDS);
   }

   public void stop() {
      executor.shutdown();

      if (connection == null) {
         return;
      }

      try {
         connection.close();
      }
      catch (final Exception ex) {
         LOG.error("Failed to close connection", ex);
      }
      finally {
         connection = null;
      }
   }

   public Channel createChannel() {
      try {
         return connection == null ? null : connection.createChannel();
      }
      catch (final Exception ex) {
         LOG.error("Failed to create channel", ex);
         return null;
      }
   }

   public void closeChannel(final Channel channel) {
      // isOpen is not fully trustable!!!
      if (channel == null || !channel.isOpen()) {
         return;
      }

      try {
         channel.close();
      }
      catch (final Exception ex) {
         LOG.error("Failed to close channel: " + channel, ex);
      }
   }

   public <T> T call(final ChannelCallable<T> callable) {
      final Channel channel = createChannel();

      if (channel != null) {
         try {
            return callable.call(channel);
         }
         catch (Exception ex) {
            LOG.error("Failed to run: " + callable.getDescription() + " on channel: " + channel, ex);
         }
         finally {
            closeChannel(channel);
         }
      }

      return null;
   }

}
