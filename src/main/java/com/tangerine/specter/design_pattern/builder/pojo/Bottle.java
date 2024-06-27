package com.tangerine.specter.design_pattern.builder.pojo;

public class Bottle implements Packing {
    @Override
    public String pack() {
        return "瓶装";
    }
}
