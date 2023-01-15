package vframework;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MethodHandler {
    private static final AtomicLong ROUND_ROBIN = new AtomicLong(0);
    private static final Map<Integer, Long> map = new ConcurrentHashMap<>();
    private static final int SIZE = QueueConfiguration.optimizedNumberOfQueues();
    private static final List<LinkedBlockingDeque<SendableMethod>> CHANNELS = IntStream.rangeClosed(0, SIZE)
            .mapToObj(__ -> new LinkedBlockingDeque<SendableMethod>(20))
            .collect(Collectors.toList());

    public MethodHandler() {
        var executorService = Executors.newFixedThreadPool(SIZE);
        IntStream.rangeClosed(0, SIZE)
                .mapToObj(CHANNELS::get)
                .forEach(channel -> executorService.execute(new MethodProcessor(channel)));
    }

    /**
     * @return True if enqueued
     */
    public boolean tryQueue(SendableMethod method) {
        long queue;

        if(map.containsKey(method.receiver())){
            queue = map.get(method.receiver());
        }else{
            queue = ROUND_ROBIN.incrementAndGet() & (SIZE - 1);
            map.put(method.receiver(), queue);
        }
        boolean queued = CHANNELS.get((int) queue).offer(method);
        if (queued) {
            System.out.println("Queued method " + method);
        }
        return queued;
    }
}
