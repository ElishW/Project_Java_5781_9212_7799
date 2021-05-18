package geometries;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;


public abstract class Geometry implements Intersectable {


    protected Color emission = Color.BLACK;
    public abstract Vector getNormal(Point3D p);

    /**
     * getter of emission field
     * @return emission value
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * setter of emission field according to builder pattern
     */
    public Geometry setEmission(Color color){
        emission=color;
        return this;
    }
}
