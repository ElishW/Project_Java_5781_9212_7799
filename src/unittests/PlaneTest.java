package unittests;

import geometries.Plane;
import geometries.Polygon;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class PlaneTest {

    @Test
    public void testConstructor(){
        // ============ Equivalence Partitions Tests ==============

        //TC01: regular values
        try {
            new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct plane");
        }

        // =============== Boundary Values Tests ==================

        //TC10 : the first and second point are the same point
        try {
            new Plane(new Point3D(0, 0, 1), new Point3D(0, 0, 1),
                    new Point3D(0, 1, 0));
            fail("Constructed a plane with two same points");

        } catch (IllegalArgumentException e) {}

        //TC11 : third points are on the same line
        try {
            new Plane(new Point3D(0, 0, 1), new Point3D(0, 0, 2),
                    new Point3D(0, 0, 4));
            fail("Constructed a plane three points on the same line");

        } catch (IllegalArgumentException e) {}

    }

    @Test
    public void getNormal() {
        Plane p = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to triangle", new Vector(sqrt3, sqrt3, sqrt3), p.getNormal());
        assertTrue("the normal vector length different to 1",p.getNormal().length()==1);

    }
}