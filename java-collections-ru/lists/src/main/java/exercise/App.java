package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

// BEGIN
class App {
  public static boolean scrabble(String symbols, String word) {

    if (symbols.isEmpty() || word.isEmpty()) {
      return false;
    }

    List<String> listSymbols = new ArrayList<>(Arrays.stream(symbols.toLowerCase().split("")).toList());
    List<String> listWordSymbols = new ArrayList<>(Arrays.stream(word.toLowerCase().split("")).toList());

    ListIterator<String> iter = listWordSymbols.listIterator();
    var element = "";

    while (iter.hasNext()) {
      element = iter.next();
      if (!listSymbols.contains(element)) {
        return false;
      }
      listSymbols.remove(element);
    }

    return true;
  }
}
//END
