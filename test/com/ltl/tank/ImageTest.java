package com.ltl.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Test;

/**
 * liutongliang
 * 2020-07-2020/7/26
 */
public class ImageTest {

    @Test
    public void testImage() {
        try {
            BufferedImage image = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD"
                        + ".gif"));
            Assert.assertNotNull(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
