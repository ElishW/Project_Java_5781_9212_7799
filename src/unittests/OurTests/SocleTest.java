package unittests.OurTests;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

public class SocleTest {
    private Scene scene = new Scene("Test scene");
    @Test
    public void Socles()
    {
        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.2));
                //.setBackground(new Color(java.awt.Color.ORANGE));

        scene.geometries.add(//
                new Triangle(new Point3D(20, -20, 100), new Point3D(-20, 20, 100), new Point3D(20, 20, 100)) //
                       .setEmission(new Color(20, 20, 20))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Polygon( new Point3D(20, 20, 50),new Point3D(20, -20, 50),new Point3D(-20, -20, 50),new Point3D(-20,20, 50))//
                        .setEmission(new Color(java.awt.Color.GREEN))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere( new Point3D(-20, 0, 0),10) //
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere( new Point3D(0, 20, 0),10) //
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere( new Point3D(20, 0, 0),10) //
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere( new Point3D(0, 0, 0),10) //
                .setEmission(new Color(java.awt.Color.BLUE)) //
                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));
        scene.lights.add(new SpotLight(new Color(java.awt.Color.WHITE), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point3D(-80, -50, 20), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));


        ImageWriter imageWriter = new ImageWriter("SocleTest", 600, 600);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));

        render.renderImage();
        render.writeToImage();


    }
}
