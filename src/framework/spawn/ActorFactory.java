package framework.spawn;

import framework.Actor;
import framework.spawn.exceptions.ActorInitiationException;
import framework.spawn.exceptions.UnrecoverableException;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ActorFactory {

    private final String classPackagePath;

    public ActorFactory(String classPackagePath) {
        this.classPackagePath = classPackagePath + ".";
    }

    public <T extends Actor> T loadActor(String actorName) {
        try {
            Class<?> clazz = Class.forName(classPackagePath + actorName);
            Constructor<?>[] constructors = clazz.getConstructors();
            for (Constructor<?> constructor : constructors) {
                if (constructor.getParameterCount() == 0) {
                    try {
                        return (T) constructor.newInstance();
                    } catch (Exception e) {
                        throw new UnrecoverableException(e);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            throw new UnrecoverableException(e);
        }
        throw new ActorInitiationException();
    }

    public <T extends Actor> T loadActor(String actorName, Object... args) {
        try {
            Class<?>[] argumentType = getArgumentTypes(args);
            Class<?> clazz = Class.forName(classPackagePath + actorName);
            Constructor<?> constructor = clazz.getConstructor(argumentType);
            try {
                return (T) constructor.newInstance(args);
            } catch (Exception e) {
                throw new UnrecoverableException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new UnrecoverableException(e);
        } catch (NoSuchMethodException e) {
            throw new UnrecoverableException("No such constructor, arguments provided" + Arrays.toString(args), e);
        }
    }

    private static final Map<Class<?>, Class<?>> BOXED_TYPE_CONVERTER = new HashMap<>();

    static {
        BOXED_TYPE_CONVERTER.put(Integer.class, int.class);
        BOXED_TYPE_CONVERTER.put(Double.class, double.class);
        BOXED_TYPE_CONVERTER.put(Float.class, float.class);
        BOXED_TYPE_CONVERTER.put(Long.class, long.class);
        BOXED_TYPE_CONVERTER.put(Character.class, char.class);
        BOXED_TYPE_CONVERTER.put(Byte.class, byte.class);
        BOXED_TYPE_CONVERTER.put(Short.class, short.class);
        BOXED_TYPE_CONVERTER.put(Boolean.class, boolean.class);
    }

    private Class<?>[] getArgumentTypes(Object... args) {
        var argTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            Class<?> aClass = args[i].getClass();
            argTypes[i] = BOXED_TYPE_CONVERTER.getOrDefault(aClass, aClass);
        }
        return argTypes;
    }

}