package elements;
import primitives.*;

/**
 * Adding lights sources with Light class
 */
abstract class Light {

     private final Color intensity;

     /*
     Constructor
      */
     protected Light(Color color){
      this.intensity=color;
     }

 /**
  * Getter
  * @return intensity
  */
 public Color getIntensity() {
      return intensity;
     }

}
