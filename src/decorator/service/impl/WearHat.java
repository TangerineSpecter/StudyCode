package decorator.service.impl;

import decorator.service.ClothesDecorator;
import decorator.service.Person;

public class WearHat extends ClothesDecorator {

	public WearHat(Person person) {
		super(person);
	}

	@Override
	public Double cost() {
		// 加上帽子价钱
		return person.cost() + 50;
	}

	@Override
	public void show() {
		person.show();
		System.out.println("戴上了帽子，累计消费：" + this.cost());
	}

}
