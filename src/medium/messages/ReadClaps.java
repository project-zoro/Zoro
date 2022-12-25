package medium.messages;

import framework.Message;

import java.util.UUID;

public record ReadClaps(int requestId, int blogId, UUID from) implements Message {
}
