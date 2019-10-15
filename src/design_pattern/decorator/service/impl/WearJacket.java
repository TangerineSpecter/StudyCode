package design_pattern.decorator.service.impl;

import design_pattern.decorator.service.ClothesDecorator;
import design_pattern.decorator.service.Person;

/**
 * 穿夹克
 * 
 * @author TangerineSpecter
 * @Date 2019年2月11日
 */
public class WearJacket extends ClothesDecorator {

	public WearJacket(Person person) {
		super(person);
	}

	@Override
	public Double cost() {
		// 加上夹克价钱
		return person.cost() + 100;
	}

	@Override
	public void show() {
		person.show();
		System.out.println("穿上了夹克，累计消费：" + this.cost());
	}

}
