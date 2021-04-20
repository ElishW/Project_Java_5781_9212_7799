package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Plane implements Geometry{
    Point3D q0;
    Vector normal;

    public Plane(Point3D a, Point3D b, Point3D c)
    {
        this.q0= new Point3D(a);
        Vector v1 = b.subtract(a);
        normal = (v1.crossProduct(c.subtract(a))).normalize();

    }

    public Plane(Point3D q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
    }

    public Point3D getQ0() {
        return q0;
    }

    /**
     * @param ray
     * @return List of intersectable Point3D if they exist or null
     */
    @Override
    public List<Point3D> findIntersections(Ray ray){return null;}

    public Vector getNormal(){return normal;}



    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }


    @Override
    public Vector getNormal(Point3D p) {
        return normal;
    }
}
