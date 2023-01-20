package example.vframework.actors;

import vframework.Actor;

public class AdditionActor implements IAdditionActor, Actor {
    int Balance = 0;

    @Override
    public Object add(int number) {
        return Balance += number;
    }
}
