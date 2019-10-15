package design_pattern.singleton;

/**
 * 单例模式--双重检验加锁
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月14日
 *
 */
public class SingleObjectLocking {

	private volatile static SingleObjectLocking instance;

	private SingleObjectLocking() {
	};

	/**
	 * 单例模式
	 * 
	 * 需要考虑线程安全问题，当多个线程同时调用会产生多个instance实例
	 * 
	 * synchronized加锁可以解决线程安全问题，但是会带来性能损耗。
	 * 
	 * 通过double-checked locking（双重检查加锁）
	 * 
	 * 引入volatile关键字
	 * 
	 * volatile 关键字简单来说就是可以保证 instance 变量在被其中一个线程 New 出来时，其他线程可以立即看到结果并正确地处理它。
	 */
	public static synchronized SingleObjectLocking getInstance() {
		if (instance == null) {
			instance = new SingleObjectLocking();
		}
		return instance;
	}
}
