package com.tangerine.specter.design_pattern.templet.service;

public abstract class Cook {
	/**
	 * 钩子开关
	 */
	public boolean needBeforeCook = true;
	public boolean needAfterCook = true;

	public void open() {
		System.out.println("打开抽油烟机...");
	}

	public void fire() {
		System.out.println("生火");
	}

	/**
	 * 需要子类实现
	 */
	public abstract void doCook();

	public void outfire() {
		System.out.println("灭火");
	}

	public void close() {
		System.out.println("关闭抽油烟机...");
	}

	public void beforeCook() {
	}

	public void afterCook() {

	}

	/**
	 * 使用final防止子类重写
	 */
	public final void cook() {
		if (this.needBeforeCook) {
			beforeCook();
		}
		this.open();
		this.fire();
		this.doCook();
		this.outfire();
		this.close();
		if (this.needAfterCook) {
			afterCook();
		}
	}
}
