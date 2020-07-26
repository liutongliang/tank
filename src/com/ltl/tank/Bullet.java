package com.ltl.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * liutongliang
 * 2020-07-2020/7/26
 */
public class Bullet {

    private int x;
    private int y;
    private Dir dir;
    private boolean live = true;
    private Group group;
    private TankFrame tankFrame;
    private static final int SPEED = 10;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        move();
        BufferedImage bulletImage = switch (dir) {
            case LEFT -> ResourceMgr.bulletL;
            case UP -> ResourceMgr.bulletU;
            case RIGHT -> ResourceMgr.bulletR;
            case DOWN -> ResourceMgr.bulletD;
        };
        if (!live) {
            this.tankFrame.bullets.remove(this);
        }
        g.drawImage(bulletImage, x, y, null);
    }

    private void move() {
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
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            live = false;
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

    public void collideWith(Tank tank) {
        if (tank.getGroup() == this.group) {
            return;
        }
        if (!tank.isLive() || !this.isLive()) {
            return;
        }
        BufferedImage bulletImage = getBulletImage(dir);
        Rectangle bulletRect = new Rectangle(this.x, this.y, bulletImage.getWidth(), bulletImage.getHeight());
        BufferedImage tankImage = Tank.getTankImage(tank.getDir());
        Rectangle tankRect = new Rectangle(tank.getX(), tank.getY(), tankImage.getWidth(), tankImage.getHeight());
        if (bulletRect.intersects(tankRect)) {
            this.die();
            tank.die();
            int eX = this.x + (tankImage.getWidth() - ResourceMgr.explodes[0].getWidth())/2;
            int eY = this.y + (tankImage.getHeight() - ResourceMgr.explodes[0].getHeight())/2;
            tankFrame.explodes.add(new Explode(eX, eY, tankFrame));
            //new Audio("audio/explode.wav").play();
        }
    }

    private void die() {
        this.live = false;
    }

    public static BufferedImage getBulletImage(Dir dir) {
        return switch (dir) {
            case LEFT -> ResourceMgr.bulletL;
            case UP -> ResourceMgr.bulletU;
            case RIGHT -> ResourceMgr.bulletR;
            case DOWN -> ResourceMgr.bulletD;
        };
    }
}
