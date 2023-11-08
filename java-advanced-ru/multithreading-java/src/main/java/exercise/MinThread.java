package exercise;

import java.util.Arrays;
import java.util.logging.Logger;

public class MinThread extends Thread {
    private int[] numbers;
    private Integer minInt;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMinInt() {
        return minInt;
    }

    @Override
    public void run() {
        var logger = Logger.getLogger("AppLogger");
        logger.info("Thread " + getName() + " started");
        minInt = Arrays.stream(numbers).min()
                .orElseThrow(() -> new RuntimeException("Min value not found"));
        if (isInterrupted()) {
            logger.info("Thread " + getName() + " finished");
        }
    }
}
