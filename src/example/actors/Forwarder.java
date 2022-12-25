package example.actors;

import example.messages.Greetings;
import framework.Actor;
import framework.Message;

import java.util.UUID;

public class Forwarder extends Actor {

    @Override
    public void onMessage(Message message, UUID from) {
        if (message instanceof Greetings greetings) {
            UUID forwardTo = greetings.from();
            System.out.println("Forwarder: " + greetings);
            sendMessage(new Greetings("Thanks for the message!", getId()), forwardTo);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " - " + this.getId();
    }
}
