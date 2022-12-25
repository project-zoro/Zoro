package framework.supervisor;

import framework.channel.MessageHandler;
import framework.registry.ActorRegistry;
import framework.spawn.ActorFactory;

public final class DefaultSuperVisor extends SuperVisor {

    private DefaultSuperVisor(String actorPackage, ActorRegistry actorRegistry) {
        super(new ActorFactory(actorPackage), actorRegistry, new MessageHandler(actorRegistry));
    }

    public static SuperVisor actors(String actorPackage) {
        return new DefaultSuperVisor(actorPackage, new ActorRegistry());
    }
}
