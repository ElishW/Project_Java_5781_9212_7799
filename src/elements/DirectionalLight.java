package elements;

import primitives.*;

public class DirectionalLight extends Light implements LightSource{

    private Vector direction;


    /**
     Constructor with 2 parameters
     **/
    public DirectionalLight(Color col,Vector v){
        super(col);
        direction=v;
    }

    /**
     * This function calculates the intensity of a points and returns the new color
     *
     * @param p
     * @return the new color with intensity
     */
    @Override
    public Color getIntensity(Point3D p) {
        return intensity;
    }

    /**
     * This function calculates and returns the position of the light source
     *
     * @param p
     * @return a directional vector
     */
    @Override
    public Vector getL(Point3D p) {
        return direction.normalize();
    }

    /**
     * Return the distance from the light source to the point
     *
     * @param point
     * @return
     */
    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY; //because the distance is too big that the difference between two point is neglected
    }
}