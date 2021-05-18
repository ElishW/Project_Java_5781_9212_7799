package elements;

import primitives.Color;

public class AmbientLight extends Light {

    public AmbientLight(){
        super(Color.BLACK);
    }

    public AmbientLight(Color Ia, double kA){
        super(new Color(Ia.scale(kA)));
    }



}
