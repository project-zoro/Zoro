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

    /**
     * Takes up and invokes the first item that the specific channel received and then publishes the result using ResponseHandler.
     * The next item will be picked up only after the current one is executed.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                SendableMethod sendableMethod = channel.take();
                Object result = sendableMethod.method().invoke(sendableMethod.object(), sendableMethod.args());
                if(result != null) responseHandler.publishResponse(sendableMethod.requestID(), result);
            } catch (InterruptedException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
