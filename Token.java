
package io.cdap.wrangler.api.parser;

public abstract class Token {
    protected String value;
    public Token(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
