package design_pattern.facade.service;

import design_pattern.facade.service.impl.Cpu;
import design_pattern.facade.service.impl.Disk;
import design_pattern.facade.service.impl.Memory;

/**
 * 启动按钮
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月15日
 *
 */
public class StartBtn {

	public void start() {
		new Cpu().start();
		new Disk().start();
		new Memory().start();
	}
}
