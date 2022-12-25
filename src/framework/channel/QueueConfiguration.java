package framework.channel;

public final class QueueConfiguration {

    public static int optimizedNumberOfQueues() {
        int cores = Runtime.getRuntime().availableProcessors();
        if (cores < 2) {
            return 2;
        }
        cores = cores - 1;
        cores |= cores >> 1;
        cores |= cores >> 2;
        cores |= cores >> 4;
        cores |= cores >> 8;
        cores |= cores >> 16;
        return cores + 1;
    }

    private QueueConfiguration() {
    }
}
