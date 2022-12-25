package framework.channel;

import framework.processing.MessageProcessor;
import framework.registry.ActorRegistry;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MessageHandler {
    private static final AtomicLong ROUND_ROBIN = new AtomicLong(0);
    private static final int SIZE = QueueConfiguration.optimizedNumberOfQueues();
    private static final List<LinkedBlockingDeque<SendableMessage>> CHANNELS = IntStream.rangeClosed(0, SIZE)
            .mapToObj(__ -> new LinkedBlockingDeque<SendableMessage>(20))
            .collect(Collectors.toList());

    public MessageHandler(ActorRegistry actorRegistry) {
        var executorService = Executors.newFixedThreadPool(SIZE);
        IntStream.rangeClosed(0, SIZE)
                .mapToObj(CHANNELS::get)
                .forEach(channel -> executorService.execute(new MessageProcessor(channel, actorRegistry)));
    }

    /**
     * @return True if enqueued
     */
    public boolean tryQueue(SendableMessage message) {
        long queue = ROUND_ROBIN.incrementAndGet() & (SIZE - 1);
        boolean queued = CHANNELS.get((int) queue).offer(message);
        if (queued) {
            System.out.println("Queued message " + message);
        }
        return queued;
    }
}
