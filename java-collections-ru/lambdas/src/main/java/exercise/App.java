package exercise;

import java.util.Arrays;
import java.util.stream.Stream;


// BEGIN
class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        if (image.length == 0) {
            return new String[0][];
        }

        return Arrays.stream(image)
                     .flatMap(line -> Stream.of(line, line))
                     .map(line -> Arrays.stream(line)
                                        .flatMap(element -> Stream.of(element, element))
                                        .toArray(String[]::new))
                    .toArray(String[][]::new);

    }
}
// END
