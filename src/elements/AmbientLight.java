package elements;

import primitives.Color;

public class AmbientLight {

    private Color intensity;

    public AmbientLight(Color Ia, double kA){
        intensity=new Color(Ia.scale(kA));
    }

    //Getter
    public Color getIntensity() {
        return intensity;
    }


}
