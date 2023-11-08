package exercise;

import java.util.HashMap;
import java.util.Map;

class App {

    public static Map<String, Integer> getMinMax(int[] numbers) {
        Map<String, Integer> result = new HashMap<>();
        MinThread minThread = new MinThread(numbers);
        MaxThread maxThread = new MaxThread(numbers);

        minThread.start();
        maxThread.start();

        try {
            minThread.join();
            maxThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        result.put("min", minThread.getMinInt());
        result.put("max", maxThread.getMaxInt());

        return result;
    }


}
