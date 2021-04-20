package unittests;

import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import static primitives.Util.isZero;
import static org.junit.Assert.*;

public class Point3DTest {

    @Test
    public void subtract() {
        Point3D p1=new Point3D (1,2,3);
        // ============ Equivalence Partitions Tests ==============
        Point3D p2=new Point3D (2,4,6);
        Vector vr= p2.subtract(p1);
        Vector expected= new Vector(1,2,3);
        assertEquals("subtract() wrong result ",expected,vr );
        // =============== Boundary Values Tests ==================
        Point3D p3 = new Point3D(1, 2, 3);
        assertThrows("subtract() does not throw an exception",
                IllegalArgumentException.class, () -> p1.subtract(p3));
    }

    @Test
    public void add() {
        Point3D p1 = new Point3D(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(1, 2, 3);
        Point3D p = p1.add(v2);
        Point3D expected = new Point3D(2, 4, 6);
        assertEquals("addVector() wrong result ", expected, p);
        // =============== Boundary Values Tests ==================
        Vector v3 = new Vector(-1, -2, -3);
        Point3D po = p1.add(v3);
        try {
            Vector v = new Vector(po);
            fail("add() does not throw an exception");
        } catch(Exception e) {

        }
    }

    @Test
    public void distanceSquared() {
        Point3D p1=new Point3D (10,7,8);
        // ============ Equivalence Partitions Tests ==============
        Point3D p2=new Point3D (6,4,8);
        double ds =p1.distanceSquared(p2);
        double expected= 5 ;
        assertEquals("distanceSquared() wrong result ",expected,ds,0.00001);
    }

    @Test
    public void distance() {
        Point3D p1=new Point3D (10,7,8);
        // ============ Equivalence Partitions Tests ==============
        Point3D p2=new Point3D (6,4,8);
        double ds =p1.distance(p2);
        double expected= 25 ;
        assertEquals("distanceSquared() wrong result ",expected,ds,0.00001);
    }
}