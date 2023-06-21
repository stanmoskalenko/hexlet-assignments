package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
class App {
  public static String toString(Map<String, Integer> words) {
    if (words.isEmpty()) {
      return "{}";
    } else {
      var result = "";
      for (var key: words.keySet()) {
        result += "  " + key + ":" + " " + words.get(key) + "\n";
      }

      return "{\n" + result + "}";
    }
  }

  public static Map<String,Integer> getWordCount(String sentence) {
    if (sentence.isBlank()) {
      return new HashMap<String,Integer>();
    }

    List<String> words = new ArrayList<>(Arrays.stream(sentence.split(" ")).toList());
    Map<String,Integer> wordsCount = new HashMap<>();
    var count = 0;

    for (var word: words) {
      count = (int) words.stream().filter(w -> w.equals(word)).count();
      wordsCount.put(word, count);
    }

    return wordsCount;
  }
}
//END
