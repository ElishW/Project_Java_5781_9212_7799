package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import primitives.Util.*;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Cylinder extends Tube{
    double _height;
    Plane _base1;
    Plane _base2;

    public Vector getNormal(Point3D p)
    {

        Point3D o = axisRay.getP0();
        Vector v = axisRay.getDir();

        // projection of P-O on the ray:
        double t;
        try {
            t = alignZero(p.subtract(o).dotProduct(v));
        } catch (IllegalArgumentException e) { // P = O
            return v;
        }

        // if the point is at a base
        if (t == 0 || isZero(_height - t)) // if it's close to 0, we'll get ZERO vector exception
            return v;

        o = o.add(v.scale(t));
        return p.subtract(o).normalize();
    }

    public Cylinder(double radius,Ray axisRay, double height) {
        super(axisRay, radius);
        _height = height;
        Vector v = axisRay.getDir();
        _base1 = new Plane(axisRay.getP0(),v);
        _base2= new Plane(axisRay.getPoint(_height),v);
    }
    public double getHeight() {
        return _height;
    }



    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + _height +
                '}';
    }
}