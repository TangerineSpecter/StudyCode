package com.tangerine.specter.population.service;

import cn.hutool.core.util.StrUtil;
import com.tangerine.specter.population.pojo.StatisView;
import com.tangerine.specter.population.pojo.WorldInfo;
import org.springframework.stereotype.Service;

/**
 * 人口增长
 */
@Service
public class PopulationService {

    public StatisView v10Version() {
        WorldInfo worldInfo = new WorldInfo();
        StatisView viewData = new StatisView(worldInfo.getYear(), worldInfo.getTotalPeople(), 0, 0);
        for (int index = 0; index < worldInfo.getAllTime(); index++) {
            worldInfo.nextYear();
            viewData.add(worldInfo);
            if (worldInfo.isAllDie()) {
                break;
            }
        }
        return viewData;
    }

    public static void main(String[] args) {
        new PopulationService().v10Version();
    }
}
