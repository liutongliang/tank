package com.ltl.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * liutongliang
 * 2020-08-2020/8/2
 */
public class PropertyMgr {
    static Properties prop = new Properties();

    static {
        try {
            prop.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getProperty(String name) {
        if (prop == null) {
            return null;
        }
        return prop.getProperty(name);
    }
}
