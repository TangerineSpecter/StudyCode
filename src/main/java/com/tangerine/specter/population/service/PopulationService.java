package com.tangerine.specter.population.service;

import com.tangerine.specter.population.pojo.BasePeople;
import com.tangerine.specter.population.pojo.StatisView;
import com.tangerine.specter.population.pojo.V11People;
import com.tangerine.specter.population.pojo.WorldInfo;
import org.springframework.stereotype.Service;

/**
 * 人口增长
 */
@Service
public class PopulationService {

    public StatisView v10Version() {
        WorldInfo worldInfo = new WorldInfo(10000, new BasePeople());
        StatisView viewData = new StatisView(worldInfo.getYear(), worldInfo.getTotalPeople(), 0, 0);
        while (worldInfo.nextYear()) {
            viewData.add(worldInfo);
        }
        return viewData;
    }

    public StatisView v11Version() {
        WorldInfo worldInfo = new WorldInfo(10000, new V11People());
        StatisView viewData = new StatisView(worldInfo.getYear(), worldInfo.getTotalPeople(), 0, 0);
        while (worldInfo.nextYear()) {
            viewData.add(worldInfo);
        }
        return viewData;
    }

}
