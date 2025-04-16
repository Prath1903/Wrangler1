
package io.cdap.wrangler.parser.directives.aggregate;

import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.executor.Directive;
import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.TimeDuration;
import java.util.*;

public class AggregateStats implements Directive {
    private String sizeColumn, timeColumn, outputSizeColumn, outputTimeColumn;
    private long totalBytes = 0;
    private long totalNanos = 0;
    private int rowCount = 0;

    public void initialize(String[] args) {
        this.sizeColumn = args[0];
        this.timeColumn = args[1];
        this.outputSizeColumn = args[2];
        this.outputTimeColumn = args[3];
    }

    @Override
    public List<Row> execute(List<Row> rows) {
        for (Row row : rows) {
            String sizeVal = (String) row.getValue(sizeColumn);
            String timeVal = (String) row.getValue(timeColumn);
            totalBytes += new ByteSize(sizeVal).getBytes();
            totalNanos += new TimeDuration(timeVal).getNanos();
            rowCount++;
        }

        double totalMB = totalBytes / (1024.0 * 1024);
        double totalSec = totalNanos / 1_000_000_000.0;

        Row result = new Row();
        result.add(outputSizeColumn, totalMB);
        result.add(outputTimeColumn, totalSec);

        return Collections.singletonList(result);
    }
}
