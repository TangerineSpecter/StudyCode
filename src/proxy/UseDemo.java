package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import proxy.service.TicketSell;
import proxy.service.impl.ProxyStation;
import proxy.service.impl.Station;
import proxy.service.impl.TicketHandler;

/**
 * 代理模式
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月18日
 *
 */
public class UseDemo {

	/**
	 * 代理模式
	 * 
	 * 静态代理：一个代理类对应一个被代理类，如果很多地方需要用到代理，那么就会产生很多代理类。
	 * 
	 * 动态代理：被代理类会根据指定的运行。
	 * 
	 * JDK的动态代理不足的地方是，被代理类要实现某个接口才行。 所以可以使用Cglib动态代理，这是基于类做代理的。
	 * 
	 * 优点：扩展性强，对象更智能
	 * 
	 * 缺点：代理类做了更多的操作，可能使请求速度变慢
	 */
	public static void main(String[] args) {
		// 静态代理演示
		TicketSell sell = new ProxyStation();
		sell.buyTicket();
		sell.buyTicket();

		// 动态代理演示
		Station station = new Station();
		InvocationHandler handler = new TicketHandler(station);
		Class<?> cls = station.getClass();
		ClassLoader loader = cls.getClassLoader();
		TicketSell ticketSell = (TicketSell) Proxy.newProxyInstance(loader, cls.getInterfaces(), handler);
		ticketSell.buyTicket();
		ticketSell.buyTicket();
	}
}
