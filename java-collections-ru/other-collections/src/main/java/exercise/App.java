package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

// BEGIN
class App {
    private static final String ADDED = "added";
    private static final String DELETED = "deleted";
    private static final String CHANGED = "changed";
    private static final String UNCHANGED = "unchanged";
    public static Map<String, String> genDiff(Map<String, Object> firstDict,
                                              Map<String, Object> secondDict) {
        Set<String> keys = new TreeSet<>();
        keys.addAll(firstDict.keySet());
        keys.addAll(secondDict.keySet());

        Function<String, Map<String, String>> compareMaps = key -> {
            if (!firstDict.containsKey(key)) {
                return Map.of(key, ADDED);
            }
            if (!secondDict.containsKey(key)) {
                return Map.of(key, DELETED);
            }
            if (firstDict.get(key).equals(secondDict.get(key))) {
                return Map.of(key, UNCHANGED);
            }

            return Map.of(key, CHANGED);
        };

        return keys.stream()
                   .map(compareMaps)
                   .collect(LinkedHashMap::new,
                            LinkedHashMap::putAll,
                            LinkedHashMap::putAll);
    }
}
//END
