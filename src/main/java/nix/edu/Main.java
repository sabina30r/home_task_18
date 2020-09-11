package nix.edu;

import nix.edu.service.Executor;

import java.io.File;
import java.util.concurrent.*;

public class Main {

    private static final String DEFAULT_OUTPUT_PATH = "output.txt";

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        File file = new File(DEFAULT_OUTPUT_PATH);
        Executor executor = new Executor(executorService, scheduler, file);
        executor.write();
        executor.shutdown();
    }

}
