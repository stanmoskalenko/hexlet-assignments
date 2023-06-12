package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

// BEGIN
class App {
    public static boolean scrabble(String randomSymbols, String word) {

        if (randomSymbols.isEmpty() || word.isEmpty()) {
            return false;
        }

        List<String> listRandomSymbols = new ArrayList<>(Arrays.stream(randomSymbols.toLowerCase().split("")).toList());
        List<String> listWordSymbols = new ArrayList<>(Arrays.stream(word.toLowerCase().split("")).toList());
        ArrayList<String> result = new ArrayList<>();

        ListIterator<String> iter = listWordSymbols.listIterator();
        var element = "";

        while (iter.hasNext()) {
            element = iter.next();
            if (listRandomSymbols.contains(element)) {
                listRandomSymbols.remove(element);
                result.add(element);
            }
        }

        return String.join("", result).equalsIgnoreCase(word);
    }
}
//END
