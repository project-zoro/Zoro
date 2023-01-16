package vframework;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class MethodProcessor implements Runnable {
    private final BlockingDeque<SendableMethod> channel;
    private final ResponseHandler responseHandler;

    public MethodProcessor(LinkedBlockingDeque<SendableMethod> channel, ResponseHandler responseHandler) {
        this.channel = channel;
        this.responseHandler = responseHandler;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                SendableMethod sendableMethod = channel.take();
                Object result = sendableMethod.method().invoke(sendableMethod.object(), sendableMethod.args());
                responseHandler.publishResponse(sendableMethod.requestID(), result);
            } catch (InterruptedException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
