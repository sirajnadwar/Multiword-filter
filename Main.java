import java.util.*;

public class Main {
    public static void main(String[] args) {
        WordFilter wf = new WordFilter(Dictionary.sample());

        List<String> r = wf.filter(FilterQuery.of("app", null, null, null, null, false, 10));
        System.out.println(r);
    }
}
