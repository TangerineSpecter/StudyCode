package com.tangerine.specter.java14;

/**
 * instance of增强
 */
public class InstanceOfDemo {

    public static void main(String[] args) {
        Object a = "hello world";
        //JDK14之前，判断之后进行强转
        if (a instanceof String) {
            String b = (String) a;
            System.out.println(b);
        }
        //JDK14，直接赋值
        if (a instanceof String b) {
            System.out.println(b);
        }
    }
}
