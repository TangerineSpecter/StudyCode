package com.tangerine.specter.design_pattern.builder.pojo;

public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    public abstract float price();
}
