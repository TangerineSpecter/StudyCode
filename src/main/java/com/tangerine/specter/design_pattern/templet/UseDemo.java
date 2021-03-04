package com.tangerine.specter.design_pattern.templet;

import com.tangerine.specter.design_pattern.templet.service.Cook;
import com.tangerine.specter.design_pattern.templet.service.impl.CookXiHongShi;

/**
 * 模版模式
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月14日
 *
 */
public class UseDemo {

	/**
	 * 模版模式
	 * 
	 * 将子类公共部分抽象到父类，可变的部分由子类实现。
	 * 
	 * 优点：封装不变的公共代码，便于维护。可变部分由子类实现，扩展性强。
	 * 
	 * 缺点：每增加一个实现就要增加一个子类，导致数量便多，后期不利于维护，增加系统复杂性。
	 * 
	 */
	public static void main(String[] args) {
		Cook cook = new CookXiHongShi();
		cook.needBeforeCook = false;
		cook.cook();
	}
}
