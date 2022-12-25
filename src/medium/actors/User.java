package medium.actors;

import framework.Actor;
import framework.Message;
import medium.messages.ReadClaps;
import medium.messages.RecordClaps;
import medium.messages.Success;
import medium.messages.TrackBlog;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class User extends Actor {

    private final AtomicInteger userId = new AtomicInteger(-1);

    private final ConcurrentHashMap<Integer, UUID> blogIdToActor = new ConcurrentHashMap<Integer, UUID>();

    public User(int userId){
        this.userId.set(userId);
    }

    @Override
    public void onMessage(Message message, UUID senderId) {
        if(message instanceof TrackBlog trackBlog) onTrackBlog(trackBlog);
        if(message instanceof RecordClaps recordClaps) onRecordClaps(recordClaps);
        if(message instanceof ReadClaps readClaps) onReadClaps(readClaps);
//        if(message instanceof Success success) System.out.println("User: " + success);
    }

    private void onReadClaps(ReadClaps readClaps) {
        UUID forwardTo = blogIdToActor.get(readClaps.blogId());
        sendMessage(readClaps, forwardTo);
    }

    private void onRecordClaps(RecordClaps recordClaps) {
        UUID forwardTo = blogIdToActor.get(recordClaps.blogId());
        sendMessage(recordClaps, forwardTo);
    }

    private void onTrackBlog(TrackBlog trackBlog){
        if(this.userId.get() == trackBlog.userId()){
            UUID blogId = blogIdToActor.get(trackBlog.blogId());
            UUID forwardTo = trackBlog.from();

            if(blogId != null){
                sendMessage(new Success(trackBlog.requestId(), getId()), forwardTo);
            }else{
                System.out.println("Creating blog actor for blogId: " + trackBlog.blogId());

                blogId = this.spawnActor("Blog", trackBlog.blogId());
                blogIdToActor.put(trackBlog.blogId(), blogId);

                sendMessage(new Success(trackBlog.requestId(), getId()), forwardTo);
            }
        }else {
            System.out.println("Warning! This actor is responsible for userId: " + this.userId.get() + " but it received request for userId: " + trackBlog.userId());
        }
    }
}
