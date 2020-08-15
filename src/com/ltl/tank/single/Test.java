package com.ltl.tank.single;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

/**
 * liutongliang
 * 2020-08-2020/8/8
 */
public class Test {
    public static void main(String[] args)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException,
            IOException, ClassNotFoundException {
//        Constructor<SingleTon> constructor = SingleTon.class.getDeclaredConstructor();
//        constructor.setAccessible(true);
//        SingleTon singleTon = constructor.newInstance();
//        System.out.println(singleTon == SingleTon.getInstance());

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:/single.txt"));
        oos.writeObject(SingleTon.getInstance());

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("E:/single.txt"));
        Object o = ois.readObject();
        System.out.println(o == SingleTon.getInstance());
        System.out.println(-5/2);
        System.out.println(-5%2);
    }
}
