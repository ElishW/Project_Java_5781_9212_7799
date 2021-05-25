package elements;
import primitives.*;
/**
 * LightSource interface
 */
public interface LightSource {
    /**
     * This function calculates the intensity of a points and returns the new color
     * @param p
     * @return the new color with intensity
     */
    public Color getIntensity(Point3D p);

    /**
     * This function calculates and returns the position of the light source
     * @param p
     * @return a directional vector
     */
    public Vector getL(Point3D p);

    /**
     * Return the distance from the light source to the point
     * @param point
     * @return
     */
    public double getDistance(Point3D point);

}