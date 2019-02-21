package java8;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 流式编程
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月21日
 *
 */
public class StreamDataDemo {

	public static void main(String[] args) {
		/**
		 * +--------------------+       +------+   +------+   +---+   +-------+
		 * | stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
		 * +--------------------+       +------+   +------+   +---+   +-------+
		 * 
		 * stream() − 为集合创建串行流。
		 * parallelStream() − 为集合创建并行流。
		 * 
		 * Stream提供了内部迭代的方式， 通过访问者模式(Visitor)实现。
		 * 
		 * 数据源: 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。
		 * 聚合操作: 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。
		 */
		List<String> strs = Arrays.asList("haha","","hehe","123","","","world");
		System.out.println(strs);
		List<String> collect = strs.stream().filter(str -> !str.isEmpty()).collect(Collectors.toList());
		System.out.println(collect);
		
		/**
		 * 新方法forEach迭代
		 * limit获取指定数量的流
		 */
		Random random = new Random();
		random.ints().limit(10).forEach(System.out::println);
		
		/**
		 * map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数
		 */
		List<Integer> nums = Arrays.asList(3,4,2,5,76,123,4,23);
		//获取对应数平方
		List<Integer> numm = nums.stream().map(i -> i*i).distinct().collect(Collectors.toList());
		System.out.println(numm);
		
		/**
		 * shorted排序
		 */
		List<Integer> sorts = nums.stream().sorted().collect(Collectors.toList());
		System.out.println(sorts);
		
		/**
		 * filter 方法用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤出空字符串：
		 */
		List<Integer> numlter = nums.stream().filter(num -> num < 10).collect(Collectors.toList());
		System.out.println(numlter);
	}
}
