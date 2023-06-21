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
      StringBuilder result = new StringBuilder("{\n");

      for (var key: words.keySet()) {
        result.append("  " + key + ":" + " " + words.get(key) + "\n");
      }

      return result.append("}").toString();
    }
  }

  public static Map<String,Integer> getWordCount(String sentence) {
    Map<String,Integer> wordsCount = new HashMap<>();

    if (sentence.isBlank()) {
      return wordsCount;
    }

    List<String> words = new ArrayList<>(Arrays.stream(sentence.split(" ")).toList());
    var count = 0;

    for (var word: words) {
      count = (int) words.stream().filter(w -> w.equals(word)).count();
      wordsCount.put(word, count);
    }

    return wordsCount;
  }
}
//END
