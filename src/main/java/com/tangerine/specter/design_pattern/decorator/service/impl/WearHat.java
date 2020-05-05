package com.tangerine.specter.design_pattern.decorator.service.impl;

import com.tangerine.specter.design_pattern.decorator.service.ClothesDecorator;
import com.tangerine.specter.design_pattern.decorator.service.Person;

/**
 * 戴帽子
 *
 * @author TangerineSpecter
 * @Date 2019年2月11日
 */
public class WearHat extends ClothesDecorator {

    public WearHat(Person person) {
        super(person);
    }

    @Override
    public Double cost() {
        // 加上帽子价钱
        return person.cost() + 50;
    }

    @Override
    public void show() {
        person.show();
        System.out.println("戴上了帽子，累计消费：" + this.cost());
    }

}
