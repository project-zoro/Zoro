package medium.messages;

import framework.Message;

import java.util.UUID;

public record Success(int requestId, UUID from) implements Message {
}
