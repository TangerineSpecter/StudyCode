package observer;

import observer.pojo.NewsOffice;
import observer.service.impl.CustomerA;
import observer.service.impl.CustomerB;

/**
 * 观察者模式
 * 
 * @author TangerineSpecter
 * @Date 2019年2月12日
 */
public class UseDemo {

	public static void main(String[] args) {
		NewsOffice office = new NewsOffice();
		CustomerA customerA = new CustomerA();
		CustomerB customerB = new CustomerB();
		//客户A订阅报纸
		office.addCustomer(customerA);
		office.addCustomer(customerB);
		//派送报纸
		office.newsPaperCome();
	}
}
