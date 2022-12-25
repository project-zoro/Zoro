package framework.channel;

import framework.Message;

import java.util.UUID;

public record SendableMessage(Message message, UUID receiver, UUID sender) {
}
