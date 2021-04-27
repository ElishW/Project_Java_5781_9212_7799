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
        if(isZero(_vTo.dotProduct(_vUp))){
            p0 = _P0;
            vTo=_vTo;
            vUp=_vUp;
            vRight=_vTo.crossProduct(_vUp);
            vTo.normalize();
            vUp.normalize();
            vRight.normalize();

        }

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
     //Point3D Pc= this.p0+ (this.distance).scale(this.vTo);
        return null;
    }

}
