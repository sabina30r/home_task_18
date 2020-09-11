package nix.edu.producer;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue<String> stringBlockingQueue;

    public Producer(BlockingQueue<String> stringBlockingQueue) {
        this.stringBlockingQueue = stringBlockingQueue;
    }

    @Override
    public void run() {
        String string;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            string = scanner.next();
            if (!string.equalsIgnoreCase("quit")) {
                stringBlockingQueue.add(string);
            } else {
                break;
            }
        }
    }
}
