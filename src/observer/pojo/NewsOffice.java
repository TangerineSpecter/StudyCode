package observer.pojo;

import java.util.ArrayList;
import java.util.List;

import observer.service.Customer;

/**
 * ���磨���۲��ߣ�
 * 
 * @author TangerineSpecter
 * @Date 2019��2��12��
 */
public class NewsOffice {

	private List<Customer> customers = new ArrayList<>();

	public void addCustomer(Customer customer) {
		this.customers.add(customer);
	}

	// ģ�ⱨֽ
	public void newsPaperCome() {
		this.notifyAllObservers();
	}

	public void notifyAllObservers() {
		for (Customer customer : customers) {
			customer.update();
		}
	}
}
