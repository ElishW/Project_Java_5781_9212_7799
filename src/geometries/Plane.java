package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
import java.util.List;

public class Plane extends Geometry{
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



    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        Point3D P0 = ray.getP0();
        Vector v = ray.getDir();

        Vector n = normal;
        //Checking if Q0=P0
        if(q0.equals(P0)){
            return  null;
        }

        Vector P0_Q0 = q0.subtract(P0);

        //numerator
        double nP0Q0  = alignZero(n.dotProduct(P0_Q0));

        //Checking if the numerator is null
        if (isZero(nP0Q0 )){
            return null;
        }

        //denominator
        double nv = alignZero(n.dotProduct(v));

        // ray is lying in the plane axis
        if(isZero(nv)){
            return null;
        }

        double  t = alignZero(nP0Q0  / nv);
        //Tacking only t>0
        if (t <=0){
            return  null;
        }
        Point3D point=  P0.add(v.scale(t));

        return List.of(new GeoPoint(this,point));

    }

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