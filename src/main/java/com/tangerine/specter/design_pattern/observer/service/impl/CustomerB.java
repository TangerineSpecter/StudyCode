package com.tangerine.specter.design_pattern.observer.service.impl;

import com.tangerine.specter.design_pattern.observer.service.Customer;

public class CustomerB extends Customer{

	@Override
	public void update() {
		System.out.println("我是客户B，我收到报纸了！");
	}

}
