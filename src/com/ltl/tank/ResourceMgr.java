package com.ltl.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * liutongliang
 * 2020-07-2020/7/26
 */
public class ResourceMgr {

    public static BufferedImage goodTankL1;
    public static BufferedImage goodTankU1;
    public static BufferedImage goodTankR1;
    public static BufferedImage goodTankD1;

    public static BufferedImage goodTankL2;
    public static BufferedImage goodTankU2;
    public static BufferedImage goodTankR2;
    public static BufferedImage goodTankD2;

    public static BufferedImage badTankL1;
    public static BufferedImage badTankU1;
    public static BufferedImage badTankR1;
    public static BufferedImage badTankD1;

    public static BufferedImage badTankL2;
    public static BufferedImage badTankU2;
    public static BufferedImage badTankR2;
    public static BufferedImage badTankD2;


    public static BufferedImage bulletL;
    public static BufferedImage bulletU;
    public static BufferedImage bulletR;
    public static BufferedImage bulletD;

    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            goodTankU1 = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL1 = ImageUtil.rotateImage(goodTankU1, -90);
            goodTankR1 = ImageUtil.rotateImage(goodTankU1, 90);
            goodTankD1 = ImageUtil.rotateImage(goodTankU1, 180);

            badTankU1 = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL1 = ImageUtil.rotateImage(badTankU1, -90);
            badTankR1 = ImageUtil.rotateImage(badTankU1, 90);
            badTankD1 = ImageUtil.rotateImage(badTankU1, 180);

            goodTankU2 = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank2.png"));
            goodTankL2 = ImageUtil.rotateImage(goodTankU2, -90);
            goodTankR2 = ImageUtil.rotateImage(goodTankU2, 90);
            goodTankD2 = ImageUtil.rotateImage(goodTankU2, 180);

            badTankU2 = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank2.png"));
            badTankL2 = ImageUtil.rotateImage(badTankU2, -90);
            badTankR2 = ImageUtil.rotateImage(badTankU2, 90);
            badTankD2 = ImageUtil.rotateImage(badTankU2, 180);



            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);

            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
