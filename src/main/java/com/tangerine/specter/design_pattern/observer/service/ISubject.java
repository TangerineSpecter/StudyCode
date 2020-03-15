package design_pattern.observer.service;

public interface ISubject {

	public void registerObserver(Customer customer);

	public void removeObserver(Customer customer);

	public void notifyAllObservers();
}
