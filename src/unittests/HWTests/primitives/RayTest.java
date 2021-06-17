package unittests.HWTests.primitives;

import geometries.Geometry;
import geometries.Intersectable.GeoPoint;

import geometries.*;
import org.junit.Test;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class RayTest {

    @Test
    public void findClosestPointTest() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: the closest point is in the middle of the list
            Ray ray = new Ray(new Point3D(0, 0, 10), new Vector(1, 10, -100));

            List<Point3D> list = new LinkedList<Point3D>();
            list.add(new Point3D(1, 1, -100));
            list.add(new Point3D(-1, 1, -99));
            list.add(new Point3D(0, 2, -10));
            list.add(new Point3D(0.5, 0, -100));

            assertEquals(list.get(2), ray.findClosestPoint(list));

        // =============== Boundary Values Tests ==================
        //TC01: the list is empty
        List<Point3D> list2 = null;
        assertNull("try again",ray.findClosestPoint(list2));

        //TC11: the closest point is the first in the list
        List<Point3D> list3 = new LinkedList<Point3D>();
        list3.add(new Point3D(0, 2, -10));
        list3.add(new Point3D(-1, 1, -99));
        list3.add(new Point3D(1, 1, -100));
        list3.add(new Point3D(0.5, 0, -100));
        assertEquals(list3.get(0), ray.findClosestPoint(list3));

        //TC12: the closest point is the last in the list
        List<Point3D> list4 = new LinkedList<Point3D>();
        list4.add(new Point3D(1, 1, -100));
        list4.add(new Point3D(0.5, 0, -100));
        list4.add(new Point3D(-1, 1, -99));
        list4.add(new Point3D(0, 2, -10));
        assertEquals(list4.get(3), ray.findClosestPoint(list4));

    }


    @Test
    public void findClosestGeoPointTest() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: the closest point is in the middle of the list
        Ray ray = new Ray(new Point3D(0, 0, 10), new Vector(1, 10, -100));
        Geometry geo = new Sphere(new Point3D(1,1,1),2);

        List<Intersectable.GeoPoint> list = new LinkedList<Intersectable.GeoPoint>();
        list.add(new GeoPoint(geo,new Point3D(1, 1, -100)));
        list.add(new GeoPoint(geo,new Point3D(-1, 1, -99)));
        list.add(new GeoPoint(geo,new Point3D(0, 2, -10)));
        list.add(new GeoPoint(geo,new Point3D(0.5, 0, -100)));



        assertEquals(list.get(2), ray.findClosestGeoPoint(list));

        // =============== Boundary Values Tests ==================
        //TC01: the list is empty
        List<GeoPoint> list2 = null;
        assertNull("try again",ray.findClosestGeoPoint(list2));

        //TC11: the closest point is the first in the list
        List<GeoPoint> list3 = new LinkedList<GeoPoint>();
        list3.add(new GeoPoint(geo,new Point3D(0, 2, -10)));
        list3.add(new GeoPoint(geo,new Point3D(-1, 1, -99)));
        list3.add(new GeoPoint(geo,new Point3D(1, 1, -100)));
        list3.add(new GeoPoint(geo,new Point3D(0.5, 0, -100)));

        assertEquals(list3.get(0), ray.findClosestGeoPoint(list3));

        //TC12: the closest point is the last in the list
        List<GeoPoint> list4 = new LinkedList<GeoPoint>();
        list4.add(new GeoPoint(geo,new Point3D(1, 1, -100)));
        list4.add(new GeoPoint(geo,new Point3D(0.5, 0, -100)));
        list4.add(new GeoPoint(geo,new Point3D(-1, 1, -99)));
        list4.add(new GeoPoint(geo,new Point3D(0, 2, -10)));

        assertEquals(list4.get(3), ray.findClosestGeoPoint(list4));
    }
}