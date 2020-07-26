package com.ltl.tank;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        for (int i = 0; i < 10; i++) {
            tankFrame.tanks.add(new Tank(50 + i*80, 100, Dir.DOWN,true, false, Group.BAD, tankFrame));
        }
        while (true) {
            TimeUnit.MILLISECONDS.sleep(50);
            tankFrame.repaint();
        }
    }
}
