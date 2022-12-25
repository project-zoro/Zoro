package framework.processing;

import framework.channel.SendableMessage;
import framework.registry.ActorRegistry;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class MessageProcessor implements Runnable {
    private final BlockingDeque<SendableMessage> channel;
    private final ActorRegistry actorRegistry;

    public MessageProcessor(LinkedBlockingDeque<SendableMessage> channel, ActorRegistry actorRegistry) {
        this.actorRegistry = actorRegistry;
        this.channel = channel;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                SendableMessage message = channel.takeLast();
                var actor = actorRegistry.getActor(message.receiver());
                if (actor != null) {
                    actor.onMessage(message.message(), message.sender());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
