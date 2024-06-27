package com.tangerine.specter.design_pattern.builder.service;

import com.tangerine.specter.design_pattern.builder.pojo.Item;

import java.util.ArrayList;
import java.util.List;

public class Meal {

    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public float getCost() {
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    public void showItems() {
        for (Item item : items) {
            System.out.print("食物 : " + item.name());
            System.out.print(", 包装 : " + item.packing().pack());
            System.out.println(", 价格 : " + item.price());
        }
    }
}
