package unittests.OurTests;


import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;


public class OurImageTest {

    private Scene scene = new Scene("Test scene");


    @Test
    public void createImage() {
        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -2), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);

        scene.geometries.add(//
               new Sphere(new Point3D(50, 50, -45), 40)//
                        .setEmission(new Color(java.awt.Color.MAGENTA))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere(new Point3D(10, 10, -5), 30)//
                        .setEmission(new Color(java.awt.Color.BLUE))//
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(80).setKt(0.4)),
                new Sphere(new Point3D(-50, -50, -30), 20)//
                        .setEmission(new Color(java.awt.Color.YELLOW))//
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10).setKt(0.2)),
                new Triangle(new Point3D(-100, -100, -100), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setKd(0.1).setKs(0.5).setShininess(100)),
                new Sphere(new Point3D(80, 40, -4), 10)//
                        .setEmission(new Color(java.awt.Color.CYAN))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere(new Point3D(-50, 40, 77),23)//
                        .setEmission(new Color(24,24,24))
                        .setMaterial(new Material().setKr(0.8).setKt(0.8)),
                new Triangle(new Point3D(80,-10,-10),new Point3D(25,-17,-14),new Point3D(50,15,15))
                        .setEmission(new Color(128,0,30))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Triangle(new Point3D(0,-50,-40),new Point3D(25,-17,-14),new Point3D(-30,-25,-19))
                        .setEmission(new Color(128,0,30))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere(new Point3D(25,-17,-14),5)
                        .setEmission(new Color(128,0,30))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));




        scene.lights.add( //
                new SpotLight(new Color(700, 340, 400), new Point3D(-80, -90, 30), new Vector(2, 2, -6)) //
                        .setKl(1E-5).setKq(1.5E-7));//
         scene.lights.add(new PointLight(new Color(java.awt.Color.WHITE),new Point3D(30,30,30)));
        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
                .setKl(0.00001).setKq(0.000005));
        ImageWriter imageWriter = new ImageWriter("OurImageTest", 600, 600);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));

        render.renderImage();
        render.writeToImage();

        //Move & Turn Camera
        //camera.moveCamera(100,100,0);
        camera.turnCamera(new Vector(-1,-1,-1),3);
        ImageWriter imageWriter1 = new ImageWriter("OurImageMove&Turn", 600, 600);
        Render render1 = new Render() //
                .setImageWriter(imageWriter1) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));

        render1.renderImage();
        render1.writeToImage();

    }

}