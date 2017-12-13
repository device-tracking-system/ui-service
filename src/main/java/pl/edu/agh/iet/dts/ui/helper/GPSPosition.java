package pl.edu.agh.iet.dts.ui.helper;

/**
 * @author Bartłomiej Grochal
 */
public class GPSPosition {

    private final double latitude;
    private final double longitude;


    public GPSPosition() {
        this(0.0d, 0.0d);
    }

    public GPSPosition(final double latitude, final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder
                .append("{latitude : ")
                .append(latitude)
                .append(", longitude : ")
                .append(longitude)
                .append("}");
        return builder.toString();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
