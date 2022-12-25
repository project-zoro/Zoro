package medium.actors;

import framework.Actor;
import framework.Message;
import medium.messages.RecordClaps;
import medium.messages.RespondClaps;
import medium.messages.Success;

import java.util.UUID;

public class Server extends Actor {
    @Override
    public void onMessage(Message message, UUID from) {
        if (message instanceof RespondClaps respondClaps) onRespondClaps(respondClaps);
        if (message instanceof Success success) System.out.println("Server: " + success);
    }

    private void onRespondClaps(RespondClaps respondClaps) {
        UUID forwardTo = respondClaps.from();
        System.out.println("Received Claps value: " + respondClaps.claps());

        sendMessage(new Success(respondClaps.requestId(), getId()), forwardTo);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " - " + this.getId();
    }
}
