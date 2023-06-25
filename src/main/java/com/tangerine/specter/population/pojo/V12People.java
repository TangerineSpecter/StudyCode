package com.tangerine.specter.population.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class V12People extends V11People {

    /**
     * 性别：0：女；1：男
     */
    private int sex;

    @Override
    public V12People newPeople(int age) {
        V12People people = new V12People();
        people.setAge(age);
        return people;
    }
}
