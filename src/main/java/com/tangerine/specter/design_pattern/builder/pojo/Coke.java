package com.tangerine.specter.design_pattern.builder.pojo;

public class Coke extends ColdDrink {

    @Override
    public String name() {
        return "可乐";
    }

    @Override
    public float price() {
        return 2.5f;
    }
}
