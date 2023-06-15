package com.tangerine.specter.population.pojo;

import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 人口统计面板数据
 *
 * @author 丢失的橘子
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisView implements Serializable {

    private List<Integer> date = CollUtil.newArrayList();

    private List<Integer> totalPeople = CollUtil.newArrayList();

    private List<Integer> bornPeople = CollUtil.newArrayList();

    private List<Integer> diePeople = CollUtil.newArrayList();

    /**
     * 峰值人口数
     */
    private int maxPeople;
    /**
     * 最大年份
     */
    private int maxYear;

    public StatisView(int date, int totalPeople, int bornPeople, int diePeople) {
        this.date.add(date);
        this.totalPeople.add(totalPeople);
        this.bornPeople.add(bornPeople);
        this.diePeople.add(diePeople);
        this.maxPeople = totalPeople;
    }

    public void add(WorldInfo worldInfo) {
        this.date.add(worldInfo.getYear());
        this.totalPeople.add(worldInfo.getTotalPeople());
        this.bornPeople.add(worldInfo.getBornPeople());
        this.diePeople.add(CollUtil.size(worldInfo.getDiePeople()));
        this.maxPeople = Math.max(worldInfo.getTotalPeople(), maxPeople);
        this.maxYear = worldInfo.getYear() - 2020;
    }
}
