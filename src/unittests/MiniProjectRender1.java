package unittests;
import static org.junit.Assert.*;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;

import primitives.*;
import renderer.*;
import scene.Scene;

import java.util.Random;

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
                .setMaterial(new Material().setKd(0.255).setKs(0.4).setShininess(50).setKr(0).setKt(1)),

                new Triangle(new Point3D(-660, 640, 640), new Point3D(640, -660, 640),
                        new Point3D(-577, -577, -810)) //
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
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
                        .setMaterial(new Material().setKd(0.25).setKs(0.4).setShininess(50).setKr(0.1).setKt(1)),
                new Sphere(new Point3D(1,30,0),15)//
                        .setEmission(new Color(java.awt.Color.YELLOW))//
                        .setMaterial(new Material().setShininess(50).setKr(0.4).setKt(0.4)),
                new Sphere(new Point3D(25,10,-30),4)//
                        .setEmission(new Color(219,95,194))//
                        .setMaterial(new Material().setShininess(50).setKr(0.4).setKt(0.4)),
               new Sphere(new Point3D(5,-5,-50),3)//
                        .setEmission(new Color(java.awt.Color.BLUE))//
                        .setMaterial(new Material().setShininess(50).setKr(0.4).setKt(0.4)),
                new Sphere(new Point3D(-40,20,-10),9)//
                        .setEmission(new Color(21,132,15))//
                        .setMaterial(new Material().setShininess(50).setKr(0.4).setKt(0.4)),
                new Sphere(new Point3D(-60,35,-30),6)//
                        .setEmission(new Color(13,166,195))//
                        .setMaterial(new Material().setKr(0.4).setKt(0.4 )),
                new Sphere(new Point3D(67,60,30),13)//
                        .setEmission(new Color(195,60,13))//
                        .setMaterial(new Material().setShininess(50).setKr(0.4).setKt(0.4)),
                new Sphere(new Point3D(-15,75,60),7)//
                        .setEmission(new Color(198,234,49))//
                        .setMaterial(new Material().setShininess(50).setKr(0.4).setKt(0.4)));

        // making the stars
        for (var i=0; i < 200; i ++) {
            Random r = new Random();
            // min and max value of the radius
            double randomRadius = 0.001 + (0.6 - 0.001) * r.nextDouble();
            double randomMult = 10+r.nextInt(60);
            Coordinate x = new Coordinate(randomMult * Math.sin(i));
            Coordinate y = new Coordinate(30+randomMult * Math.cos(i));
            int randomZ = -25+r.nextInt(30);
            if(randomZ>-2&&randomZ<2){randomZ=5;}
            Coordinate z = new Coordinate(i/randomZ);
            Point3D position = new Point3D(x,y,z);

            scene.geometries.add(
                    new Sphere(position,randomRadius)//
                            .setEmission(new Color(java.awt.Color.WHITE)) //
                            .setMaterial(new Material().setKt(0.99))
            );
        }

       scene.lights.add( //
                new SpotLight(new Color(java.awt.Color.WHITE),  new Point3D(-80, -90, 1000), new Vector(2, 2, -6))//
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add( //
                new SpotLight(new Color(java.awt.Color.WHITE),  new Point3D(30, 30, 1000), new Vector(2, 2, -6))//
                        .setKl(1E-5).setKq(1.5E-7));




    /*scene.lights.add( //
               new PointLight(new Color(java.awt.Color.YELLOW),  new Point3D(1, 30, 0))//
                 .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add( //
                new PointLight(new Color(219,95,194),  new Point3D(25,10,-30))//
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add( //
                new PointLight(new Color(java.awt.Color.BLUE),  new Point3D(5,-5,-50))//
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add( //
                new PointLight(new Color(java.awt.Color.RED),  new Point3D(-40,20,-10))//
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add( //
                new PointLight(new Color(45,221,222),  new Point3D(-60,35,-30))//
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add( //
                new PointLight(new Color(204,102,0),  new Point3D(67,60,30))//
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add( //
                new PointLight(new Color(102,178,255),  new Point3D(-15,75,60))//
                        .setKl(1E-5).setKq(1.5E-7));*/
        scene.lights.add(new PointLight(new Color(java.awt.Color.BLUE),new Point3D(0,-50,0)));


        Render render = new Render() //
                .setImageWriter(new ImageWriter("Snow Ball", 1000, 1000)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene))//
        .setMultithreading(0).setDebugPrint();
        render.renderImage();
        render.writeToImage();

        camera.moveCamera(0,0,150);
        Render renderZ = new Render()//
                .setImageWriter(new ImageWriter("Snow Ball Zoom", 500, 500)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene))//
        .setMultithreading(3).setDebugPrint();
        renderZ.renderImage();
        renderZ.writeToImage();

    }


}
