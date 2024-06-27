package com.tangerine.specter.design_pattern.builder;

import com.tangerine.specter.design_pattern.builder.pojo.ChickenBurger;
import com.tangerine.specter.design_pattern.builder.pojo.Coke;
import com.tangerine.specter.design_pattern.builder.service.Meal;

public class UseDemo {

    public static void main(String[] args) {
        Meal meal = new Meal();
        //点一杯可乐
        meal.addItem(new Coke());
        //点一个汉堡
        meal.addItem(new ChickenBurger());
        //展示点单
        meal.showItems();
        //展示价格
        System.out.println("总价：" + meal.getCost());
    }
}
