package pl.edu.agh.iet.dts.ui.messaging.format;

import java.io.Serializable;

/**
 * @author Bartłomiej Grochal
 */
public class AggregationTask implements Serializable {

    private final String ownerId;

    private final int points;
    private final int period;
    private final long timestamp;


    public AggregationTask() {
        this(null, 0, 0, -1);
    }

    public AggregationTask(final String ownerId, final int points, final int period, final long timestamp) {
        this.ownerId = ownerId;
        this.points = points;
        this.period = period;
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder
                .append("{\n")
                .append("\townerId : ").append(ownerId).append(",\n")
                .append("\tpoints : ").append(points).append(",\n")
                .append("\tperiod : ").append(period).append(",\n")
                .append("\ttimestamp : ").append(timestamp).append("\n")
                .append("}");
        return builder.toString();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public int getPoints() {
        return points;
    }

    public int getPeriod() {
        return period;
    }

    public long getTimestamp() {
        return timestamp;
    }

}
