package pl.edu.agh.iet.dts.ui.messaging.format;

import pl.edu.agh.iet.dts.ui.helper.GPSPosition;

import java.util.Arrays;

/**
 * @author Bartłomiej Grochal
 */
public class AggregatedData {

    private final String id;

    private final GPSPosition[] data;
    private final long timestamp;


    public AggregatedData() {
        this(null, null, -1);
    }

    public AggregatedData(final String id, final GPSPosition[] data, final long timestamp) {
        this.id = id;
        this.data = data;
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder
                .append("{\n")
                .append("\tid : ").append(id).append(",\n")
                .append("\tdata : ").append(Arrays.toString(data)).append(",\n")
                .append("\ttimestamp : ").append(timestamp).append("\n")
                .append("}");
        return builder.toString();
    }

    public String getId() {
        return id;
    }

    public GPSPosition[] getData() {
        return data;
    }

    public long getTimestamp() {
        return timestamp;
    }

}
