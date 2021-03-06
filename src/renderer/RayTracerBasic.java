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
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;



    public RayTracerBasic(Scene _scene) {
        super(_scene);
    }

    /**
     * function traceRay
     *
     * @param ray
     * @return the color of of the intersection point through this ray If there is
     *         no intersection with this ray, return the background color
     *
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }


    /**
     * "principal" calcColor function to calculates the final color
     * @param geopoint
     * @param ray
     * @return
     */
    private Color calcColor(GeoPoint geopoint, Ray ray)
    {
        return calcColor(findClosestIntersection(ray), ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientlight.getIntensity());
    }



    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, k));
        return level == 1 ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));
    }


    private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k) {
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
                double ktr = transparency(lightSource, l, n, intersection);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    l = l.normalize();
                    n = n.normalize();
                    double dp = l.dotProduct(n); // help parameter for the next functions
                    color = color.add(calcDiffusive(material.kD, lightIntensity, dp),
                            calcSpecular(material.kS, l, n, v, material.nShininess, lightIntensity, dp));

                }
            }
        }
        return color;
    }



    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kR;
        if (kkr > MIN_CALC_COLOR_K)

            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR, kkr);
        double kkt = k * material.kT;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));
        return color;
    }


    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
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
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n); // refactored ray head move
        // Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
        // Point3D point = geopoint.point.add(delta);
        // Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        if (intersections == null)
            return true;
        double lightDistance = light.getDistance(geopoint.point);
        for (GeoPoint gp : intersections) {
            if (Util.alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0
                    && gp.geometry.getMaterial().kT == 0)
                return false;
        }
        return true;

    }

    /**
     * Unshading test operation between a point and the source of the light for a
     * partial shadow
     *
     * @param ls:      light source
     * @param l
     * @param n        : normal
     * @param geoPoint
     * @return a number between 0 and 1 to the intensity of the shadow
     */
    private double transparency(LightSource ls, Vector l, Vector n, GeoPoint geoPoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n); // refactored ray head move
        double lightDistance = ls.getDistance(geoPoint.point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        if (intersections == null)
            return 1.0;
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (Util.alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().kT;
                if (ktr < MIN_CALC_COLOR_K)
                    return 0.0;
            }
        }
        return ktr;

    }



    /**
     * Calculates and returns the reflection ray
     */
    Ray constructReflectedRay(Point3D p, Vector v, Vector n) {
        v = v.normalize();
        double vn = v.dotProduct(n);
        if (Util.isZero(vn))
            return null;
        Ray r = new Ray(p, v.substractV(n.scale(2d * vn)).normalized(), n);
        return r;
    }

    /**
     * Calculates and returns the refraction ray
     */
    Ray constructRefractedRay(Point3D p, Vector v, Vector n) {
        return new Ray(p, v.normalize(), n); // r
    }
    /**
     * Return the closest intersection point from the head of the ray
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        // return ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
        if (scene.geometries.findGeoIntersections(ray) != null) {
            double distance = ray.getP0().distance(scene.geometries.findGeoIntersections(ray).get(0).point);
            GeoPoint myPoint = scene.geometries.findGeoIntersections(ray).get(0);
            for (GeoPoint g : scene.geometries.findGeoIntersections(ray)) {
                if (ray.getP0().distance(g.point) < distance) {
                    distance = ray.getP0().distance(g.point);
                    myPoint = g;
                }
            }
            return myPoint;

        }
        return null;
    }

}