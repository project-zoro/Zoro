package vframework;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class MethodProcessor implements Runnable {
    private final BlockingDeque<SendableMethod> channel;

    public MethodProcessor(LinkedBlockingDeque<SendableMethod> channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                SendableMethod sendableMethod = channel.take();
                sendableMethod.method().invoke(sendableMethod.object(), sendableMethod.args());
            } catch (InterruptedException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
