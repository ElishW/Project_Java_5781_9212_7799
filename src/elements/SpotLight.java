package elements;
import primitives.*;
import primitives.Point3D;
import primitives.Vector;
public class SpotLight extends PointLight {

    private Vector direction;


    /*
    Constructor with parameters
     */

    public SpotLight(Color col, Point3D point, Vector v) {
        super(col, point);
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
        return super.getIntensity(p).scale(Math.max(direction.normalize().dotProduct(getL(p)), 0));
    }

}
