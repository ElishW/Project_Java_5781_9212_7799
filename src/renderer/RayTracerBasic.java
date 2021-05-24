package renderer;

import elements.LightSource;
import geometries.*;
import primitives.*;
import scene.Scene;

import java.util.List;
import geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
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
        var intersections = scene.geometries.findGeoIntersections(_ray);
        if (intersections == null)
            return scene.background; // If there is no intersection, return the background color
        GeoPoint closestPoint = _ray.findClosestGeoPoint(intersections); // Find the closest intersection point
        return calcColor(closestPoint, _ray); // Find the color of the intersection point (Ambient light)
    }

    private Color calcColor(GeoPoint point, Ray ray)
    {
        return scene.ambientlight.getIntensity().add(point.geometry.getEmission())
                // add calculated light contribution from all light sources
                .add(calcLocalEffects(point, ray));
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
            if (nl * nv > 0) { 												// sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                l = l.normalize();
                n = n.normalize();
                double dp = l.dotProduct(n); 						// help parameter for the next functions
                color = color.add(calcDiffusive(material.kD, lightIntensity, dp),
                        calcSpecular(material.kS, l, n, v, material.nShininess, lightIntensity, dp));
            }
        }
        return color;
    }

    Color calcSpecular(double kS, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity, double dp) {
        v = v.normalize();
        Vector r = l.substractV(n.scale(2 * dp));
        return lightIntensity.scale(kS * Math.pow(Math.max(0, (-1) * v.dotProduct(r)), nShininess));
    }

    Color calcDiffusive(double kD, Color lightIntensity, double dp) {
        return lightIntensity.scale(kD * Math.abs(dp));
    }


}