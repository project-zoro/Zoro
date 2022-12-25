import example.messages.Greetings;
import framework.ActorApp;
import framework.supervisor.SuperVisor;
import medium.messages.ReadClaps;
import medium.messages.RecordClaps;
import medium.messages.TrackBlog;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {

//        example();
//        serverToBlog();
        serverToUser();
    }

    public static void example(){
        SuperVisor superVisor = ActorApp.run("example.actors").superVisor();
        UUID recipientId = superVisor.spawnActor("ReceivingActor");
        UUID forwarderId = superVisor.spawnActor("Forwarder");

        superVisor.sendMessage(new Greetings("hello", recipientId), forwarderId);
    }

    public static void serverToBlog(){
        SuperVisor superVisor = ActorApp.run("medium.actors").superVisor();
        UUID serverId = superVisor.spawnActor("Server");
        UUID blogId = superVisor.spawnActor("Blog", 1);

        superVisor.sendMessage(new RecordClaps(1, 1, 10, serverId), blogId);
        superVisor.sendMessage(new ReadClaps(2, 1, serverId), blogId);
    }

    public static void serverToUser(){
        SuperVisor superVisor = ActorApp.run("medium.actors").superVisor();
        UUID serverId = superVisor.spawnActor("Server");
        UUID userId = superVisor.spawnActor("User", 1);
        // Creating new Blog
        superVisor.sendMessage(new TrackBlog(1, 1, 1, serverId), userId);
        // Authorization Issue check
        superVisor.sendMessage(new TrackBlog(2, 2, 1, serverId), userId);
        // Try creating an existing Blog
        superVisor.sendMessage(new TrackBlog(3, 1, 1, serverId), userId);
        // Record Claps through User Actor
        superVisor.sendMessage(new RecordClaps(4, 1, 5, serverId), userId);
        // Read Claps through User Actor after waiting for 1 minute to make sure that request 4 has been executed
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        superVisor.sendMessage(new ReadClaps(5, 1, serverId), userId);
    }
}