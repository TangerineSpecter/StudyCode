package java8;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法引用
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月21日
 *
 */
public class MethodReferenceDemo {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("hello ");
		list.add("world ");
		list.add("my ");
		list.add("cat ");
		list.forEach(System.out::printf);
	}

}
