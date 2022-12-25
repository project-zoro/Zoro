package medium.messages;

import framework.Message;

import java.util.UUID;

public record TrackBlog(int requestId, int userId, int blogId, UUID from) implements Message {
}
