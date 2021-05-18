package elements;
import primitives.*;

public class SpotLight extends PointLight{

    private Vector direction;


    /*
    Constructor with parameters
     */
    public SpotLight(Color col, Point3D point, double _kC, double _kL, double _kQ, Vector v) {
        super(col, point, _kC, _kL, _kQ);
        direction = v;
    }

    /**
     * This function calculates the intensity of a points and returns the new color
     *
     * @param p
     * @return the new color with intensity
     */
    @Override
    public Color getIntensity(Point3D p) {
        Color tmp = super.getIntensity(p);
        Vector dir = direction.normalized();
        return tmp.scale(Math.max(0,dir.dotProduct(direction)));
    }

    /**
     * This function calculates and returns the direction of the light source
     *
     * @param p
     * @return a directional vector
     */
    @Override
    public Vector getL(Point3D p) {
        return direction;
    }
}
