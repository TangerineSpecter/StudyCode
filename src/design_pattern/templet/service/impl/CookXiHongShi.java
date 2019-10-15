package design_pattern.templet.service.impl;

import design_pattern.templet.service.Cook;

public class CookXiHongShi extends Cook {

	@Override
	public void beforeCook() {
		super.beforeCook();
		System.out.println("切西红柿");
	}

	@Override
	public void doCook() {
		System.out.println("炒西红柿中...");
	}

}
