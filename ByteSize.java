
package io.cdap.wrangler.api.parser;

public class ByteSize extends Token {
    private final long bytes;

    public ByteSize(String value) {
        super(value);
        this.bytes = parseToBytes(value);
    }

    private long parseToBytes(String input) {
        input = input.trim().toLowerCase();
        double num = Double.parseDouble(input.replaceAll("[a-zA-Z]+", ""));
        if (input.endsWith("kb")) return (long) (num * 1024);
        if (input.endsWith("mb")) return (long) (num * 1024 * 1024);
        if (input.endsWith("gb")) return (long) (num * 1024 * 1024 * 1024);
        return (long) num; // bytes by default
    }

    public long getBytes() {
        return bytes;
    }
}
