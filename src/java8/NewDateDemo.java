package java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 日期时间新特性
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月22日
 *
 */
public class NewDateDemo {

	/**
	 * 非线程安全 − java.util.Date 是非线程安全的，所有的日期类都是可变的，这是Java日期类最大的问题之一。
	 *
	 * 设计很差 −
	 * Java的日期/时间类的定义并不一致，在java.util和java.sql的包中都有日期类，此外用于格式化和解析的类在java.text包中定义。java.util.Date同时包含日期和时间，而java.sql.Date仅包含日期，将其纳入java.sql包并不合理。另外这两个类都有相同的名字，这本身就是一个非常糟糕的设计。
	 * 
	 * 时区处理麻烦 −
	 * 日期类并不提供国际化，没有时区支持，因此Java引入了java.util.Calendar和java.util.TimeZone类，但他们同样存在上述所有的问题。
	 * 
	 * Java 8 在 java.time 包下提供了很多新的 API。以下为两个比较重要的 API：
	 * 
	 * Local(本地) − 简化了日期时间的处理，没有时区的问题。
	 * 
	 * Zoned(时区) − 通过制定的时区处理日期时间。
	 */
	public static void main(String[] args) {
		// 获取当前时间
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("当前时间：" + localDateTime);

		LocalDate localDate = localDateTime.toLocalDate();
		System.out.println(localDate);

		Month month = localDateTime.getMonth();
		int day = localDateTime.getDayOfMonth();
		int second = localDateTime.getSecond();
		System.out.println("月：" + month + " -> 日：" + day + " -> 秒：" + second);

		LocalDateTime time = localDateTime.withDayOfMonth(6).withYear(2019);
		System.out.println(time);

		LocalDate date = LocalDate.of(2019, Month.APRIL, 11);
		System.out.println(date);

		LocalTime localTime = LocalTime.of(12, 55);
		System.out.println(localTime);

		// 解析字符串
		LocalTime ltime = LocalTime.parse("15:23:56");
		System.out.println(ltime);
		
		//ZonedDateTime
		// 获取当前时间日期
		ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
		System.out.println("date1: " + date1);

		ZoneId id = ZoneId.of("Europe/Paris");
		System.out.println("ZoneId: " + id);

		ZoneId currentZone = ZoneId.systemDefault();
		System.out.println("当期时区: " + currentZone);
	}
}
