package pl.edu.agh.iet.dts.ui.persistence.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Bartłomiej Grochal
 */
@Document
public class Preferences {

    @Id private final String id;

    private final int points;
    private final int period;
    private final int aggregationTime;


    public Preferences() {
        this(null, 0, 0, 0);
    }

    /**
     * @param id              Google ID of the user, whose preferences are represented by this object.
     * @param points          Number of points which data is aggregated to.
     * @param period          Number of minutes which data is processed from.
     * @param aggregationTime Number of minutes constituting the time interval for the task scheduler.
     */
    public Preferences(final String id, final int points, final int period, final int aggregationTime) {
        this.id = id;
        this.period = period;
        this.points = points;
        this.aggregationTime = aggregationTime;
    }


    public String getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public int getPeriod() {
        return period;
    }

    public int getAggregationTime() {
        return aggregationTime;
    }

}
