package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Triangle extends Polygon {

    public Triangle(Point3D a, Point3D b , Point3D c )
    {
        super(a,b,c);
    }

    public Vector getNormal(Point3D point) {
        return plane.getNormal();
    }

    public List<Point3D> findIntersections(Ray ray){return null;}


}
