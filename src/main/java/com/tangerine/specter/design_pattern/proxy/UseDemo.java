package com.tangerine.specter.design_pattern.proxy;

import com.tangerine.specter.design_pattern.proxy.service.TicketSell;
import com.tangerine.specter.design_pattern.proxy.service.impl.ProxyStation;
import com.tangerine.specter.design_pattern.proxy.service.impl.Station;
import com.tangerine.specter.design_pattern.proxy.service.impl.TicketHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 代理模式
 *
 * @author TangerineSpecter
 * @Datetime 2019年2月18日
 */
public class UseDemo {

    /**
     * 代理模式
     * <p>
     * 静态代理：一个代理类对应一个被代理类，如果很多地方需要用到代理，那么就会产生很多代理类。
     * <p>
     * 动态代理：被代理类会根据指定的运行。
     * <p>
     * JDK的动态代理不足的地方是，被代理类要实现某个接口才行。 所以可以使用Cglib动态代理，这是基于类做代理的。
     * <p>
     * 优点：扩展性强，对象更智能
     * <p>
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
