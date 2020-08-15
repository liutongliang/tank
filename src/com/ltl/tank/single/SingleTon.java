package com.ltl.tank.single;

import java.io.Serializable;

/**
 * liutongliang
 * 2020-08-2020/8/8
 */
public class SingleTon implements Serializable {

    private static class Instance {
        private static final SingleTon instance = new SingleTon();
    }

    private SingleTon() {
        if (Instance.instance != null) {
            throw new RuntimeException();
        }
    }

    public static SingleTon getInstance() {
        return Instance.instance;
    }

    private Object readResolve() {
        return Instance.instance;
    }

}
