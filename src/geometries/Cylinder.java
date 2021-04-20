package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Cylinder{
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

    public List<Point3D> findIntersections(Ray ray){return null;}


    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                '}';
    }
}
