package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN
class App {
    private static final String ADDED = "added";
    private static final String DELETED = "deleted";
    private static final String CHANGED = "changed";
    private static final String UNCHANGED = "unchanged";
    public static Map<String, String> genDiff(Map<String, Object> firstDict,
                                              Map<String, Object> secondDict) {
        Set<String> keys = new TreeSet<>();
        Map<String, String> comparedMaps = new LinkedHashMap<>();
        keys.addAll(firstDict.keySet());
        keys.addAll(secondDict.keySet());

        for (var key : keys) {
            if (!firstDict.containsKey(key)) {
                comparedMaps.put(key, ADDED);
            } else if (firstDict.get(key).equals(secondDict.get(key))) {
                comparedMaps.put(key, UNCHANGED);
            } else if (!secondDict.containsKey(key)) {
                comparedMaps.put(key, DELETED);
            } else {
                comparedMaps.put(key, CHANGED);
            }
        }

        return comparedMaps;
    }
}
//END
