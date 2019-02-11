package decorator.service.impl;

import decorator.service.ClothesDecorator;
import decorator.service.Person;

public class WearHat extends ClothesDecorator {

	public WearHat(Person person) {
		super(person);
	}

	@Override
	public Double cost() {
		// ����ñ�Ӽ�Ǯ
		return person.cost() + 50;
	}

	@Override
	public void show() {
		person.show();
		System.out.println("������ñ�ӣ��ۼ����ѣ�" + this.cost());
	}

}
