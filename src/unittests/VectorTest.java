package unittests;

import org.junit.Test;
import primitives.Coordinate;
import primitives.Vector;

import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

import java.awt.*;

import static java.lang.System.out;
import static org.junit.Assert.*;
import static primitives.Util.isZero;

public class VectorTest {

    @Test
    public void testAdd() {
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
    public void testSubstractV() {
        Vector v1=new Vector (1,2,3);
        // ============ Equivalence Partitions Tests ==============
        Vector v2=new Vector (2,4,6);
        Vector vr=v2.substractV(v1);
        Vector expected= new Vector(1,2,3);
        assertEquals("subtractV() wrong result ",expected,vr );
        // =============== Boundary Values Tests ==================
        Vector v3 = new Vector(1, 2, 3);
        assertThrows("subtractV() does not throw an exception",
                IllegalArgumentException.class, () -> v1.substractV(v3));

    }

    @Test
    public void testSubstractP() {
        // ============ Equivalence Partitions Tests ==============
        // TC01:when the head of the vector and the point aren't equal
        Vector v1 = new Vector(1, 2, 3);
        Point3D p1=new Point3D(3,2,1);
        Vector vr=v1.substractP(p1);
        assertEquals("substractP() wrong result",new Vector(-2,0,2),vr);

        // ============ Boundary Values Test ==============
        // TC02: the head of the vector and the point are equal so the result will be zero vector
        Point3D p3 = new Point3D(1, 2, 3);
        assertThrows("substractP() for opposite vectors does not throw an exception",
                IllegalArgumentException.class, () -> v1.substractP(p3));
    }

    @Test
    public void testScale() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: the parameter s is != 0
        Vector v1 = new Vector(1, 2, 3);
        int s1=2;
        Vector vr=v1.scale(s1);
        assertEquals("scale() function result is wrong",new Vector(2,4,6),vr);

        // ============ Equivalence Partitions Tests ==============
        //TC02: the parameter s == 0
        int s2=0;
        assertThrows("scale() for parameter s=0 does not throw an exception",
                IllegalArgumentException.class, () -> v1.scale(s2));
    }

    @Test
    public void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01:the angle between the vectors is between 0-90
        assertTrue("dotProduct(Vector) result", Util.isZero(1 - new Vector(1, 0, 0).dotProduct(new Vector(1, 1, 0))));
        // TC02:the angle between the vectors is between 90 -180
        assertTrue("dotProduct(Vector) result",Util.isZero(-1 - new Vector(1, 0, 0).dotProduct(new Vector(-1, 1, 0))));

        // =============== Boundary Values Tests ==================
        // TC03: multiply parallel vectors
        assertTrue("dotProduct(Vector) result",Util.isZero(28 - new Vector(1, 2, 3).dotProduct(new Vector(2, 4, 6))));
        // TC04: multiply by opposite vector
        assertTrue("dotProduct(Vector) result",Util.isZero(-14 - new Vector(1, 2, 3).dotProduct(new Vector(-1, -2, -3))));
        // TC05: multiply by the equal vector
        assertTrue("dotProduct(Vector) result",Util.isZero(14 - new Vector(1, 2, 3).dotProduct(new Vector(1, 2, 3))));
        // TC06:multiply by orthogonal vector
        assertTrue("dotProduct(Vector) result",Util.isZero(new Vector(1, 0, 0).dotProduct(new Vector(0, 1, 0))));
        // TC07:multiply vectors with the same length
        assertTrue("dotProduct(Vector) result",Util.isZero(15 - new Vector(3, 4, 0).dotProduct(new Vector(5, 0, 0))));
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
    public void testLengthSquared() {
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
    public void testLength() {
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
    public void testNormalized() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: verify if the function creates a new vector
        Vector v1=new Vector(1,2,3);
        Vector v2=v1.normalized();
        assertTrue("normalized() function doesn't create a new object",v1!=v2);

        // =============== Boundary Values Tests ==================
        //TC02: if the vector already normalized so normalized func creates a new object but they are equals
        Vector v3 = new Vector(1,0,1);
        assertTrue("normalized() result is wrong", v3.normalized()!=v3);
    }

    @Test
    public void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(v1.getHead());
        Vector v3 = v2.normalize();
        //TC01: verify if the function doesn't create a new object
        assertFalse("normalize() function creates a new object",v2!=v3);
        //TC02: verify if the function returns a vector which the length equals 1
        assertFalse("normalize() result is not a unit vector",v1.normalize().length()==0);
        //TC03: verify if the function returns a vector which the length equals 1
        assertTrue("normalize() result is not a unit vector",v1.normalize().length()==1)  ;

    }

}