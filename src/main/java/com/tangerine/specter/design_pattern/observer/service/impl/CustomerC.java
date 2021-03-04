package com.tangerine.specter.design_pattern.observer.service.impl;

import java.util.Observable;
import java.util.Observer;

public class CustomerC implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("我是客户C，我收到报纸了！");
    }

}
