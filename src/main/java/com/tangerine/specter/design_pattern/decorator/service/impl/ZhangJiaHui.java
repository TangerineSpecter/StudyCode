package design_pattern.decorator.service.impl;

import design_pattern.decorator.service.Person;

public class ZhangJiaHui implements Person {

	@Override
	public Double cost() {
		// 默认消费为0
		return 0.0;
	}

	@Override
	public void show() {
		System.out.println("大家好，我是渣渣辉！");
	}

}
