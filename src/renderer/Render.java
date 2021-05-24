package renderer;

import elements.Camera;
import scene.Scene;
import primitives.*;

import java.util.MissingResourceException;

public class Render {
     ImageWriter imageWriter;
   // private Scene scene;
     Camera camera;
     RayTracerBase rayTracer;

    public Render setImageWriter(ImageWriter _imageWriter) {
        imageWriter = _imageWriter;
        return this;
    }

 /*   public Render setScene(Scene _scene) {
        scene = _scene;
        return this;
    }
*/
    public Render setCamera(Camera _camera) {
        camera = _camera;
        return this;
    }

    public Render setRayTracer(RayTracerBase _rayTracer) {
        this.rayTracer = _rayTracer;
        return this;
    }

    public void renderImage() {
        try
        {
            if (imageWriter == null)
                throw new MissingResourceException("missing imageWriter", ImageWriter.class.getName(), "");
           // if (scene == null)
             //   throw new MissingResourceException("missing scene", Scene.class.getName(), "");
            if (camera == null)
                throw new MissingResourceException("missing camera", Camera.class.getName(), "");
            if (rayTracer == null)
                throw new MissingResourceException("missing rayTracerBase", RayTracerBase.class.getName(), "");
        }
        catch (MissingResourceException e)
        {
            throw new UnsupportedOperationException("Not implemented" + e.getClassName());
        }
        Color pixelColor;
        for (int i=0; i<imageWriter.getNx(); i++) {
            for (int j=0; j<imageWriter.getNy(); j++) {
                pixelColor = rayTracer.traceRay(camera.constructRayThroughPixel(imageWriter.getNx(), imageWriter.getNy(),i,j));
                imageWriter.writePixel(i,j,pixelColor);	 //color the pixel
            }
        }
        }
        public void printGrid ( int interval, Color color)
        {
            if (imageWriter == null)
                throw new MissingResourceException("missing imageWriter", ImageWriter.class.getName(), "");
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();

            for (int i = 0; i < nX; i +=interval) {
                for (int j = 0; j < nY; j++) {
                    imageWriter.writePixel(i, j, color);
                }
            }
            for (int i = 0; i < nY; i +=interval) {
                for (int j = 0; j < nX; j++) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }

        public void writeToImage ()
        {
            if (imageWriter == null)
                throw new MissingResourceException("missing imageWriter", ImageWriter.class.getName(), "");
            imageWriter.writeToImage();
        }


}


