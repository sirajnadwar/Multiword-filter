import java.util.*;

public class WordFilter {
    private final Dictionary dictionary;

    public WordFilter(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> filter(FilterQuery q) {
        // TODO:
        // Implement filtering based on q fields:
        // prefix, suffix, contains, minLen, maxLen, caseSensitive
        // Then sort results:
        // - if caseSensitive: natural String order
        // - else: sort by lowercased value, tie-break by original
        // Then return up to q.limit results
        return Collections.emptyList();
    }

    // Helper for case-insensitive comparisons
    private boolean matches(String word, FilterQuery q) {
        // TODO: implement match logic
        return false;
    }
}
