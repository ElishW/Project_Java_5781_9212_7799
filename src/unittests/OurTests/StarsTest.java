package unittests.OurTests;
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

public class StarsTest {
    @Test
    public void drawStars(){
        Scene scene = new Scene("Stars").setBackground(new Color(0,0,45));//
        // .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        Camera camera = new Camera(new Point3D(0, 0, -150), new Vector(0, 0, 1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(150, 150).setDistance(100);
        Coordinate xa = new Coordinate(0);
        Point3D a = new Point3D(0, 30, 0);
        Point3D b=new Point3D(-1, 29, 0);
        Point3D c=new Point3D(1, 29, 0);
        Point3D d=new Point3D(-1, 29.75, 0);
        Point3D e=new Point3D(1,29.75,0);
        Point3D f=new Point3D(0, 28.75, 0);
        scene.geometries.add(
                new Triangle(a,b ,c)
                        .setEmission(new Color(java.awt.Color.WHITE)) //
                        .setMaterial(new Material().setKt(0.99)),
                new Triangle(d,e ,f)
                        .setEmission(new Color(java.awt.Color.WHITE)) //
                        .setMaterial(new Material().setKt(0.99))

        );



        for(double i=2;i>1;i-=0.1){
            a=new Point3D(a.getX(),a.getY()-i,a.getZ());
            b=new Point3D(c.getX()+i,c.getY()-i,c.getZ());
            c=new Point3D(c.getX()+2-i,c.getY()-i,c.getZ());
            d=new Point3D(e.getX()+i,e.getY()-i,e.getZ());
            e=new Point3D(e.getX()+2-i,e.getY()-i,e.getZ());
            f=new Point3D(f.getX()+2-i,f.getY()+i,f.getZ());
            scene.geometries.add(
                    new Triangle(a,b ,c)
                            .setEmission(new Color(java.awt.Color.WHITE)) //
                            .setMaterial(new Material().setKt(0.99)),
                    new Triangle(d,e ,f)
                            .setEmission(new Color(java.awt.Color.WHITE)) //
                            .setMaterial(new Material().setKt(0.99))
            );
        }

        scene.lights.add( //
                new SpotLight(new Color(java.awt.Color.WHITE),  new Point3D(-80, -90, 30), new Vector(2, 2, -6))//
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add( //
                new SpotLight(new Color(java.awt.Color.WHITE),  new Point3D(30, 30, 10), new Vector(2, 2, -6))//
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render() //
                .setImageWriter(new ImageWriter("Stars", 500, 500)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void SpiralTest(){
        Scene scene = new Scene("Stars").setBackground(new Color(0,0,45));//
        // .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        Camera camera = new Camera(new Point3D(0, 0, -250), new Vector(0, 0, 1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(150, 150).setDistance(100);
        Camera cameraX = new Camera(new Point3D(-250, 0, 0), new Vector(1, 0, 0), new Vector(0, 1, 0)) //
                .setViewPlaneSize(150, 150).setDistance(100);


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
                new SpotLight(new Color(java.awt.Color.WHITE),  new Point3D(-80, -90, 30), new Vector(2, 2, -6))//
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add( //
                new SpotLight(new Color(java.awt.Color.WHITE),  new Point3D(30, 30, 10), new Vector(2, 2, -6))//
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render() //
                .setImageWriter(new ImageWriter("Spiral", 500, 500)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
}
