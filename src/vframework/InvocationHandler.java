package vframework;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 *Handles the method invocation of dynamic proxy classes.
 */
public record InvocationHandler(Object actor, MethodPublisher methodPublisher) implements java.lang.reflect.InvocationHandler {

    /**
     * Publishes the method which is invoked on proxy instance to MethodPublisher
     * where this will be invoked on a later stage by the MethodProcessor.
     */
    @Override
    public UUID invoke(Object proxy, Method method, Object[] args) throws Throwable {
        UUID requestID = UUID.randomUUID();
        methodPublisher.tryQueue(new SendableMethod(method, actor.hashCode(), requestID, actor, args));
        return requestID;
    }
}
