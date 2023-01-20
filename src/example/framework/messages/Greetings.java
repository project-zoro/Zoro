package example.framework.messages;

import framework.Message;

import java.util.UUID;

public record Greetings(String greeting, UUID from) implements Message {

    public void greet() {
        System.out.println(greeting);
    }
}