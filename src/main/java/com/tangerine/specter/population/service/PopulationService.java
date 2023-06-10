package com.tangerine.specter.population.service;

import cn.hutool.core.util.StrUtil;
import com.tangerine.specter.population.pojo.WorldInfo;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

/**
 * 人口增长
 */
@Service
public class PopulationService {

    public void v10Version() {
        WorldInfo worldInfo = new WorldInfo();
        System.out.println(StrUtil.format("当前年份：{}，总人口：{}", worldInfo.getYear(), worldInfo.getTotalPeople()));
        IntStream.range(0, worldInfo.getAllTime()).forEach(year -> {
            worldInfo.nextYear();
            System.out.println(StrUtil.format("当前年份：{}，总人口：{}", worldInfo.getYear(), worldInfo.getTotalPeople()));
        });
//        System.out.println(JSON.toJSONString(worldInfo.getAllPeople().size()));
    }

    public static void main(String[] args) {
        new PopulationService().v10Version();
    }
}
