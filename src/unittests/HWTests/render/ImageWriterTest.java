package unittests.HWTests.render;
import static org.junit.Assert.*;
import org.junit.Test;

import primitives.Color;
import renderer.*;

public class ImageWriterTest {

    @Test
    public void testWriteToImage(){
        ImageWriter img = new ImageWriter("test1",800,500);
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 500; j++) {
                // 800/16 = 50
                if (i % 50 == 0) {
                    img.writePixel(i, j, new Color(java.awt.Color.DARK_GRAY));
                }
                // 500/10 = 50
                else if (j % 50 == 0) {
                    img.writePixel(i, j,new Color(java.awt.Color.DARK_GRAY));
                } else {
                    img.writePixel(i, j,new Color(java.awt.Color.PINK));
                }
            }
        }
        img.writeToImage();
    }

}
