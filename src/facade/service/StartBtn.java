package facade.service;

import facade.service.impl.Cpu;
import facade.service.impl.Disk;
import facade.service.impl.Memory;

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
