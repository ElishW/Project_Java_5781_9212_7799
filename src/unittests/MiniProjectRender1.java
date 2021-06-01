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
        Scene scene = new Scene("Snow Ball").setBackground(new Color(0,0,50))//
         .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        Camera camera = new Camera(new Point3D(0, 0, -250), new Vector(0, 0, 1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(150, 150).setDistance(100);
        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, 0), 50) //
                .setEmission(new Color(java.awt.Color.CYAN)) //
                .setMaterial(new Material().setKd(0.9).setKs(0.7).setKt(0.95).setShininess(100)),
                new Triangle(new Point3D(-660, 640, 640), new Point3D(640, -660, 640),
                        new Point3D(-577, -577, -810)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKt(0.88)));


        scene.lights.add(
                new SpotLight(new Color(128,128,128),new Point3D(30,0,-20),new Vector(0,0,1)));

        Render render = new Render() //
                .setImageWriter(new ImageWriter("Snow Ball", 500, 500)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();

    }
}
