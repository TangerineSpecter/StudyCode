package com.tangerine.specter.population.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * v1.0 人口模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePeople implements IPeople {

    /**
     * 平均78岁死亡
     */
    private int dieAge = 78;
    /**
     * 年龄
     */
    private int age = 20;

    /**
     * 是否结婚
     */
    private boolean marriage = false;

    @Override
    public BasePeople newPeople(int age) {
        return new BasePeople(78, age, false);
    }

    @Override
    public int marry() {
        if (this.marriage) {
            return 0;
        }
        this.marriage = true;
        return 1;
    }

    /**
     * 年纪增长
     */
    public void addAge() {
        this.age++;

    }

    @Override
    public boolean isDie() {
        return age > dieAge;
    }

}
