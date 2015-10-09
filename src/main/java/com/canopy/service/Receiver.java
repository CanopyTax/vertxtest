package com.canopy.service;

        import io.vertx.core.AbstractVerticle;
        import io.vertx.core.Vertx;
        import io.vertx.core.eventbus.EventBus;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Receiver extends AbstractVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(Receiver.class.getCanonicalName());
    }

    @Override
    public void start() throws Exception {

        EventBus eb = vertx.eventBus();

        eb.consumer("ping-address", message -> {

            System.out.println("Received message: " + message.body());
            // Now send back reply
            message.reply("pong!");
        });

        System.out.println("Receiver ready!");
    }
}