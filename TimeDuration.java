
package io.cdap.wrangler.api.parser;

public class TimeDuration extends Token {
    private final long nanos;

    public TimeDuration(String value) {
        super(value);
        this.nanos = parseToNanos(value);
    }

    private long parseToNanos(String input) {
        input = input.trim().toLowerCase();
        double num = Double.parseDouble(input.replaceAll("[a-zA-Z]+", ""));
        if (input.endsWith("ms")) return (long) (num * 1_000_000);
        if (input.endsWith("s")) return (long) (num * 1_000_000_000);
        if (input.endsWith("m")) return (long) (num * 60 * 1_000_000_000L);
        return (long) num; // assume nanoseconds
    }

    public long getNanos() {
        return nanos;
    }
}
