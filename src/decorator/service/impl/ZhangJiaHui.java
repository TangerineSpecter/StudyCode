package decorator.service.impl;

import decorator.service.Person;

public class ZhangJiaHui implements Person {

	@Override
	public Double cost() {
		// Ĭ������Ϊ0
		return 0.0;
	}

	@Override
	public void show() {
		System.out.println("��Һã����������ԣ�");
	}

}
