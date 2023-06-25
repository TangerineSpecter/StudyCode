package com.tangerine.specter.population.pojo;

public interface IPeople {

    /**
     * 新人类
     *
     * @param age 年龄
     * @return 对象
     */
    IPeople newPeople(int age);

    /**
     * 是否结婚
     *
     * @return true：已婚
     */
    boolean isMarriage();

    /**
     * 结婚
     *
     * @return 1：结婚；0：未婚
     */
    int marry();

    /**
     * 年龄增长
     */
    void addAge();

    /**
     * 是否死亡
     *
     * @return true：表示超过死亡年龄
     */
    boolean isDie();
}
