package test.framework.test_actors;

import framework.Actor;
import framework.Message;

import java.util.UUID;

public class CrashOnInitiationActor extends Actor {
    public CrashOnInitiationActor() {
        throw new RuntimeException();
    }

    @Override
    public void onMessage(Message message, UUID senderId) {

    }
}
