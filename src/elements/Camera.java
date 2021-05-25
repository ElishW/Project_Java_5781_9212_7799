package elements;

import primitives.*;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {

    /**
     * Class camera
     */

    private Point3D p0;				//camera localization
    private Vector vTo;				//vector director "to" of the camera
    private Vector vUp;				//vector director "up" of the camera
    private Vector vRight;			//vector director "right" of the camera
    private double width;			//width of the view plane
    private double height;			//height of the view plane
    private double distance;		//distance from the camera to the view plane



    @Override
    public String toString() {
        return "Camera [p0=" + p0 + ", vTo=" + vTo + ", vUp=" + vUp + ", vRight=" + vRight + ", width=" + width
                + ", height=" + height + ", distance=" + distance + "]";
    }


    /**
     * @return the p0
     */
    public Point3D getp0() {
        return p0;
    }


    /**
     * @return the vTo
     */
    public Vector getvTo() {
        return vTo;
    }


    /**
     * @return the vUp
     */
    public Vector getvUp() {
        return vUp;
    }


    /**
     * @return the vRight
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * @param p0
     * @param vTo
     * @param vUp
     */
    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        if (vUp.dotProduct(vTo)==0) {
            this.p0 = p0;
            this.vTo = vTo;
            this.vUp = vUp;
            this.vRight= vTo.crossProduct(vUp);
            this.vTo.normalize();
            this.vUp.normalize();
            this.vRight.normalize();
        }
        else {
            throw new IllegalArgumentException("The vector vUp have to be orthogonal to vto");
        }
    }

    /**
     *
     * @param width
     * @param height
     * @return the object Camera
     */
    public Camera setViewPlaneSize(double width, double height) {
        this.width=width;
        this.height=height;
        return this;
    }


    /**
     *
     * @param distance
     * @return the object Camera
     */
    public Camera setDistance(double distance)
    {
        this.distance=distance;
        return this;
    }


    /**
     *
     * @param nX : number of columns : width of a row
     * @param nY : number of rows : height of a column
     * @param j : column of the pixel
     * @param i : row of the pixel
     * @return the Ray constructed through the pixel
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i)
    {
        Point3D Pc = p0.add(vTo.scale(distance));

        double Rx = width / nX;
        double Ry = height / nY;

        Point3D Pij = Pc;

        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yi = -(i - (nY - 1) / 2d) * Ry;

        if (isZero(Xj) && isZero(Yi)) {
            return new Ray(p0, Pij.subtract(p0));
        }
        if (isZero(Xj)) {
            Pij = Pij.add(vUp.scale(Yi));
            return new Ray(p0, Pij.subtract(p0));
        }
        if (isZero(Yi)) {
            Pij = Pij.add(vRight.scale(Xj));
            return new Ray(p0, Pij.subtract(p0));
        }

        Pij = Pij.add(vRight.scale(Xj).add(vUp.scale(Yi)));
        return new Ray(p0, Pij.subtract(p0));
    }

    /**
     * Move camera (for bonus)
     * @param right shift
     * @param to shift
     * @param up shift
     */
    public Camera moveCamera(double up, double right, double to) {
        if (up == 0 && right == 0 && to == 0) return this;
        if (up != 0) this.p0.add(vUp.scale(up));
        if (right != 0) this.p0.add(vRight.scale(right));
        if (to != 0) this.p0.add(vTo.scale(to));
        return this;
    }

    /**
     * Turn Camera (for bonus)
     * @param axis is the new direction vector
     * @param theta is the angle of rotation
     */
    public Camera turnCamera(Vector axis, double theta) {
        if (theta == 0) return this;
        // this.vUp.rotateVector(axis, theta);
        //  this.vRight.rotateVector(axis, theta);
        // this.vTo.rotateVector(axis, theta);
        return this;
    }

}