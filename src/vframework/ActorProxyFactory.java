package vframework;

import org.reflections.Reflections;

import java.lang.reflect.Proxy;

public class ActorProxyFactory{
    String namespace;
    MethodHandler methodHandler = new MethodHandler();

    public ActorProxyFactory(String namespace){
        this.namespace = namespace;
    }

    private <T extends Actor> T createProxyInternal(Class<?> interfaceClass){
        Reflections reflections = new Reflections(namespace);

        Class<?> clazz = (Class<?>)reflections.getSubTypesOf(interfaceClass).toArray()[0];
        T original = null;

        try {
            original = (T) clazz.getConstructors()[0].newInstance();
        }catch (Exception e){
            System.out.println(e);
        }

        java.lang.reflect.InvocationHandler handler = new vframework.InvocationHandler(original, methodHandler);

        T proxyClass = (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class[] { interfaceClass },
                handler);
        System.out.println("Proxy object created successfully for " + clazz);

        return proxyClass;
    }

    public <T extends ActorWithNoKey> T createProxy(Class<?> interfaceClass){
        return createProxyInternal(interfaceClass);
    }
}
