package unittests;

import org.junit.Test;
import primitives.Coordinate;
import primitives.Vector;


import static org.junit.Assert.*;
import static primitives.Util.isZero;

public class VectorTest {

    @Test
    public void add() {
        Vector v1=new Vector (1,2,3);
        // ============ Equivalence Partitions Tests ==============
        Vector v2=new Vector (1, 2, 3);
        Vector vr=v1.add(v2);
        Vector expected= new Vector(2,4,6);
        assertEquals("addVector() wrong result ",expected,vr );
        // =============== Boundary Values Tests ==================
        Vector v3 = new Vector(-1, -2, -3);
        assertThrows("add() does not throw an exception",
                IllegalArgumentException.class, () -> v1.add(v3));
        //try {
        //    v1.crossProduct(v2);
        //    fail("add() does not throw an exception");
        //} catch (Exception e) {}

    }

    @Test
    public void subtractV() {
        Vector v1=new Vector (1,2,3);
        // ============ Equivalence Partitions Tests ==============
        Vector v2=new Vector (2,4,6);
        Vector vr=v2.subtractV(v1);
        Vector expected= new Vector(1,2,3);
        assertEquals("subtractV() wrong result ",expected,vr );
        // =============== Boundary Values Tests ==================
        Vector v3 = new Vector(1, 2, 3);
        assertThrows("subtractV() does not throw an exception",
                IllegalArgumentException.class, () -> v1.subtractV(v3));
        //try {
        //    v1.crossProduct(v2);
        //    fail("subtractV() does not throw an exception");
        //} catch (Exception e) {}
    }

    @Test
    public void substractP() {
    }

    @Test
    public void scale() {
    }

    @Test
    public void dotProduct() {
    }

    @Test
    public void testCrossProduct() {
         Vector v1 = new Vector(1, 2, 3);

         // ============ Equivalence Partitions Tests ==============
         Vector v2 = new Vector(0, 3, -2);
         Vector vr = v1.crossProduct(v2);

         // TC01: Test that length of cross-product is proper (orthogonal vectors taken
         // for simplicity)
         assertEquals("crossProduct() wrong result length", v1.length() * v2.length(), vr.length(), 0.00001);

         // TC02: Test cross-product result orthogonality to its operands
         assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
         assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v2)));

         // =============== Boundary Values Tests ==================
         // TC11: test zero vector from cross-productof co-lined vectors
         Vector v3 = new Vector(-2, -4, -6);
         assertThrows("crossProduct() for parallel vectors does not throw an exception",
                 IllegalArgumentException.class, () -> v1.crossProduct(v3));
         // try {
         //     v1.crossProduct(v2);
         //     fail("crossProduct() for parallel vectors does not throw an exception");
         // } catch (Exception e) {}
    }




    @Test
    public void lengthSquared() {
        Vector v1=new Vector (4,3,0);
        // ============ Equivalence Partitions Tests ==============
        double ls =v1.lengthSquared();
        double expected= 25 ;
        assertEquals("lengthSquared() wrong result ",expected,ls,0.00001);
        // =============== Boundary Values Tests ==================
       /* Vector v3 = new Vector(0, 0, 0);
        assertThrows("lengthSquared() does not throw an exception",
                IllegalArgumentException.class, () -> v3.lengthSquared());*/
    }

    @Test
    public void length() {
        Vector v1=new Vector (4,3,0);
        // ============ Equivalence Partitions Tests ==============
        double l =v1.length();
        double expected= 5 ;
        assertEquals("length() wrong result ",expected,l,0.00001);
        // =============== Boundary Values Tests ==================
       /* Vector v3 = new Vector(0, 0, 0);
        assertThrows("length() does not throw an exception",
                IllegalArgumentException.class, () -> v3.length());*/
    }

    @Test
    public void normalized() {
    }

    @Test
    public void normalize() {
    }

}