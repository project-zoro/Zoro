package medium.messages;

import framework.Message;

import java.util.UUID;

public record RespondClaps(int requestId, int claps, UUID from) implements Message {
}
