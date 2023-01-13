package vframework;

import java.lang.reflect.Method;

public record InvocationHandler(Object actor) implements java.lang.reflect.InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Inside invoke: " + method.getName());
        method.invoke(actor, args);
        return null;
    }
}
