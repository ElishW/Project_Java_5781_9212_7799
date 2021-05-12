package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

public interface Intersectable {

    /**
     *
     * @param ray
     * @return List of intersectable Point3D if they exist or null
     */
    List<Point3D> findIntersections(Ray ray);

    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;
    }

}
