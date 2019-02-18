package proxy.service.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月18日 JDK 为我们提供了一种动态代理的实现，通过实现 InvocationHandler 接口来实现动态代理。
 *
 */
public class TicketHandler implements InvocationHandler {

	/**
	 * 被代理类
	 */
	private Object target;

	/**
	 * 库存
	 */
	private static Integer ticket = 1;

	public TicketHandler(Object target) {
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (ticket > 0) {
			method.invoke(target, null);
			ticket--;
		} else {
			throw new RuntimeException("库存不足");
		}
		return null;
	}

}
