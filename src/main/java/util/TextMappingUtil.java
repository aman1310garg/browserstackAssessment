package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextMappingUtil {
    public static Map<String, Integer> checkRepeatedWords(List<String> headers) {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String header : headers) {
            for (String word : header.split("\\s+")) {
                word = word.toLowerCase();
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }
        return wordCount;
    }
}
