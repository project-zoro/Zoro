package framework.supervisor;

import framework.Actor;
import framework.Message;
import framework.channel.MessageHandler;
import framework.channel.SendableMessage;
import framework.registry.ActorRegistry;
import framework.spawn.ActorFactory;

import java.util.UUID;

public class Supervisor extends Actor {
    private final ActorFactory actorFactory;
    private final ActorRegistry actorRegistry;
    private final MessageHandler messageHandler;

    public Supervisor(ActorFactory actorFactory, ActorRegistry actorRegistry,
                      MessageHandler messageHandler) {
        this.actorFactory = actorFactory;
        this.actorRegistry = actorRegistry;
        this.messageHandler = messageHandler;
    }

    public UUID spawnActor(String actorName) {
        var actor = actorFactory.loadActor(actorName);
        return actorRegistry.registerActor(actor);
    }

    public UUID spawnActor(String actorName, Object... args) {
        var actor = actorFactory.loadActor(actorName, args);
        return actorRegistry.registerActor(actor);
    }

    public boolean sendMessage(Message message, UUID receiver, UUID sender) {
        return messageHandler.tryQueue(new SendableMessage(message, receiver, sender));
    }

    @Override
    public void onMessage(Message message, UUID from) {

    }
}