package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Tube extends Geometry{
    Ray axisRay;
    double radius;

    @Override
    public Vector getNormal(Point3D p)
    {
        Vector v = axisRay.getDir();
        Point3D p0 = axisRay.getP0();
        Vector pp0 = new Vector(p.getX()-p0.getX(),p.getY()-p0.getY(),p.getZ()-p0.getZ());
        double t= v.dotProduct(pp0);
        Point3D o = p0.add(v.scale(t));
        Vector n = new Vector(p.subtract(o));
        return n.normalize();
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
    public List<GeoPoint> findGeoIntersections(Ray _ray) {
        return null;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}