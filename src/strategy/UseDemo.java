package strategy;

import strategy.pojo.Calculator;
import strategy.service.impl.OperationAdd;

/**
 * 策略模式
 * 
 * @author TangerineSpecter
 * @Date 2019年2月11日
 */
public class UseDemo {
	
	/**
	 * 策略模式很好的符合了代码的“开闭原则”;
	 * 即对修改关闭，对扩展开放;
	 * 如例子，当计算规则无法满足之后的计算的时候，
	 * 可以增加新的功能，就可以自己定义一个类实现Operation接口
	 * 
	 * 优点：符合开闭原则，扩展性好。
	 * 
	 * 缺点：随着策略增加，相应的类也增加，没有文档后期很难理解使用规则。
	 * 
	 * Shiro就使用了策略模式
	 * 
	 * Shiro中就提供了三种策略：
	 * AtLeastOneSuccessfulStrategy ：只要有一个验证通过，那么最终验证结果就是通过。
	 * FirstSuccessfulStrategy ：只有第一个成功地验证的 Realm 返回的信息将被使用，所有进一步的 Realm 将被忽略，如果没有一个验证成功，则整体尝试失败。
	 * AllSucessfulStrategy ：所有验证器都必须验证成功。
	 */
	public static void main(String[] args) {
		// 创建一个计算器
		Calculator calculator = new Calculator();
		// 设置计算方法
		calculator.setOperation(new OperationAdd());
		// 执行运算
		int result = calculator.doOperation(1, 2);
		System.out.println(result);
	}
}
