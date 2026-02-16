public class FilterQuery {
    // Optional text filters (null means "do not filter by this field").
    public final String prefix;
    public final String suffix;
    public final String contains;

    // Optional length bounds (inclusive). Null means "no bound".
    public final Integer minLen;
    public final Integer maxLen;

    // Sorting and matching mode.
    public final boolean caseSensitive;

    // Max number of rows to return (0 means return nothing).
    public final int limit;

    private FilterQuery(
        String prefix,
        String suffix,
        String contains,
        Integer minLen,
        Integer maxLen,
        boolean caseSensitive,
        int limit
    ) {
        if (minLen != null && minLen < 0) {
            throw new IllegalArgumentException("minLen must be >= 0");
        }
        if (maxLen != null && maxLen < 0) {
            throw new IllegalArgumentException("maxLen must be >= 0");
        }
        if (minLen != null && maxLen != null && minLen > maxLen) {
            throw new IllegalArgumentException("minLen must be <= maxLen");
        }
        if (limit < 0) {
            throw new IllegalArgumentException("limit must be >= 0");
        }

        this.prefix = prefix;
        this.suffix = suffix;
        this.contains = contains;
        this.minLen = minLen;
        this.maxLen = maxLen;
        this.caseSensitive = caseSensitive;
        this.limit = limit;
    }

    public static FilterQuery of(
        String prefix,
        String suffix,
        String contains,
        Integer minLen,
        Integer maxLen,
        boolean caseSensitive,
        int limit
    ) {
        return new FilterQuery(prefix, suffix, contains, minLen, maxLen, caseSensitive, limit);
    }
}

