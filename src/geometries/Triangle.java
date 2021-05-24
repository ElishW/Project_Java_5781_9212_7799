package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

public class Triangle extends Polygon {

    public Triangle(Point3D a, Point3D b , Point3D c )
    {
        super(a,b,c);
    }

    @Override
    public Vector getNormal(Point3D point) {
        return plane.getNormal();
    }

    public List<GeoPoint> findGeoIntersections(Ray ray) {
        // First check the intersections with the plane
        Point3D point3d = vertices.get(0);
        Vector v = vertices.get(0).subtract(vertices.get(1));
        Vector w = vertices.get(1).subtract(vertices.get(2));
        Vector normal = (v.crossProduct(w)).normalize();
        Plane plane = new Plane(point3d, normal);
        if (plane.findIntersections(ray) == null)
            return null;

        // After check if they're in the triangle
        Vector v1 = (ray.getP0().subtract(vertices.get(0)));
        Vector v2 = (ray.getP0().subtract(vertices.get(1)));
        Vector v3 = (ray.getP0().subtract(vertices.get(2)));
        Vector N1 = (v1.crossProduct(v2)).normalize();
        Vector N2 = (v2.crossProduct(v3)).normalize();
        Vector N3 = (v3.crossProduct(v1)).normalize();
        if (Util.alignZero(ray.getDir().dotProduct(N1)) == 0 || Util.alignZero(ray.getDir().dotProduct(N2)) == 0
                || Util.alignZero(ray.getDir().dotProduct(N3)) == 0)
            return null;

        if ((ray.getDir().dotProduct(N1) > 0 && ray.getDir().dotProduct(N2) > 0 && ray.getDir().dotProduct(N3) > 0)
                || (ray.getDir().dotProduct(N1) < 0 && ray.getDir().dotProduct(N2) < 0
                && ray.getDir().dotProduct(N3) < 0)) {
            return List.of(new GeoPoint (this, plane.findIntersections(ray).get(0)));
        }

        else
            return null;

    }



}
