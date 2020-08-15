package com.ltl.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * liutongliang
 * 2020-07-2020/7/26
 */
public class Tank {

    private int x;
    private int y;
    private Dir dir;
    private boolean moving;
    private TankFrame frame;
    private boolean live = true;
    private Group group;
    private Random random = new Random();
    private int imageStep;
    private Rectangle rect = new Rectangle();
    private static final int SPEED = 5;
    private static final int maxImageStep = 10;
    private static final int boundFaultTolerant = 4;

    public Tank(int x, int y, Dir dir, boolean moving, Group group, TankFrame frame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.group = group;
        this.frame = frame;
        this.updateRect();
    }

    public void paint(Graphics g) {
        if (moving) {
            move();
        }
        if (!live) {
            this.frame.tanks.remove(this);
        }
        g.drawImage(getTankImage(dir, group, imageStep), x, y, null);
        this.imageStep++;
        if (imageStep >= maxImageStep) {
            imageStep = 0;
        }
    }

    public void randomDir() {
        if (random.nextInt(100) <= 90) {
            return;
        }
        int i = random.nextInt(4);
        switch (i) {
            case 0:
                dir = Dir.LEFT;
                break;
            case 1:
                dir = Dir.UP;
                break;
            case 2:
                dir = Dir.RIGHT;
                break;
            case 3:
                dir = Dir.DOWN;
                break;
            default:
                break;
        }
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getImageStep() {
        return imageStep;
    }

    public void setImageStep(int imageStep) {
        this.imageStep = imageStep;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    private void move() {
        BufferedImage tankImage = getTankImage(dir, group, imageStep);
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
        if (this.group == Group.GOOD) {
            new Thread(() -> new Audio("audio/tank_move.wav").play()).start();
        }
        if (group == Group.BAD && random.nextInt(100) > 97) {
            this.fire();
        }
        if (group == Group.BAD) {
            randomDir();
        }
        boundCheck();
        updateRect();
    }

    public void boundCheck() {
        if (this.x < boundFaultTolerant) {
            this.x = boundFaultTolerant;
        }
        if (this.y < 30) {
            this.y = 30;
        }
        BufferedImage tankImage = getTankImage(dir, group, imageStep);
        if (this.x > this.frame.getWidth() - tankImage.getWidth() - boundFaultTolerant) {
            this.x = this.frame.getWidth() - tankImage.getWidth() - boundFaultTolerant;
        }
        if (this.y > this.frame.getHeight() - tankImage.getHeight() - boundFaultTolerant) {
            this.y = this.frame.getHeight() - tankImage.getHeight() - boundFaultTolerant;
        }
    }

    public void updateRect() {
        this.rect.x = this.x;
        this.rect.y = this.y;
        this.rect.width = getTankImage(dir, group, imageStep).getWidth();
        this.rect.height = getTankImage(dir, group, imageStep).getHeight();
    }

    public void fire() {

        BufferedImage tankImage = getTankImage(dir, group, imageStep);
        BufferedImage bulletImage = Bullet.getBulletImage(dir);
        int bX = (tankImage.getWidth() - bulletImage.getWidth()) / 2 + x;
        int bY = (tankImage.getHeight() - bulletImage.getHeight()) / 2 + y;
        frame.bullets.add(new Bullet(bX, bY, dir, this.group, frame));
        if (this.group == Group.GOOD) {
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }

    public static BufferedImage getTankImage(Dir dir, Group group, int imageStep) {
        if (group == Group.GOOD) {
            return getGoodTankImage(dir, imageStep);
        }
        return getBadTankImage(dir, imageStep);
    }

    public static BufferedImage getGoodTankImage(Dir dir, int imageStep) {
        return switch (dir) {
            case LEFT -> imageStep < maxImageStep / 2 ? ResourceMgr.goodTankL1 : ResourceMgr.goodTankL2;
            case UP -> imageStep < maxImageStep / 2 ? ResourceMgr.goodTankU1 : ResourceMgr.goodTankU2;
            case RIGHT -> imageStep < maxImageStep / 2 ? ResourceMgr.goodTankR1 : ResourceMgr.goodTankR2;
            case DOWN -> imageStep < maxImageStep / 2 ? ResourceMgr.goodTankD1 : ResourceMgr.goodTankD2;
        };
    }

    public static BufferedImage getBadTankImage(Dir dir, int imageStep) {
        return switch (dir) {
            case LEFT -> imageStep < maxImageStep / 2 ? ResourceMgr.badTankL1 : ResourceMgr.badTankL2;
            case UP -> imageStep < maxImageStep / 2 ? ResourceMgr.badTankU1 : ResourceMgr.badTankU2;
            case RIGHT -> imageStep < maxImageStep / 2 ? ResourceMgr.badTankR1 : ResourceMgr.badTankR2;
            case DOWN -> imageStep < maxImageStep / 2 ? ResourceMgr.badTankD1 : ResourceMgr.badTankD2;
        };
    }


    public void die() {
        this.live = false;
    }
}
