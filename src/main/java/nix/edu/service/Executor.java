package nix.edu.service;

import nix.edu.consumer.Consumer;
import nix.edu.producer.Producer;

import java.io.File;
import java.util.concurrent.*;

public class Executor {
    private final BlockingQueue<String> stringBlockingQueue = new LinkedBlockingDeque<>();
    private final ExecutorService executorService;
    private final ScheduledExecutorService scheduler;
    private final File file;
    private Future<?> futureTask;

    public Executor(ExecutorService executorService, ScheduledExecutorService scheduler, File file) {
        this.executorService = executorService;
        this.scheduler = scheduler;
        this.file = file;
    }

    public void write() {
        Producer producer = new Producer(stringBlockingQueue);
        Consumer consumer = new Consumer(stringBlockingQueue, file);
        futureTask = executorService.submit(producer);
        scheduler.scheduleWithFixedDelay(consumer, 0, 1, TimeUnit.SECONDS);
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            futureTask.get();
            while (true) {
                if (stringBlockingQueue.size() == 0) {
                    scheduler.shutdown();
                    break;
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
