package unittests;

import static org.junit.Assert.*;

import elements.SpotLight;
import geometries.Sphere;
import org.junit.Test;
import elements.Camera;
import primitives.*;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

public class SuperSamplingTest {



    @Test
    public void twoSpheres() {
         Scene scene = new Scene("Test scene");


        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(150, 150).setDistance(1000);

        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -50), 50) //
                        .setEmission(new Color(java.awt.Color.BLUE)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
            .setKl(0.0004).setKq(0.0000006));

    Render render = new Render() //
            .setImageWriter(new ImageWriter("superSamplingSphere", 500, 500)) //
            .setCamera(camera) //
            .setRayTracer(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
    }
}