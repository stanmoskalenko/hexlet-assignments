package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.List;
import java.time.LocalDate;

// BEGIN
class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        if (users.isEmpty()) {
            return new ArrayList<String>();
        }

        Function<Map<String, String>, LocalDate> getBirthday = man -> LocalDate.parse(man.get("birthday"));

        return users.stream()
                    .filter(user -> user.get("gender").equals("male"))
                    .sorted(Comparator.comparing(getBirthday))
                    .map(man -> man.get("name"))
                    .toList();
    }
}
// END
