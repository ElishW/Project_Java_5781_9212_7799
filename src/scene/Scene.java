package scene;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

public class Scene {


        private final String _name;

        public Color background = Color.BLACK;
        public AmbientLight ambientlight= new AmbientLight(); ;
        public Geometries geometries = new Geometries();
//new Color(192, 192, 192),1.d


    public List<LightSource> lights = new LinkedList<LightSource>();

        public Scene(String name) {
            _name = name;
            geometries= new Geometries();
        }

        //chaining set methods (with builder pattern)

        public Scene setBackground(Color background) {
            this.background = background;
            return  this;
        }

        public Scene setAmbientLight(AmbientLight ambientlight) {
            this.ambientlight = ambientlight;
            return this;
        }

        public Scene setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return  this;
        }
        public Scene setLights(List<LightSource> lights) {
            this.lights = lights;
            return this;
        }

}
