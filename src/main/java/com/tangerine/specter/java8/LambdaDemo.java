package com.tangerine.specter.java8;

/**
 * Lambda表达式
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月20日
 *
 */
public class LambdaDemo {

	// 只能引用标记了final的外层局部变量
	private static final String salutation = "Hello！";

	/**
	 * Lambda表达式主要用来定义行内执行的方法类接口，免去使用匿名方法麻烦。
	 * 
	 */
	public static void main(String[] args) {
		LambdaDemo lam = new LambdaDemo();

		// 类型声明
		MathOperation addition = (int a, int b) -> a + b;

		// 不用类型声明
		MathOperation subtraction = (a, b) -> a - b;

		// 大括号中的返回语句
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};

		// 没有大括号的返回语句
		MathOperation division = (int a, int b) -> a / b;
		System.out.println("10 + 5 = " + lam.operate(10, 5, addition));
		System.out.println("10 - 5 = " + lam.operate(10, 5, subtraction));
		System.out.println("10 * 5 = " + lam.operate(10, 5, multiplication));
		System.out.println("10 / 5 = " + lam.operate(10, 5, division));

		// 不用括号
		GreetingService greetingService1 = message -> System.out.println(salutation + message);
		GreetingService greetingService2 = message -> System.out.println("Hi~ " + message);

		greetingService1.sayMessage("Bob");
		greetingService2.sayMessage("Joy");

		final int num = 1;
		Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
		s.convert(5);
		
		//lambda 表达式的局部变量可以不用声明为 final，但是必须不可被后面的代码修改（即隐性的具有 final 的语义）
	}

	interface MathOperation {
		int operation(int a, int b);
	}

	interface GreetingService {
		void sayMessage(String message);
	}

	private int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}

	public interface Converter<T1, T2> {
		void convert(int i);
	}
}
