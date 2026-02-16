import java.util.*;

public class WordFilterTest {

    public static void main(String[] args) {
        WordFilter wf = new WordFilter(Dictionary.sample());

        assertEq(
            wf.filter(FilterQuery.of("app", null, null, null, null, false, 10)),
            Arrays.asList("app", "apple", "Application", "apply"),
            "prefix=app, caseInsensitive"
        );

        assertEq(
            wf.filter(FilterQuery.of(null, "ana", null, null, null, false, 10)),
            Arrays.asList("banana", "Bandana"),
            "suffix=ana, caseInsensitive"
        );

        assertEq(
            wf.filter(FilterQuery.of(null, null, "and", null, null, false, 10)),
            Arrays.asList("band", "Bandana", "candy"),
            "contains=and, caseInsensitive"
        );

        assertEq(
            wf.filter(FilterQuery.of("ca", null, null, 3, 5, false, 10)),
            Arrays.asList("can", "candy", "cap", "cape"),
            "prefix=ca, len range 3..5, caseInsensitive"
        );

        assertEq(
            wf.filter(FilterQuery.of("ca", null, null, 3, 5, true, 10)),
            Arrays.asList("can", "candy", "cap", "cape"),
            "caseSensitive: Candle should not match prefix=ca"
        );

        assertEq(
            wf.filter(FilterQuery.of(null, null, null, null, null, false, 3)),
            Arrays.asList("app", "apple", "Application"),
            "limit=3, caseInsensitive sort"
        );

        System.out.println("ALL TESTS PASSED");
    }

    private static void assertEq(List<String> actual, List<String> expected, String name) {
        if (!actual.equals(expected)) {
            throw new AssertionError(
                "FAILED: " + name + "\nExpected: " + expected + "\nActual:   " + actual
            );
        }
        System.out.println("PASSED: " + name);
    }
}
