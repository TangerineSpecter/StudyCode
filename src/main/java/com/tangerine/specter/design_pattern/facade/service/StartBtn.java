package com.tangerine.specter.design_pattern.facade.service;

import com.tangerine.specter.design_pattern.facade.service.impl.Cpu;
import com.tangerine.specter.design_pattern.facade.service.impl.Disk;
import com.tangerine.specter.design_pattern.facade.service.impl.Memory;

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
