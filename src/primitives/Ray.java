package primitives;

import java.util.List;

import static primitives.Util.isZero;

public class Ray {
    Point3D p0;
    Vector dir;

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