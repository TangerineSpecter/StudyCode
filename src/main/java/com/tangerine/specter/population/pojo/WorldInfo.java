package com.tangerine.specter.population.pojo;

import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 世界人口
 *
 * @author 丢失的橘子
 */
@Data
@AllArgsConstructor
public class WorldInfo implements Serializable {

    /**
     * 100个世纪，1万年
     */
    private int allTime = 100 * 100;
    /**
     * 起始年份：2020年
     */
    private int year = 2020;
    /**
     * 总人口
     */
    private List<BasePeople> allPeople = CollUtil.newArrayList();

    /**
     * 死亡人口
     */
    private List<BasePeople> diePeople = CollUtil.newArrayList();

    /**
     * 出生人口
     */
    private int bornPeople;

    /**
     * 初始1万人
     */
    public WorldInfo() {
        int peopleNumber = 10000;
        IntStream.range(0, peopleNumber).forEach(i -> allPeople.add(new BasePeople()));
    }

    /**
     * 获取当前总人口
     *
     * @return 总人口
     */
    public int getTotalPeople() {
        return CollUtil.size(this.allPeople);
    }

    /**
     * 获取当前未婚人口
     *
     * @return 未婚人口
     */
    public int getNotMarriagePeople() {
        return CollUtil.size(this.allPeople.stream().filter(people -> !people.isMarriage()).collect(Collectors.toList()));
    }

    /**
     * 新一年
     * 年份+1，清理死亡人口
     */
    public void newYear() {
        this.year++;
        diePeople.clear();
    }

    /**
     * 执行下一年
     */
    public void nextYear() {
        this.newYear();
        this.bornPeople = this.getNotMarriagePeople() / 2;
        this.allPeople.forEach(people -> {
            people.setMarriage(true);
            boolean isDie = people.addAge();
            if (isDie) {
                diePeople.add(people);
            }
        });
        //出生
        this.born();
        //死亡
        if (CollUtil.isNotEmpty(diePeople)) {
            allPeople.removeAll(diePeople);
        }
    }

    /**
     * 出生
     */
    public void born() {
        IntStream.range(0, this.bornPeople).forEach(i -> allPeople.add(new BasePeople()));
    }

    /**
     * 全人类灭绝
     *
     * @return true：灭绝
     */
    public boolean isAllDie() {
        return allPeople.isEmpty();
    }
}
