package com.tangerine.specter.population.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * v1.0 人口模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePeople implements Serializable {

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

    /**
     * 年纪增长
     *
     * @return 未超过死亡年龄则返回false，ture表示死亡
     */
    public boolean addAge() {
        this.age++;
        return age > dieAge;
    }
}
