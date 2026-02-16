import java.util.*;

public class WordFilter {
    private final Dictionary dictionary;

    public WordFilter(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> filter(FilterQuery q) {
        if (q == null) {
            throw new IllegalArgumentException("FilterQuery cannot be null");
        }

        List<String> filtered = new ArrayList<>();
        for (String word : dictionary.allWords()) {
            if (matches(word, q)) {
                filtered.add(word);
            }
        }

        filtered.sort(comparatorFor(q.caseSensitive));

        int end = Math.min(q.limit, filtered.size());
        if (end == 0) {
            return Collections.emptyList();
        }
        return new ArrayList<>(filtered.subList(0, end));
    }

    // Main helper: checks both length and text constraints for one word.
    private boolean matches(String word, FilterQuery q) {
        if (!matchesLength(word, q.minLen, q.maxLen)) {
            return false;
        }
        return matchesText(word, q);
    }

    // Keeps the length logic isolated and easy to read.
    private boolean matchesLength(String word, Integer minLen, Integer maxLen) {
        int len = word.length();
        if (minLen != null && len < minLen) {
            return false;
        }
        if (maxLen != null && len > maxLen) {
            return false;
        }
        return true;
    }

    // Handles prefix/suffix/contains using case mode from the query.
    private boolean matchesText(String word, FilterQuery q) {
        String normalizedWord = normalize(word, q.caseSensitive);

        if (q.prefix != null) {
            String normalizedPrefix = normalize(q.prefix, q.caseSensitive);
            if (!normalizedWord.startsWith(normalizedPrefix)) {
                return false;
            }
        }

        if (q.suffix != null) {
            String normalizedSuffix = normalize(q.suffix, q.caseSensitive);
            if (!normalizedWord.endsWith(normalizedSuffix)) {
                return false;
            }
        }

        if (q.contains != null) {
            String normalizedContains = normalize(q.contains, q.caseSensitive);
            if (!normalizedWord.contains(normalizedContains)) {
                return false;
            }
        }

        return true;
    }

    // Case-insensitive mode always uses Locale.ROOT for stable behavior.
    private String normalize(String value, boolean caseSensitive) {
        if (caseSensitive) {
            return value;
        }
        return value.toLowerCase(Locale.ROOT);
    }

    // Comparator required by the TODO rules.
    private Comparator<String> comparatorFor(boolean caseSensitive) {
        if (caseSensitive) {
            return Comparator.naturalOrder();
        }
        return Comparator.comparing((String s) -> s.toLowerCase(Locale.ROOT))
            .thenComparing(Comparator.naturalOrder());
    }
}
