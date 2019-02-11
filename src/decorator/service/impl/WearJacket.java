package decorator.service.impl;

import decorator.service.ClothesDecorator;
import decorator.service.Person;

/**
 * ���п�
 * 
 * @author TangerineSpecter
 * @Date 2019��2��11��
 */
public class WearJacket extends ClothesDecorator {

	public WearJacket(Person person) {
		super(person);
	}

	@Override
	public Double cost() {
		// ���ϼп˼�Ǯ
		return person.cost() + 100;
	}

	@Override
	public void show() {
		person.show();
		System.out.println("�����˼пˣ��ۼ����ѣ�" + this.cost());
	}

}
