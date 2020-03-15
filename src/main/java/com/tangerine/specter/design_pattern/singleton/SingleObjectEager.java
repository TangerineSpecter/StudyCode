package design_pattern.singleton;

/**
 * 单例模式--饿汉式
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月14日
 *
 */
public class SingleObjectEager {

	private static SingleObjectEager instance = new SingleObjectEager();

	public SingleObjectEager() {
	};

	/**
	 * 饿汉式
	 * 
	 * 在类加载时候就创建好对象实例，保证了实例的唯一性。
	 * 
	 * 优点：对象在内存中只有一个实例，并且无需频繁的创建对象和销毁对象，大大减少了性能损耗。由于一开始就初始化实例，所以获取对象速度很快。
	 * 
	 * 缺点：一开始就进行初始化实例，不管使不使用都会使用一部分内存。
	 * 
	 * 
	 */
	public static SingleObjectEager getInstance() {
		return instance;
	}
}
