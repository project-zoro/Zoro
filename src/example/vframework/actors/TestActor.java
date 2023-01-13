package example.vframework.actors;

import vframework.Actor;
import vframework.Client;

public class TestActor implements ITestActor, Actor {
    public TestActor(){}
    @Override
    public void display(String str) {
        System.out.println(str);
        IChildTestActor childTestActor = Client._getActorFactory().createProxy(IChildTestActor.class);
        childTestActor.display("This is child test actor");
    }
}
