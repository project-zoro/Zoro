package test.vframework;

import example.vframework.actors.IGreeterActor;
import org.junit.jupiter.api.Test;
import vframework.Client;

import static org.junit.jupiter.api.Assertions.*;

public class BasicActorTests {

    @Test
    void TestBasicActorRequestResponse() {
        IGreeterActor greeterActor = Client.init("example").actorFactory().createProxy(IGreeterActor.class);
        Object result = greeterActor.greet("Zoro");
        assertEquals("Hello Zoro", Client._getActorFactory().getResponse(result));
    }
}
