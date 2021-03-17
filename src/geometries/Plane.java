package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane {
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

    public Vector getNormal() {
        return normal;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }
}
