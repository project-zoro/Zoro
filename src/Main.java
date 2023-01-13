import example.framework.messages.Greetings;
import example.vframework.actors.ITestActor;
import framework.ActorApp;
import framework.supervisor.Supervisor;
import medium.messages.ReadClaps;
import medium.messages.RecordClaps;
import medium.messages.TrackBlog;
import vframework.Client;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {

//        exampleFramework();
        exampleVFramework();
//        serverToBlog();
//        serverToUser();
    }

    public static void exampleFramework(){
        Supervisor superVisor = ActorApp.run("example.framework.actors").superVisor();
        UUID recipientId = superVisor.spawnActor("ReceivingActor");
        UUID forwarderId = superVisor.spawnActor("Forwarder");

        superVisor.sendMessage(new Greetings("hello", recipientId), forwarderId);
    }

    public static void exampleVFramework(){
        ITestActor testActor = Client.init("example").actorFactory().createProxy(ITestActor.class);
        testActor.display("This is test actor");
    }

    public static void serverToBlog(){
        Supervisor superVisor = ActorApp.run("medium.actors").superVisor();
        UUID serverId = superVisor.spawnActor("Server");
        UUID blogId = superVisor.spawnActor("Blog", 1);

        superVisor.sendMessage(new RecordClaps(1, 1, 10, serverId), blogId);
        superVisor.sendMessage(new ReadClaps(2, 1, serverId), blogId);
    }

    public static void serverToUser(){
        Supervisor supervisor = ActorApp.run("medium.actors").superVisor();
        UUID serverId = supervisor.spawnActor("Server");
        UUID userId = supervisor.spawnActor("User", 1);
        // Creating new Blog
        supervisor.sendMessage(new TrackBlog(1, 1, 1, serverId), userId);
        // Authorization Issue check
        supervisor.sendMessage(new TrackBlog(2, 2, 1, serverId), userId);
        // Try creating an existing Blog
        supervisor.sendMessage(new TrackBlog(3, 1, 1, serverId), userId);
        // Record Claps through User Actor
        supervisor.sendMessage(new RecordClaps(4, 1, 15, serverId), userId);
        // Read Claps through User Actor after waiting for 1 minute to make sure that request 4 has been executed
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        supervisor.sendMessage(new ReadClaps(5, 1, serverId), userId);
    }
}