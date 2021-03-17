package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere {
    Point3D center;
    double radius;

    public Vector getNormal(Point3D p)
    {
        return null;
    }

    public Sphere(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point3D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
