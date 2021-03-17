package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Cylinder {
    double height;

    public Vector getNormal(Point3D p)
    {
        return null;
    }

    public Cylinder(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                '}';
    }
}
