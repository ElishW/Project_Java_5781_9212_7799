package unittests;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class SphereTest {

    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Test ==============
        //TC01: regular values
        Sphere s = new Sphere(new Point3D(1,2,3),2);
        Point3D p= new Point3D(2,3,2);
        assertEquals("getNormal() doesn't work properly",new Vector(1/Math.sqrt(3),1/Math.sqrt(3),-1/Math.sqrt(3)),s.getNormal(p));
    }
}