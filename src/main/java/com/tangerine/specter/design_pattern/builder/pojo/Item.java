package com.tangerine.specter.design_pattern.builder.pojo;

/**
 * 食物基本属性
 */
public interface Item {
    /**
     * 食品名称
     */
    String name();

    /**
     * 包装方式
     */
    Packing packing();

    /**
     * 价格
     */
    float price();
}
