package elements;
import primitives.*;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Camera {

    private Point3D p0;
    private Vector vUp , vTo, vRight;
    private double width, height, distance;

    public Point3D getP0() {
        return p0;
    }

    public Vector getvUp() {
        return vUp;
    }

    public Vector getvTo() {
        return vTo;
    }


    public Vector getvRight() {
        return vRight;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDistance() {
        return distance;
    }

    public Camera (Point3D _P0, Vector _vTo, Vector _vUp){
        if(isZero(_vTo.dotProduct(_vUp))) {
            p0 = _P0;
            vTo = _vTo;
            vUp = _vUp;
            vRight = _vTo.crossProduct(_vUp);
            vTo.normalize();
            vUp.normalize();
            vRight.normalize();
        }
        else
            throw new IllegalArgumentException("vto and vup are not orthogonal");


    }

    public Camera setViewPlaneSize(double _width, double _height)
    {
        this.width=_width;
        this.height=_height;
        return this;
    }
    public Camera setDistance(double _distance)
    {
        this.distance=_distance;
        return this;
    }

    public Ray constructRayThroughPixel(int nX, int nY, int j, int i)
    {
        double Rx = width / nX;
        double Ry = height / nY;

        Point3D Pc = p0.add(vTo.scale(distance));
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


}
