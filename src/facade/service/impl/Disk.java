package facade.service.impl;

import facade.service.IHardware;

public class Disk implements IHardware {

	@Override
	public void start() {
		System.out.println("启动硬盘");
	}

}
