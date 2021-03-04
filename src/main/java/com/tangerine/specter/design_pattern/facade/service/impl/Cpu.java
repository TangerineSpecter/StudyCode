package com.tangerine.specter.design_pattern.facade.service.impl;

import com.tangerine.specter.design_pattern.facade.service.IHardware;

public class Cpu implements IHardware{

	@Override
	public void start() {
		System.out.println("启动CPU");
	}

}
