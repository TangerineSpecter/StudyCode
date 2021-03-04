package com.tangerine.specter.design_pattern.factory.pojo;

import com.tangerine.specter.design_pattern.factory.service.Girl;

public class ThinGirl extends Girl {

    public ThinGirl() {
        System.out.println("你好，我是痩妹子~");
    }

    public ThinGirl(String city) {
        System.out.println(String.format("你好，我是来自%s的痩妹子~", city));
    }

    @Override
    public void show() {

    }

}
