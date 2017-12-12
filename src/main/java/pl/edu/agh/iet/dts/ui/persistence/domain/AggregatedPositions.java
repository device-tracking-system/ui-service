package pl.edu.agh.iet.dts.ui.persistence.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.edu.agh.iet.dts.ui.persistence.domain.helper.GPSPosition;

/**
 * @author Bartłomiej Grochal
 */
@Document
public class AggregatedPositions {

    @Id private final String id;

    private final GPSPosition[] data;
    private final long timestamp;


    public AggregatedPositions() {
        this(null, null, -1);
    }

    /**
     * @param id        Google ID of the user, whose aggregated positions are represented by this object.
     * @param data      Array of aggregated points.
     * @param timestamp Timestamp of the latest event processed within a batch represented by this object.
     */
    public AggregatedPositions(final String id, final GPSPosition[] data, final long timestamp) {
        this.id = id;
        this.data = data;
        this.timestamp = timestamp;
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
