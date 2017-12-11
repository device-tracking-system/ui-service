package pl.edu.agh.iet.dts.ui.controller.json;

/**
 * @author Bartłomiej Grochal
 */
public class UserJSON {

    public int points;
    public int period;
    public int aggregationTime;


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder
                .append("{\n")
                .append("\tpoints : ").append(points).append(",\n")
                .append("\tperiod : ").append(period).append(",\n")
                .append("\taggregationTime : ").append(aggregationTime).append("\n")
                .append("}");
        return builder.toString();
    }

}
