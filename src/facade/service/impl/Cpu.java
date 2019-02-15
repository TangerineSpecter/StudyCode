package facade.service.impl;

import facade.service.IHardware;

public class Cpu implements IHardware{

	@Override
	public void start() {
		System.out.println("启动CPU");
	}

}
