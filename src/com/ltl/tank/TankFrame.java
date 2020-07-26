package com.ltl.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * liutongliang
 * 2020-07-2020/7/26
 */
public class TankFrame extends Frame {
    public static final int GAME_WIDTH = 800;

    public static final int GAME_HEIGHT = 600;

    public Tank myTank = new Tank(600, 500, Dir.UP, false,true, Group.GOOD, this);

    public List<Bullet> bullets = new ArrayList<>();

    public List<Tank> tanks = new ArrayList<>();

    public List<Explode> explodes = new ArrayList<>();

    public TankFrame() throws HeadlessException {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("TANK WAR");
        setLocationRelativeTo(null);
        addKeyListener(new MyKeyListener());
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private Image offScreenImage;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics imageGraphics = offScreenImage.getGraphics();
        Color oldColor = imageGraphics.getColor();
        imageGraphics.setColor(Color.BLACK);
        imageGraphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        imageGraphics.setColor(oldColor);
        paint(imageGraphics);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量：" + bullets.size(), 10, 60);
        g.drawString("坦克数量：" + tanks.size(), 10, 80);
        g.setColor(oldColor);

//        if (myTank.isLive()) {
            myTank.paint(g);
//        }

        //消灭坦克
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j < tanks.size(); j++) {
                Tank tank = tanks.get(j);
                bullet.collideWith(tank);
            }
            bullet.collideWith(myTank);
        }
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
    }

    class MyKeyListener extends KeyAdapter {

        private boolean bL = false;
        private boolean bU = false;
        private boolean bR = false;
        private boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_SPACE:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            myTank.setMoving(bL || bU || bR || bD);
            if (bL) {
                myTank.setDir(Dir.LEFT);
            }
            if (bU) {
                myTank.setDir(Dir.UP);
            }
            if (bR) {
                myTank.setDir(Dir.RIGHT);
            }
            if (bD) {
                myTank.setDir(Dir.DOWN);
            }
        }
    }
}
