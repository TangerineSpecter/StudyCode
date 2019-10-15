package design_pattern.facade.service.impl;

import design_pattern.facade.service.IHardware;

public class Disk implements IHardware {

	@Override
	public void start() {
		System.out.println("启动硬盘");
	}

}
