package exercise;

import java.util.Arrays;
import java.util.logging.Logger;


public class MaxThread extends Thread {
    private int[] numbers;
    private Integer maxInt;

    public void init(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMaxInt() {
        return maxInt;
    }

    @Override
    public void run() {
        var logger = Logger.getLogger("AppLogger");
        logger.info("Thread " + getName() + " started");
        maxInt = Arrays.stream(numbers).max()
                .orElseThrow(() -> new RuntimeException("Max value not found"));

        if (isInterrupted()) {
            logger.info("Thread " + getName() + " finished");
        }
    }
}
