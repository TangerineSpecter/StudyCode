package decorator.service;

public abstract class ClothesDecorator implements Person {
	// װ������Ҫʹ�õ�װ�������󣬹��췽������
	protected Person person;

	public ClothesDecorator(Person person) {
		this.person = person;
	}
}
