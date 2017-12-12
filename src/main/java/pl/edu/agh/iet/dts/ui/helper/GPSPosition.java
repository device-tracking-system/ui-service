package pl.edu.agh.iet.dts.ui.helper;

/**
 * @author Bartłomiej Grochal
 */
public class GPSPosition {

    private final double latitude;
    private final double longitude;


    public GPSPosition(final double latitude, final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
