package renderer;

import elements.LightSource;
import geometries.*;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene _scene) {
        super(_scene);
    }

    @Override
    public Color traceRay(Ray _ray) {
        var intersections = scene.geometries.findGeoIntersections(_ray);
        if (intersections != null) {
            Intersectable.GeoPoint closestPoint = _ray.findClosestGeoPoint(intersections);
            return calcColor(closestPoint,_ray);
        }
        //there isn't intersection
        return scene.background;
    }

    private Color calcColor(Intersectable.GeoPoint point, Ray ray)
    {
        return scene.ambientlight.getIntensity()
                .add(point.geometry.getEmission())
                // add calculated light contribution from all light sources)
                .add(calcLocalEffects(point, ray));
    }

    private Color calcLocalEffects(Intersectable.GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.getShininess();
        double kd = material.getKd(), ks = material.getKs();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.substractV(n.scale(alignZero(l.dotProduct(n)*2)));
        double minus_vr=alignZero(v.dotProduct(r)*(-1));
        return lightIntensity.scale(ks*Math.pow(Math.max(0,minus_vr),nShininess));
    }

    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        return lightIntensity.scale(kd*Math.abs(alignZero(l.dotProduct(n))));
    }

}