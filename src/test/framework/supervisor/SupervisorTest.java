package test.framework.supervisor;

import framework.channel.MessageHandler;
import framework.registry.ActorRegistry;
import framework.spawn.ActorFactory;
import framework.supervisor.Supervisor;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SupervisorTest {
    private final static ActorRegistry ACTOR_REGISTRY = new ActorRegistry();
    private final static ActorFactory ACTOR_FACTORY = new ActorFactory("test.framework.test_actors");
    private final static MessageHandler MESSAGE_HANDLER = new MessageHandler(ACTOR_REGISTRY);
    private final static Supervisor SUPERVISOR = new Supervisor(ACTOR_FACTORY, ACTOR_REGISTRY, MESSAGE_HANDLER);

    @Test
    void shouldReturnUUIDOnSpawnActor(){
        Object obj = SUPERVISOR.spawnActor("TestActor");
        assertTrue(isUUID(obj));
    }

    private boolean isUUID(Object target) {
        return target instanceof UUID;
    }
}
