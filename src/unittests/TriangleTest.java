package unittests;

import geometries.Polygon;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import static primitives.Util.*;
import static org.junit.Assert.*;

public class TriangleTest {

    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Triangle t = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to triangle", new Vector(sqrt3, sqrt3, sqrt3), t.getNormal(new Point3D(0, 0, 1)));
    }

    @Test
    public void findIntersections() {
    }
}