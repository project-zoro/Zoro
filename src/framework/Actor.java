package framework;

import java.util.UUID;

public abstract class Actor {
    private final UUID id = UUID.randomUUID();

    public UUID spawnActor(String name) {
        return ActorApp._getSuperVisor().spawnActor(name);
    }

    public UUID spawnActor(String name, Object... args) {
        return ActorApp._getSuperVisor().spawnActor(name, args);
    }

    public final boolean sendMessage(Message message, UUID receiver) {
        return ActorApp._getSuperVisor().sendMessage(message, receiver, id);
    }

    public abstract void onMessage(Message message, UUID senderId);

    public UUID getId() {
        return id;
    }
}