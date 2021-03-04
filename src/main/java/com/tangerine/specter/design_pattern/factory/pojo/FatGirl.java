package com.tangerine.specter.design_pattern.factory.pojo;

import com.tangerine.specter.design_pattern.factory.service.Girl;

public class FatGirl extends Girl {

	public FatGirl() {
		System.out.println("你好，我是胖妹子~");
	}

	public FatGirl(String city) {
		System.out.println(String.format("你好，我是来自%s的胖妹子~", city));
	}

	@Override
	public void show() {
	}

}
