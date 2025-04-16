
package io.cdap.wrangler.api.executor;

import io.cdap.wrangler.api.Row;
import java.util.List;

public interface Directive {
    void initialize(String[] args);
    List<Row> execute(List<Row> rows);
}
