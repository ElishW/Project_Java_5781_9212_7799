package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

public class Scene {

    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight(Color.BLACK,0);
    public Geometries geometries = new Geometries();

    public Scene(String _name){
        name=_name;
        geometries=new Geometries();
    }

    /**
     * Builder Scene
     */
    public static class BuilderScene{

        public Color background = Color.BLACK;
        public AmbientLight ambientLight = new AmbientLight(Color.BLACK,0);
        public Geometries geometries = new Geometries();

        public BuilderScene setBackground(Color _color){
            background=_color;
            return this;
        }
        public BuilderScene setAmbientLight(AmbientLight _amLight){
            ambientLight=_amLight;
            return this;
        }
        public BuilderScene setGeometries(Geometries _geo){
            geometries=_geo;
            return this;
        }

    }
}
