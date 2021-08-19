package com.tangerine.specter.design_pattern.factory.service;

public class GirlStore {

    AbstractGirlFactory factory;

    /**
     * 动态选择工厂
     *
     * @param factory
     */
    public GirlStore(AbstractGirlFactory factory) {
        this.factory = factory;
    }

    public Girl createGirl(String whatYouLike) {
        return factory.createGirl(whatYouLike);
    }
}
