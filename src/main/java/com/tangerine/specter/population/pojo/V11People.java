package com.tangerine.specter.population.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class V11People extends BasePeople {

    /**
     * 结婚年龄
     */
    private int marryAge = 24;

    @Override
    public int marry() {
        //未婚，并且达到适婚年龄
        if (!super.isMarriage() && super.getAge() >= this.marryAge) {
            super.setMarriage(true);
            return 1;
        }
        return 0;
    }

    @Override
    public V11People newPeople(int age) {
        V11People people = new V11People();
        people.setAge(age);
        return people;
    }
}
