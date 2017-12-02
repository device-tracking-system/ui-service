package pl.edu.agh.iet.dts.ui.controller.json;

/**
 * @author Bartłomiej Grochal
 */
public class UserJSON {

    public String userID;

    public int outputPoints;
    public int timePeriod;
    public int updateTime;


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder
                .append("{\n")
                .append("\tuserID : ").append(userID).append(",\n")
                .append("\toutputPoints : ").append(outputPoints).append(",\n")
                .append("\ttimePeriod : ").append(timePeriod).append(",\n")
                .append("\tupdateTime : ").append(updateTime).append("\n")
                .append("}");
        return builder.toString();
    }

}
