package design_pattern.singleton;

/**
 * 单例模式--懒汉式
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月14日
 *
 */
public class SingleObjectLazy {

	private static SingleObjectLazy instance = null;

	public SingleObjectLazy() {
	};

	/**
	 * 懒汉式
	 * 
	 * 在类加载时候效率很高，比较懒，一开始不进行实例初始化，需要的时候才进行初始化，获取对象较慢
	 * 
	 * 优点：对象在内存中只有一个实例，并且无需频繁的创建对象和销毁对象，避免内存浪费。
	 * 
	 * 缺点：由于懒汉式是非线程安全的，所以加入synchronized会影响性能效率。
	 * 
	 * 
	 */
	public static SingleObjectLazy getInstance() {
		// 加入判断来绕过锁，线程就不需要等待
		if (instance == null) {
			// 通过反射获取对象
			synchronized (SingleObjectLazy.class) {
				if (instance == null) {
					instance = new SingleObjectLazy();
				}
			}
		}
		return instance;
	}
}
