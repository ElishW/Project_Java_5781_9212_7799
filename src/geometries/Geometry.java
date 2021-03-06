package geometries;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;


public abstract class Geometry implements Intersectable {


    protected Color emission = Color.BLACK;
    public abstract Vector getNormal(Point3D p);
    private Material material=new Material();

    /**
     * getter of emission field
     * @return emission value
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * getter of material field
     * @return material value
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * setter of emission field according to builder pattern
     */
    public Geometry setEmission(Color color){
        emission=color;
        return this;
    }
    /**
     * setter of material field according to builder pattern
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}