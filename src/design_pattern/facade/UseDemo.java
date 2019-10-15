package design_pattern.facade;

import design_pattern.facade.service.StartBtn;

/**
 * 外观模式
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月15日
 *
 */
public class UseDemo {

	/**
	 * 外观模式 包装多个复杂子系统，提供一个简单的接口。 重新包装系统，隐藏不想暴露的接口。
	 * 
	 * 优点：将复杂的系统简单化，减少客户端和接口之间的耦合，提高安全性。
	 * 
	 * 缺点：可能会产生大量外观类，一定程度上增加了系统的复杂度。
	 * 
	 */
	public static void main(String[] args) {
		new StartBtn().start();
	}
}
