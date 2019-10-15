package design_pattern.singleton;

/**
 * 单例模式--静态内部类方式
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月14日
 *
 */
public class SingleObjectStatic {

	private static class Singleton {
		private static SingleObjectStatic single = new SingleObjectStatic();
	}

	/**
	 * 静态内部类Singleton在初始化的时候不会被加载，只有get的时候才会实例化。也算懒汉式的一种。
	 * 
	 * 而静态内部类只会被实例化一次，不管多少的线程并发，大家拿到的都是同一个实例，线程是安全的。
	 * 
	 * 没有使用synchronized进行加锁，所以并发性高，线程安全，性能很好。
	 */
	public static SingleObjectStatic getInstance() {
		return Singleton.single;
	}
}
