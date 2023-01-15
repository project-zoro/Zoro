package vframework;

import java.lang.reflect.Method;

public record SendableMethod(Method method, Integer receiver, Object object, Object[] args) {
}
