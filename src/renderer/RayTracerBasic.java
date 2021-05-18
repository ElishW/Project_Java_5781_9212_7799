package renderer;

import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene _scene) {
        super(_scene);
    }

    @Override
    public Color traceRay(Ray _ray) {
        var intersections = scene.geometries.findGeoIntersections(_ray);
        if (intersections != null) {
            Intersectable.GeoPoint closestPoint = _ray.findClosestGeoPoint(intersections);
            return calcColor(closestPoint);
        }
        //there isn't intersection
        return scene.background;
    }

    private Color calcColor(Intersectable.GeoPoint point)
    {
        return scene.ambientlight.getIntensity()
                .add(point.geometry.getEmission());

    }
}