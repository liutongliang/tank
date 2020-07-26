package com.ltl.tank;

import java.awt.Graphics;

/**
 * liutongliang
 * 2020-07-2020/7/26
 */
public class Explode {
    private int x;
    private int y;
    private int step = 0;
    private TankFrame frame;

    public Explode(int x, int y, TankFrame frame) {
        this.x = x;
        this.y = y;
        this.frame = frame;
    }

    public void paint(Graphics g) {
       g.drawImage(ResourceMgr.explodes[step++], x, y, null);
       if ( step >= ResourceMgr.explodes.length) {
            frame.explodes.remove(this);
       }
    }
}
