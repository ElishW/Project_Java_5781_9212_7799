package unittests.HWTests.elements;
import static org.junit.Assert.*;

import elements.Camera;
import geometries.*;
import org.junit.Test;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

public class CameraRayIntersectionsIntegrationTests {

    /**
     * Test help function to count the intersections and compare with expected value
     *
     * @param cam      camera for the test
     * @param geo      3D body to test the integration of the camer with
     * @param expected amount of intersections
     */
    private void assertCountIntersections(Camera cam, Intersectable geo, int expected) {
        int count = 0;

        List<Point3D> lstOfPoints = null;

        cam.setViewPlaneSize(3, 3);
        cam.setDistance(1);
        int nX =3;
        int nY =3;
        //view plane 3X3 (WxH 3X3 & nx,ny =3 => Rx,Ry =1)
        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                var intersections = geo.findIntersections(cam.constructRayThroughPixel(nX, nY, j, i));
                if (intersections != null) {
                    if (lstOfPoints == null) {
                        lstOfPoints = new LinkedList<>();
                    }
                    lstOfPoints.addAll(intersections);
                }
                count += intersections == null ? 0 : intersections.size();
            }
        }

        if (lstOfPoints != null) {
            for (var item : lstOfPoints) {
                System.out.println(item);
            }
        }

        assertEquals("Wrong number of intersections",expected, count);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Sphere intersections
     */
    @Test
    public void cameraRaySphereIntegration() {
        Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0));
        Camera cam2 = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Small Sphere 2 points
        assertCountIntersections(cam1, new Sphere(new Point3D(0, 0, -3), 1), 2);

        // TC02: Big Sphere 18 points
        assertCountIntersections(cam2, new Sphere(new Point3D(0, 0, -2.5), 2.5), 18);

        // TC03: Medium Sphere 10 points
        assertCountIntersections(cam2, new Sphere(new Point3D(0, 0, -2), 2), 10);

        // TC04: Inside Sphere 9 points
        assertCountIntersections(cam2, new Sphere(new Point3D(0, 0, -1), 4), 9);

        // TC05: Beyond Sphere 0 points
        assertCountIntersections(cam1, new Sphere(new Point3D(0, 0, 1), 0.5), 0);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Plane intersections
     */
    @Test
    public void cameraRayPlaneIntegration() {
        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0))
                ;

        // TC01: Plane against camera 9 points
        assertCountIntersections(cam, new Plane(new Point3D(0, 0, -5), new Vector(0, 0, 1)), 9);

        // TC02: Plane with small angle 9 points
        assertCountIntersections(cam, new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 2)), 9);

        // TC03: Plane parallel to lower rays 6 points
        assertCountIntersections(cam, new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 1)), 6);

        // TC04: Beyond Plane 0 points
        assertCountIntersections(cam, new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 1)), 6);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Triangle intersections
     */
    @Test
    public void cameraRayTriangleIntegration() {
        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Small triangle 1 point
        assertCountIntersections(cam, new Triangle(new Point3D(1, 1, -2), new Point3D(-1, 1, -2), new Point3D(0, -1, -2)), 1);

        // TC02: Medium triangle 2 points
        assertCountIntersections(cam, new Triangle(new Point3D(1, 1, -2), new Point3D(-1, 1, -2), new Point3D(0, -20, -2)), 2);
    }

}
