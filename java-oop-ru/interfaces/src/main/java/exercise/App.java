package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int count) {

        if (apartments.isEmpty()) {
            return new ArrayList<String>();
        }
        if (count > apartments.size()) {
            count = apartments.size();
        }

        return apartments.stream()
                         .sorted((home, anotherHome) -> home.compareTo(anotherHome))
                         .peek(System.out::println)
                         .limit(count)
                         .map(home -> home.toString())
                         .toList();
    }
}

// END
