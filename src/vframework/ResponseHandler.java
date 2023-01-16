package vframework;

import framework.Actor;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ResponseHandler {
    private static final Map<UUID, Object> map = new ConcurrentHashMap<>();

    public void publishResponse(UUID requestID, Object response) {
        map.put(requestID, response);
    }

    public Object consumeResponse(Object uuid) {
        return map.remove((UUID)uuid);
    }
}