package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;


// BEGIN
public class App {
    public static Map<String,String> getComparedMap(Map<String,String> cond, Map<String,String> book) {
       List<Boolean> result = cond.entrySet()
                                  .stream()
                                  .map(c -> book.containsKey(c.getKey()) && book.containsValue(c.getValue()))
                                  .toList();

        if (result.contains(false)) {
            return new HashMap<>();
        }

        return book;
    }

    public static List<Map<String,String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {
        List<Map<String,String>> result = new ArrayList<>();

        if (books.isEmpty() || where.isEmpty()) {
            return result;
        }

        result = books.stream()
                      .map(book -> getComparedMap(where, book))
                      .filter(b -> !b.isEmpty())
                      .collect(Collectors.toList());

        return result;
    }
}
//END
