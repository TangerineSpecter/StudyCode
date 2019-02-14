package factory.service;

public class GirlStore {

	GirlFactory factory;

	/**
	 * 动态选择工厂
	 * 
	 * @param factory
	 */
	public GirlStore(GirlFactory factory) {
		this.factory = factory;
	}

	public Girl createGril(String whatYouLike) {
		return factory.createGirl(whatYouLike);
	}
}
