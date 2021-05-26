package renderer;

import elements.LightSource;
import geometries.*;
import primitives.*;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;
import geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;



    public RayTracerBasic(Scene _scene) {
        super(_scene);
    }

    /**
     * function traceRay
     *
     * @param _ray
     * @return the color of of the intersection point through this ray If there is
     *         no intersection with this ray, return the background color
     *
     */
    @Override
    public Color traceRay(Ray _ray) {
        GeoPoint closestPoint = findClosestIntersection(_ray);
        if (closestPoint == null)
            return scene.background; // If there is no intersection, return the background color
        return calcColor(closestPoint, _ray); // Find the color of the closest intersection point
    }

    /**
     * "principal" calcColor function to calculates the final color
     * @param point
     * @param ray
     * @return
     */
    private Color calcColor(GeoPoint point, Ray ray)
    {
        return calcColor(findClosestIntersection(ray), ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientlight.getIntensity());
    }

    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));
    }

    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = Util.alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = Util.alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if(unshaded(lightSource,l,n,intersection)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
                    l = l.normalize();
                    n = n.normalize();
                    double dp = l.dotProduct(n);                        // help parameter for the next functions
                    color = color.add(calcDiffusive(material.kD, lightIntensity, dp),
                            calcSpecular(material.kS, l, n, v, material.nShininess, lightIntensity, dp));
                }
            }
        }
        return color;
    }

    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK; Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kR;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR, kkr);
        double kkt = k * material.kT;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));
        return color;
    }

    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection (ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx).scale(kx));
    }


    Color calcSpecular(double kS, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity, double dp) {
        v = v.normalize();
        Vector r = l.substractV(n.scale(2 * dp));
        return lightIntensity.reduce(1/(kS * Math.pow(Math.max(0, (-1) * v.dotProduct(r)), nShininess)));
    }

    Color calcDiffusive(double kD, Color lightIntensity, double dp) {
        return lightIntensity.scale(kD * Math.abs(dp));
    }

    /**
     * Verify if there isn't any geometry between the point and the light source
     * which would cause shadows
     * @param l
     * @param n
     * @param geopoint
     * @return true or false
     */
    private boolean unshaded(LightSource lightSource,Vector l, Vector n, GeoPoint geopoint ){
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
        Point3D point = geopoint.point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return true;
        double lightDistance = lightSource.getDistance(geopoint.point);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point)-lightDistance) <= 0 && gp.geometry.getMaterial().kT == 0)
                return false;
        }
        return true;
    }


    /**
     * Calculates and returns the reflection ray
     */
    public Ray constructReflectedRay(Point3D p,Vector v, Vector n){
        v=v.normalize();
        Vector r=v.substractV(n.scale(v.dotProduct(n)).scale(-2));
        return new Ray(p,r);
    }

    /**
     * Calculates and returns the refraction ray
     */
    public Ray constructRefractedRay(Point3D p,Vector v,Vector n){
        return new Ray(p,v);
    }

    /**
     * Return the closest intersection point from the head of the ray
     */
    private GeoPoint findClosestIntersection(Ray ray){
        List<GeoPoint> intersections = new LinkedList<GeoPoint>();
        for(Intersectable geometrie : scene.geometries.lstOfGeometries){ //pass in every geometrie in the scene
            if(geometrie.findGeoIntersections(ray)!=null)
                intersections.addAll(geometrie.findGeoIntersections(ray)); //foreach geometry, intersections points are
                                                                    //added to the intersection list
        }
        if(intersections==null)
            return null;
        return ray.findClosestGeoPoint(intersections); //return the closest point in those intersection points
    }

}