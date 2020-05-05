package com.tangerine.specter.design_pattern.decorator.service;

public abstract class ClothesDecorator implements Person {
    // 装饰器中要使用的装饰器对象，构造方法传入
    protected Person person;

    public ClothesDecorator(Person person) {
        this.person = person;
    }
}
