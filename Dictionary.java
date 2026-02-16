import java.util.*;

public class Dictionary {
    private final List<String> words;

    public Dictionary(List<String> words) {
        this.words = new ArrayList<>(words);
    }

    public List<String> allWords() {
        return Collections.unmodifiableList(words);
    }

    public static Dictionary sample() {
        return new Dictionary(Arrays.asList(
            "apple", "Application", "apply", "app", "banana", "band", "Bandana",
            "can", "candy", "Candle", "cape", "cap", "caption", "dog", "Dogma"
        ));
    }
}
