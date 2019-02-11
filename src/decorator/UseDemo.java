package decorator;

import decorator.service.Person;
import decorator.service.impl.WearHat;
import decorator.service.impl.WearJacket;
import decorator.service.impl.ZhangJiaHui;

/**
 * 装饰者模式
 * 
 * @author TangerineSpecter
 * @Date 2019年2月11日
 */
public class UseDemo {

	/**
	 * 装饰器和被装饰器要实现同一个接口，以实现消费累加效果
	 * 
	 * 作用：动态给对象增加一些功能，而不需要修改对象本身
	 * 
	 * 优点：扩展灵活；每一个装饰器独立，修改时不会相互影响。
	 * 
	 * 缺点：多层装饰比较复杂；对新手不友好；
	 */
	public static void main(String[] args) {
		//创建一个渣渣辉
		Person zhangjiahui = new ZhangJiaHui();
		//穿上夹克
		zhangjiahui = new WearJacket(zhangjiahui);
		//戴上帽子
		zhangjiahui = new WearHat(zhangjiahui);
		
		zhangjiahui.show();
	}
}
