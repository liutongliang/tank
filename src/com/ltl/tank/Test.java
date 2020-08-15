package com.ltl.tank;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        int initTankCount = Integer.parseInt((String) PropertyMgr.getProperty("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            tankFrame.tanks.add(new Tank(50 + i*80, 100, Dir.DOWN, true, Group.BAD, tankFrame));
        }
        //new Thread(() -> new Audio("audio/war1.wav").loop()).start();
        while (true) {
            TimeUnit.MILLISECONDS.sleep(50);
            tankFrame.repaint();
        }
    }
}
