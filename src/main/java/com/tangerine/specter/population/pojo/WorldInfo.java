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
     * 初始1万人
     */
    public WorldInfo() {
        IntStream.range(0, 10000).forEach(i -> allPeople.add(new BasePeople()));
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
     * 增加一年
     */
    public void addYear() {
        this.year++;
    }

    /**
     * 执行下一年
     */
    public void nextYear() {
        this.addYear();
        int bornPeople = this.getNotMarriagePeople() / 2;
        this.allPeople.forEach(people -> {
            people.setMarriage(true);
            boolean isDie = people.addAge();
            if (isDie) {
                diePeople.add(people);
            }
        });
        //出生
        this.born(bornPeople);
        //死亡
        if (CollUtil.isNotEmpty(diePeople)) {
            allPeople.removeAll(diePeople);
            diePeople.clear();
        }
    }

    /**
     * 出生
     *
     * @param bornPeople 出生人数
     */
    public void born(int bornPeople) {
        IntStream.range(0, bornPeople).forEach(i -> allPeople.add(new BasePeople()));
    }
}
