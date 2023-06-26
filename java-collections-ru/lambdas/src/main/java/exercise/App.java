package exercise;

import java.util.Arrays;
import java.util.List;


// BEGIN
class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        if (image.length == 0) {
            return new String[0][];
        }

        List<List<String>> iLists = Arrays.asList(image).stream()
                                                        .map(line -> Arrays.asList(line))
                                                        .toList();

        return iLists.stream()
                        .map(line -> List.of(List.copyOf(line), line))
                        .flatMap(l -> l.stream())
                        .map(line -> line.stream()
                                        .map(element -> List.of(element, element))
                                        .flatMap(e -> e.stream())
                                        .toArray(String[]::new))
                        .toArray(String[][]::new);
    }
}
// END
