package example.framework.actors;

import example.framework.messages.Greetings;
import framework.Actor;
import framework.Message;

import java.util.UUID;

public class ReceivingActor extends Actor {
    @Override
    public void onMessage(Message message, UUID from) {
        if (message instanceof Greetings greetings) {
            System.out.println("Receiver: " + greetings);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " - " + this.getId();
    }
}