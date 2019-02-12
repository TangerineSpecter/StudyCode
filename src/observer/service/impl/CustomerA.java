package observer.service.impl;

import observer.service.Customer;

public class CustomerA extends Customer{

	@Override
	public void update() {
		System.out.println("我是客户A，我收到报纸了！");
	}

}
