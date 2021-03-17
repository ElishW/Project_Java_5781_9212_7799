package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube {
    Ray axisRay;
    double radius;

    public Vector getNormal(Point3D p)
    {
        return null;
    }

    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}
