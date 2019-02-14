package factory;

import factory.service.ChineseGirlFactory;
import factory.service.GirlStore;

/**
 * 工厂模式
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月14日
 *
 */
public class UseDemo {

	/**
	 * 简单工厂模式\抽象工厂模式
	 * 
	 * 简单工厂模式：用于创建单一产品，将所有子类的创建集中在一个工厂中，如需要修改只修改一个工厂就行了。
	 * 
	 * 抽象工厂模式：用于创建一整个家族产品，而不是单一产品。通过选择不同的工厂来创建不同的东西。优势在于只需要替换工厂就可以替换整个产品线。
	 * 
	 * 优点：客户端与具体要创建的产品解耦，扩展性和灵活些高。
	 * 
	 * 缺点：增加要创建的对象时，需要增加的代码较多，会使系统变复杂。
	 * 
	 */
	public static void main(String[] args) {
		GirlStore girlStore = new GirlStore(new ChineseGirlFactory());
		girlStore.createGril("thin");
	}
}
