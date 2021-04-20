package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Sphere {
    Point3D center;
    double radius;

    public Vector getNormal(Point3D p)
    {
        Vector n = new Vector(p.subtract(center));
        return n.normalize();
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

    public List<Point3D> findIntersections(Ray ray){return null;}


    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
