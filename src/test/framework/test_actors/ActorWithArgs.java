package test.framework.test_actors;

import framework.Actor;
import framework.Message;

import java.util.UUID;

public class ActorWithArgs extends Actor {
    String abc;
    int number;

    public ActorWithArgs(String abc, int number) {
        this.abc = abc;
        this.number = number;
    }

    @Override
    public void onMessage(Message message, UUID senderId) {

    }
}
