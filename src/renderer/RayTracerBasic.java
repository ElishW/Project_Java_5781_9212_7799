package renderer;

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
        List<Point3D> intersections = scene.geometries.findIntersections(_ray);
        if (intersections != null) {
            Point3D closestPoint = _ray.findClosestPoint(intersections);
            return calcColor(closestPoint);
        }
        //there isn't intersection
        return scene.background;
    }

    private Color calcColor(Point3D point)
    {
        //returning the intensity of the light of the scene
        return scene.ambientLight.getIntensity();
    }
}