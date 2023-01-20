package example.vframework.actors;

import vframework.ActorWithNoKey;

public interface IGreeterActor extends ActorWithNoKey {
    Object greet(String str);
}