package observer.pojo;

import java.util.ArrayList;
import java.util.List;

import observer.service.Customer;

/**
 * 报社（被观察者）
 * 
 * @author TangerineSpecter
 * @Date 2019年2月12日
 */
public class NewsOffice {

	private List<Customer> customers = new ArrayList<>();

	public void addCustomer(Customer customer) {
		this.customers.add(customer);
	}

	// 模拟报纸
	public void newsPaperCome() {
		this.notifyAllObservers();
	}

	public void notifyAllObservers() {
		for (Customer customer : customers) {
			customer.update();
		}
	}
}
