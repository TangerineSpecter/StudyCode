package design_pattern.proxy.service.impl;

import design_pattern.proxy.service.TicketSell;

/**
 * 代理售票车站
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月18日
 *
 */
public class ProxyStation implements TicketSell {

	private Station station;

	/**
	 * 库存
	 */
	private static Integer ticket = 1;

	public ProxyStation() {
		this.station = new Station();
	}

	@Override
	public void buyTicket() {
		if (ticket > 0) {
			station.buyTicket();
			ticket--;
		} else {
			throw new RuntimeException("库存不足");
		}
	}
}
