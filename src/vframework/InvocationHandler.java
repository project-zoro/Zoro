package vframework;

import java.lang.reflect.Method;
import java.util.UUID;

public record InvocationHandler(Object actor, MethodPublisher methodPublisher) implements java.lang.reflect.InvocationHandler {
    @Override
    public UUID invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Inside invoke: " + method.getName());
        UUID requestID = UUID.randomUUID();
        methodPublisher.tryQueue(new SendableMethod(method, actor.hashCode(), requestID, actor, args));
        return requestID;
    }
}
