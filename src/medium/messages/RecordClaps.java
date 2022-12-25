package medium.messages;

import framework.Message;

import java.util.UUID;

public record RecordClaps(int requestId, int blogId, int claps, UUID from) implements Message {
}

