
package io.cdap.wrangler.api;

import java.util.HashMap;
import java.util.Map;

public class Row {
    private Map<String, Object> fields = new HashMap<>();

    public Row add(String key, Object value) {
        fields.put(key, value);
        return this;
    }

    public Object getValue(String key) {
        return fields.get(key);
    }

    public Map<String, Object> getFields() {
        return fields;
    }
}
