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
    private List<IPeople> allPeople = CollUtil.newArrayList();

    /**
     * 出生人口
     */
    private List<IPeople> bornPeople = CollUtil.newArrayList();

    /**
     * 死亡人口
     */
    private List<IPeople> diePeople = CollUtil.newArrayList();


    /**
     * 结婚人数
     */
    private int marryPeople;

    /**
     * 初始化
     *
     * @param initPeople 初始人口
     */
    public WorldInfo(int initPeople, IPeople people) {
        IntStream.range(0, initPeople).forEach(i -> allPeople.add(people.newPeople(20)));
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
        //重置本年结婚人数
        this.marryPeople = 0;
        //清理本年出生人口记录
        bornPeople.clear();
        //清理死亡人口
        diePeople.clear();
    }

    /**
     * 一年结束
     */
    private void endYear() {
        //总人口 + 出生人口 - 死亡人口
        allPeople.addAll(this.bornPeople);
        allPeople.removeAll(this.diePeople);
    }

    /**
     * 执行下一年
     *
     * @return true：还有人口，false：无人口
     */
    public boolean nextYear() {
        //无人口 或者 达到最大年份
        if (this.isAllDie() || this.year >= this.allTime) {
            return false;
        }
        //新一年
        this.newYear();
        //过程
        this.process();
        //结束
        this.endYear();
        return true;
    }

    /**
     * 过程
     */
    private void process() {
        for (IPeople people : this.allPeople) {
            //成长
            people.addAge();
            //婚姻
            this.marryPeople += people.marry();
            //出生 和 死亡
            this.bornAndDie(people);
        }
    }

    /**
     * 出生
     */
    public void bornAndDie(IPeople people) {
        //成对 且 超过0，则人口 + 1
        if (this.marryPeople > 0 && this.marryPeople % 2 == 0) {
            this.bornPeople.add(people.newPeople(0));
        }
        if (people.isDie()) {
            this.diePeople.add(people);
        }
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
