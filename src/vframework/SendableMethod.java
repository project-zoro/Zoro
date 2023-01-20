package vframework;

import java.lang.reflect.Method;
import java.util.UUID;

public record SendableMethod(Method method, Integer receiver, UUID requestID, Object object, Object[] args) {
}
