package com.tangerine.specter.design_pattern.factory.service;

import com.tangerine.specter.design_pattern.factory.pojo.FatGirl;
import com.tangerine.specter.design_pattern.factory.pojo.ThinGirl;

public class AmericanGirlFactory extends AbstractGirlFactory {

    private String city = "美国";

    @Override
    public Girl createGirl(String whatYouLike) {
        Girl girl = null;
        if (whatYouLike.equals("fat")) {
            girl = new FatGirl(city);
        } else if (whatYouLike.equals("thin")) {
            girl = new ThinGirl(city);
        }
        return girl;
    }

}
