package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;


// BEGIN
public class App {
    public static Map<String, String> compareMaps(Map<String, String> cond, Map<String, String> book) {
       List<Boolean> result = cond.entrySet()
                                  .stream()
                                  .map(c -> book.containsKey(c.getKey()) && book.containsValue(c.getValue()))
                                  .toList();

        if (result.contains(false)) {
            return new HashMap<>();
        }

        return book;
    }

    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {
        if (books.isEmpty() || where.isEmpty()) {
            return new ArrayList<>();
        }

        return books.stream()
                    .map(book -> compareMaps(where, book))
                    .filter(b -> !b.isEmpty())
                    .toList();
    }
}
//END
