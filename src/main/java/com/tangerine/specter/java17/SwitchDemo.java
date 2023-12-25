package com.tangerine.specter.java17;

import cn.hutool.core.util.RandomUtil;

/**
 * switch增强
 */
public class SwitchDemo {

    public static void main(String[] args) {
        //相当于以前的if(count instanceof String a)，此处在JDK14开始支持instanceof增强转换
        Object count = RandomUtil.randomInt(10);
        switch (count) {
            case String a -> System.out.println(a);
            case Integer a -> System.out.println("结果是:" + a);
            default -> throw new RuntimeException("结果错误");
        }
    }
}
