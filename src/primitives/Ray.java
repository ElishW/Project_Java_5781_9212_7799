package primitives;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.isZero;

public class Ray {
    Point3D p0;
    Vector dir;
    private static final double DELTA = 0.1;



    public Ray(Point3D p, Vector d) {
        this.p0 = new Point3D(p);
        this.dir= d.normalized();
    }

    public Point3D getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    public Point3D getPoint(double t){
        if (isZero(t)){
            return  p0;
        }
        return p0.add(dir.scale(t));
    }

    /**
     * Constructor with 3 parameters to add the parameter DELTA
     */
    public Ray(Point3D head, Vector direction, Vector normal) {
        if (direction.dotProduct(normal) == 0) {
            p0 = head;
            dir = direction;
        } else {
            int sign = 1;
            // if (direction.dotProduct(normal)>0)
            // sign=1;
            if (direction.dotProduct(normal) < 0)
                sign = -1;
            p0 = head.add(normal.scale(sign * DELTA));
            dir = direction;
        }
    }
    /**
     * Returns the closest point from the head of the ray
     * @param pointsList : list of all the points crossed by the ray
     * @return the closest point from the point P0
     */
    public Point3D findClosestPoint(List<Point3D> pointsList){
        Point3D result =null;
        double closestDistance = Double.MAX_VALUE;

        if(pointsList== null){
            return null;
        }

        for (Point3D p: pointsList) {
            double temp = p.distance(p0);
            if(temp < closestDistance){
                closestDistance =temp;
                result =p;
            }
        }

        return  result;
    }

    /**
     *  Returns the closest point from the head of the ray
     * @param pointsList : list of all the points crossed by the ray
     * @return the closest point from the point P0
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> pointsList){
        GeoPoint result =null;
        double closestDistance = Double.MAX_VALUE;

        if(pointsList== null){
            return null;
        }

        for (GeoPoint p: pointsList) {
            double temp = p.point.distance(p0);
            if(temp < closestDistance){
                closestDistance =temp;
                result =p;
            }
        }

        return  result;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Ray)) return false;
        Ray other = (Ray) o;
        return this.p0.equals(other.p0) && this.dir.equals(other.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}