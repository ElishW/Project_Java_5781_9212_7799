package unittests;
import static org.junit.Assert.*;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.*;
import scene.Scene;

/**
 * Authors : Elisheva Wechsler & Noa Ghozlan
 * Mini Project Part I
 */

public class MiniProjectRender1 {


    @Test
    public void drawOurImagination(){
        Scene scene = new Scene("Snow Ball").setBackground(new Color(0,0,45));//
        // .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        Camera camera = new Camera(new Point3D(0, 0, -250), new Vector(0, 0, 1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(150, 150).setDistance(100);
        scene.geometries.add( //
                new Sphere(new Point3D(0, 30, 0), 80) //
                .setEmission(new Color(0,0,0))//
                .setMaterial(new Material().setKd(0.255).setKs(0.4).setShininess(50).setKr(0.1).setKt(1)),
             /* new Triangle(new Point3D(-660, 640, 640), new Point3D(640, -660, 640),
                     new Point3D(-577, -577, -810)) //
                    .setEmission(new Color(20,20,20)) //
                      .setMaterial(new Material().setKt(0.99)),*/
        new Triangle(new Point3D(-40, -50, -40), new Point3D(40, -50, -40),
                new Point3D(40, -80, -40))//
                .setEmission(new Color(24,24,24)) //
                .setMaterial(new Material().setKd(0.25).setKs(0.4).setShininess(50).setKr(0.1).setKt(1)),
                new Triangle(new Point3D(-40, -80, -40), new Point3D(-40, -50, -40),
                        new Point3D(40, -80, -40))//
                        .setEmission(new Color(24,24,24))
                        .setMaterial(new Material().setKd(0.25).setKs(0.4).setShininess(50).setKr(0.1).setKt(1)),
                new Triangle(new Point3D(-40, -50, 40), new Point3D(40, -50, 40),
                        new Point3D(40, -80, 40))//
                        .setEmission(new Color(24,24,24)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.4).setShininess(50).setKr(0.1).setKt(1)),
                new Triangle(new Point3D(-40, -80, 40), new Point3D(-40, -50, 40),
                        new Point3D(40, -80, 40))//
                        .setEmission(new Color(24,24,24))
                        .setMaterial(new Material().setKd(0.25).setKs(0.4).setShininess(50).setKr(0.1).setKt(1)),
                new Triangle(new Point3D(40, -50, 40), new Point3D(40, -80, 40),
                        new Point3D(40, -80, -40))//
                        .setEmission(new Color(24,24,24)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.4).setShininess(50).setKr(0.1).setKt(1)),
                new Triangle(new Point3D(40, -50, 40), new Point3D(40, -50, -40),
                        new Point3D(40, -80, -40))//
                        .setEmission(new Color(24,24,24))
                        .setMaterial(new Material().setKd(0.25).setKs(0.4).setShininess(50).setKr(0.1).setKt(1)),
                new Triangle(new Point3D(-40, -50, 40), new Point3D(-40, -50, -40),
                        new Point3D(-40, -80, 40))//
                        .setEmission(new Color(24,24,24)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.4).setShininess(50).setKr(0.1).setKt(1)),
                new Triangle(new Point3D(-40, -50, -40), new Point3D(-40, -80, -40),
                        new Point3D(-40, -80, 40))//
                        .setEmission(new Color(24,24,24))
                        .setMaterial(new Material().setKd(0.25).setKs(0.4).setShininess(50).setKr(0.1).setKt(1)),
                new Triangle(new Point3D(-40, -50, -40), new Point3D(40, -50, 40),
                        new Point3D(-40, -50, 40))//
                        .setEmission(new Color(24,24,24)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.4).setShininess(50).setKr(0.1).setKt(1)),
                new Triangle(new Point3D(-40, -50, -40), new Point3D(40, -50, -40),
                        new Point3D(40, -50, 40))//
                        .setEmission(new Color(24,24,24))
                        .setMaterial(new Material().setKd(0.25).setKs(0.4).setShininess(50).setKr(0.1).setKt(1)));


       scene.lights.add( //
                new SpotLight(new Color(java.awt.Color.WHITE),  new Point3D(-80, -90, 30), new Vector(2, 2, -6))//
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add( //
                new SpotLight(new Color(java.awt.Color.WHITE),  new Point3D(80, -90, 30), new Vector(-2, 2, -6))//
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add(new PointLight(new Color(java.awt.Color.BLUE),new Point3D(0,1000,0)));


        Render render = new Render() //
                .setImageWriter(new ImageWriter("Snow Ball", 500, 500)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();

    }
}
