package framework.supervisor;

import framework.channel.MessageHandler;
import framework.registry.ActorRegistry;
import framework.spawn.ActorFactory;

public final class DefaultSupervisor extends Supervisor {

    private DefaultSupervisor(String actorPackage, ActorRegistry actorRegistry) {
        super(new ActorFactory(actorPackage), actorRegistry, new MessageHandler(actorRegistry));
    }

    public static Supervisor actors(String actorPackage) {
        return new DefaultSupervisor(actorPackage, new ActorRegistry());
    }
}
