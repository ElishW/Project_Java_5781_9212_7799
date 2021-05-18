package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.stream.Collectors;

public interface Intersectable {

    /**
     *
     * @param ray
     * @return List of intersectable Point3D if they exist or null
     */
    default List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
    }


    List<GeoPoint> findGeoIntersections(Ray _ray);


    /**
     * Helper Class - Geo Point
     * Every object of this class is an intersection point with a specific geometry
     * to make difference between each geometry in the scene
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * Constructor GeoPoint
         * @param _geometry
         * @param _point
         */
        public GeoPoint(Geometry _geometry, Point3D _point) {
            this.geometry = _geometry;
            this.point = _point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (!(o instanceof GeoPoint)) return false;
            GeoPoint other = (GeoPoint) o;
           // return this.x.equals(other.x) && this.y.equals(other.y)&& this.z.equals(other.z);
            return this.geometry.equals(other.geometry) && this.point.equals(other.point);

        }


    }

}
