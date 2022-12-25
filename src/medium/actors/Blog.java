package medium.actors;

import framework.Actor;
import framework.Message;
import medium.messages.ReadClaps;
import medium.messages.RecordClaps;
import medium.messages.RespondClaps;
import medium.messages.Success;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Blog extends Actor{

    private final AtomicInteger blogId = new AtomicInteger(-1);

    private final AtomicInteger lastClapsReading = new AtomicInteger(0);

    public Blog(int blogId) {
        this.blogId.set(blogId);
    }

    @Override
    public void onMessage(Message message, UUID from) {
        if(message instanceof RecordClaps recordClaps) onRecordClaps(recordClaps);
        if(message instanceof ReadClaps readClaps) onReadClaps(readClaps);
        if(message instanceof Success success) System.out.println("Blog (" + blogId + "): " + success);
    }

    private void onReadClaps(ReadClaps readClaps) {
        UUID forwardTo = readClaps.from();
        System.out.println("BlogId (" + blogId + "): " + readClaps);

        sendMessage(new RespondClaps(readClaps.requestId(), lastClapsReading.get(), getId()), forwardTo);
    }

    private void onRecordClaps(RecordClaps recordClaps) {
        UUID forwardTo = recordClaps.from();
        System.out.println("BlogId (" + blogId + "): " + recordClaps);
        lastClapsReading.set(recordClaps.claps());

        sendMessage(new Success(recordClaps.requestId(), getId()), forwardTo);
    }
}