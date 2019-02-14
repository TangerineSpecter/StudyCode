package observer.service;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSubject implements ISubject {

	private List<Customer> customers = new ArrayList<>();

	public void notifyAllObservers() {
		for (Customer customer : customers) {
			customer.update();
		}
	}

	@Override
	public void registerObserver(Customer customer) {
		this.customers.add(customer);
	}

	@Override
	public void removeObserver(Customer customer) {
		this.customers.remove(customer);
	}
}
