package test.framework.registry;

import framework.Actor;
import framework.registry.ActorRegistry;
import framework.spawn.ActorFactory;
import org.junit.jupiter.api.Test;
import test.framework.test_actors.TestActor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ActorRegistryTest {
    private final static ActorRegistry ACTOR_REGISTRY = new ActorRegistry();
    private final static ActorFactory ACTOR_FACTORY = new ActorFactory("test.framework.test_actors");
    private final static TestActor TEST_ACTOR = ACTOR_FACTORY.loadActor(TestActor.class.getSimpleName());

    @Test
    void shouldReturnUUIDOnRegisterActor(){
        Object obj = ACTOR_REGISTRY.registerActor(TEST_ACTOR);
        assertTrue(isUUID(obj));
    }

    private boolean isUUID(Object target) {
        return target instanceof UUID;
    }
}
