package unittests;

import geometries.Plane;
import geometries.Polygon;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.*;
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
        assertTrue("the normal vector length different to 1",p.getNormal(new Point3D(0,0,0)).length()==1);

    }

    @Test
    public void findIntsersections() {
        Plane pl = new Plane(new Point3D(0, 0, 1), new Vector(1, 1, 1));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray into plane
        assertEquals("Bad plane intersection",
                List.of(new Point3D(1, 0, 0)),
                pl.findIntersections(new Ray(new Point3D(0.5, 0, 0), new Vector(1, 0, 0)))
                );

        // TC02: Ray out of plane
        assertNull("Must not be plane intersection",
                pl.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 0, 0)))
                );

        // =============== Boundary Values Tests ==================
        // TC11: Ray parallel to plane
        assertNull("Must not be plane intersection",
                pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(0, 1, -1)))
                );

        // TC12: Ray in plane
        assertNull("Must not be plane intersection",
                pl.findIntersections(new Ray(new Point3D(0, 0.5, .5), new Vector(0, 1, -1)))
                );


        // TC13: Orthogonal ray into plane
        assertEquals("Bad plane intersection",
                List.of(new Point3D(1d / 3, 1d / 3, 1d / 3)),
                pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(-1, -1, -1)))
                );

        // TC14: Orthogonal ray out of plane
        assertNull("Must not be plane intersection",
                pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 1, 1)))
                );

        // TC15: Orthogonal ray out of plane
        assertNull("Must not be plane intersection",
                pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 1, 1)))
                );

        // TC16: Orthogonal ray from plane
        assertNull("Must not be plane intersection",
                pl.findIntersections(new Ray(new Point3D(0, 0.5, 0.5), new Vector(1, 1, 1)))
                );

        // TC17: Ray from plane
        assertNull("Must not be plane intersection",
                pl.findIntersections(new Ray(new Point3D(0, 0.5, 0.5), new Vector(1, 1, 0)))
                );

        // TC18: Ray from plane's Q point
        assertNull("Must not be plane intersection",
                pl.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(1, 1, 0)))
                );

    }

}