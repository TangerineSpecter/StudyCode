package factory;

import factory.service.GirlFactory;
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
	 * 简单工厂模式
	 * 
	 */
	public static void main(String[] args) {
		GirlStore girlStore = new GirlStore(new GirlFactory());
		girlStore.createGril("thin");
	}
}
