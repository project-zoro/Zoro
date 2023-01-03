package test.framework;

import framework.Actor;
import framework.ActorApp;
import framework.supervisor.DefaultSupervisor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import test.framework.test_actors.TestActor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActorTest {
    @BeforeAll
    static void initSupervisor() {
        ActorApp._setSuperVisor(DefaultSupervisor.actors("test.framework.test_actors"));
    }

    @Test
    void actorsShouldBeAbleToCreateActors() {
        TestActor testActor = new TestActor();
        UUID uuid = testActor.spawnActor("TestActor");

        assertNotNull(uuid);
        assertTrue(isActor(testActor));
    }

    private boolean isActor(Object target) {
        return target instanceof Actor;
    }
}
