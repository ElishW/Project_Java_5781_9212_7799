package unittests;

import org.junit.Test;
import geometries.*;
import primitives.*;

import static org.junit.Assert.*;

public class GeometriesTest {

    @Test
    public void findIntersections() {
        Sphere mySphere = new Sphere(new Point3D(1, 0, 0), 1d);
        Triangle myTriangle = new Triangle (new Point3D(4,1,0),new Point3D(4,-1,0),new Point3D(4,0,1));
        Plane myPlane = new Plane(new Point3D(-1,0,0), new Vector (new Point3D (1,0,0)));

        // =============== Boundary Values Tests ==================

        // **** Group: there isn't any shapes in geometries
        // TC01:No object in List<Intersectable> geometries
        Geometries myGeometries = new Geometries();
        assertNull("There is any object",
                myGeometries.findIntersections(new Ray(new Point3D(6,0,0), new Vector(0,1,0))));


        // **** Group: there are a sphere, a triangle and a plane in geometries
        myGeometries.add(mySphere, myTriangle, myPlane);

        // TC02:No object intersected in List<Intersectable>
        assertNull("There is no object intersected",
                myGeometries.findIntersections(new Ray(new Point3D(6,0,0), new Vector(0,1,0))));

        //TC03:Only one object intersected
        assertEquals("Only one object intersected",1,
                myGeometries.findIntersections(new Ray(new Point3D(0,2,0), new Vector(-1, 0, 0))).size());

        //TC04:All shapes are intersected
        assertEquals("All shapes are intersected",4,
               myGeometries.findIntersections(new Ray(new Point3D(6,0,0.5), new Vector(-1, 0, 0))).size());


        // =============== Equivalence Partitions Tests ==================
        //TC05: There are shapes intersected but not all intersected
        assertEquals("There are shapes intersected but not all",2,
                myGeometries.findIntersections(new Ray(new Point3D(1,0,0), new Vector(-1, 0, 0))).size());


    }
}