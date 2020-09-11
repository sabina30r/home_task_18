package nix.edu.consumer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;


public class Consumer implements Runnable {

    private final BlockingQueue<String> stringBlockingQueue;
    private final File file;

    public Consumer(BlockingQueue<String> stringBlockingQueue, File file) {
        this.stringBlockingQueue = stringBlockingQueue;
        this.file = file;
    }

    @Override
    public void run() {
        if (stringBlockingQueue.size() != 0) {
            try (PrintStream printStream = new PrintStream(file)) {
                String string = stringBlockingQueue.poll();
                printStream.print(string);
            } catch (FileNotFoundException e) {
                System.out.println("There is no such file");
            }
        }
    }
}


