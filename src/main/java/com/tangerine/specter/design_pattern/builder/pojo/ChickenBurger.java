package com.tangerine.specter.design_pattern.builder.pojo;

public class ChickenBurger implements Item {

    @Override
    public String name() {
        return "鸡肉汉堡";
    }

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public float price() {
        return 20.5f;
    }
}
