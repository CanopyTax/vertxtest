package com.canopy.service;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Sender extends AbstractVerticle {

//    // Convenience method so you can run it in your IDE
//    public static void main(String[] args) {
//        Vertx vertx = Vertx.vertx();
//        vertx.deployVerticle(Sender.class.getCanonicalName());
//    }

    @Override
    public void start() throws Exception {
        EventBus eb = vertx.eventBus();

        // Send a message every second

        vertx.setPeriodic(1000, v -> {

            eb.send("ping-address", "ping!", reply -> {
                if (reply.succeeded()) {
                    System.out.println("Received reply " + reply.result().body());
                } else {
                    System.out.println("No reply");
                }
            });

        });
    }
}