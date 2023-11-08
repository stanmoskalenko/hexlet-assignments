package exercise;


import java.util.logging.Logger;

public class ListThread extends Thread {
    private final SafetyList list;
    private final Logger logger = Logger.getLogger("AppLogger");

    public ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        logger.info("Thread " + getName() + " started");
        for (var i = 0; i < 1000; i++) {
            list.add(i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (isInterrupted()) {
            logger.info("Thread " + getName() + " finished");
        }
    }
}
