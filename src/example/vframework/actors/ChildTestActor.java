package example.vframework.actors;

import vframework.Actor;

public class ChildTestActor implements IChildTestActor, Actor {
    public ChildTestActor(){}
    @Override
    public void display(String str) {
        System.out.println(str);
    }
}
