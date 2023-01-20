package example.vframework.actors;

import vframework.Actor;

public class GreeterActor implements IGreeterActor, Actor {
    public GreeterActor(){}
    @Override
    public Object greet(String str) {
        return("Hello " + str);
    }
}