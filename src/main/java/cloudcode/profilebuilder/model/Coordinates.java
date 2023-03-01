package cloudcode.profilebuilder.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.geom.Point2D;

public class Coordinates {

    @Getter @Setter
    public double longitude;

    @Getter @Setter
    public double latitude;

    public Coordinates(double longitude, double latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
