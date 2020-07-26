package com.ltl.tank;

import java.awt.Graphics;
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
    private boolean main;
    private Random random = new Random();
    private static final int SPEED = 5;

    public Tank(int x, int y, Dir dir, boolean moving, boolean main, Group group, TankFrame frame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.main = main;
        this.group = group;
        this.frame = frame;
    }

    public void paint(Graphics g) {
        if (moving) {
            move();
        }
        if (!live) {
            this.frame.tanks.remove(this);
        }
        if (!main) {
           randomDir();
        }
        g.drawImage(getTankImage(dir), x, y, null);
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

    private void move() {
        BufferedImage tankImage = getTankImage(dir);
        switch (dir) {
            case LEFT:
                if (x > SPEED) {
                    x -= SPEED;
                } else {
                    x = 0;
                }
                break;
            case UP:
                if (y > SPEED) {
                    y -= SPEED;
                } else {
                    y = 0;
                }
                break;
            case RIGHT:
                if (x + tankImage.getWidth() < TankFrame.GAME_WIDTH - SPEED) {
                    x += SPEED;
                } else {
                    x = TankFrame.GAME_WIDTH - tankImage.getWidth();
                }
                break;
            case DOWN:
                if (y + tankImage.getHeight() < TankFrame.GAME_HEIGHT -SPEED) {
                    y += SPEED;
                } else {
                    y = TankFrame.GAME_HEIGHT - tankImage.getHeight();
                }
                break;
            default:
                break;
        }
        if (!main && random.nextInt(10) > 8) {
            this.fire();
        }
    }

    public void fire() {

        BufferedImage tankImage = getTankImage(dir);
        BufferedImage bulletImage = Bullet.getBulletImage(dir);
        int bX = (tankImage.getWidth() - bulletImage.getWidth())/2 + x;
        int bY = (tankImage.getHeight() - bulletImage.getHeight())/2 + y;
        frame.bullets.add(new Bullet(bX, bY, dir, this.group, frame));
    }

    public static BufferedImage getTankImage(Dir dir) {
        return switch (dir) {
            case LEFT -> ResourceMgr.tankL;
            case UP -> ResourceMgr.tankU;
            case RIGHT -> ResourceMgr.tankR;
            case DOWN -> ResourceMgr.tankD;
        };
    }


    public void die() {
        this.live = false;
    }
}
