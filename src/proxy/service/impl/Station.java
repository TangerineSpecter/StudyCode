package proxy.service.impl;

import proxy.service.TicketSell;

/**
 * 售票车站
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月18日
 *
 */
public class Station implements TicketSell {

	@Override
	public void buyTicket() {
		System.out.println("有人买了一张票");
	}

}
