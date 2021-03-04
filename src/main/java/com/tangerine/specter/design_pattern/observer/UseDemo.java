package com.tangerine.specter.design_pattern.observer;

import java.util.Observable;
import java.util.Observer;

import com.tangerine.specter.design_pattern.observer.pojo.NewsOffice;
import com.tangerine.specter.design_pattern.observer.pojo.NewsOffice2;
import com.tangerine.specter.design_pattern.observer.service.ISubject;
import com.tangerine.specter.design_pattern.observer.service.impl.CustomerA;
import com.tangerine.specter.design_pattern.observer.service.impl.CustomerB;
import com.tangerine.specter.design_pattern.observer.service.impl.CustomerC;

/**
 * 观察者模式
 * 
 * @author TangerineSpecter
 * @Date 2019年2月12日
 */
public class UseDemo {

	/**
	 * 观察者模式：定义了对象之间一对多关系，当被观察者的状态改变了，就通知所有关联的观察者。
	 * 被观察者一般会有一份观察者列表，只要状态改变就执行update通知所有观察者。
	 * 
	 * java自带了Observable类就提供观察者模式，简单的场景使用比较方便。
	 * 
	 * 优点：观察者和被观察者之间是抽象耦合的，自带触发机制。无需知道观察者是谁，只要符合观察者接口就可以。
	 * 
	 * 缺点：观察者只知道被观察者发生了变化，但是并不知道发生的具体变化是什么。 如果观察者较多，一个个通知性能很低很费时。
	 */
	public static void main(String[] args) {
		// 案例1：用java实现
		ISubject officeA = new NewsOffice();
		CustomerA customerA = new CustomerA();
		CustomerB customerB = new CustomerB();
		// 客户A订阅报纸ֽ
		officeA.registerObserver(customerA);
		officeA.registerObserver(customerB);
		// 派送报纸ֽ
		officeA.notifyAllObservers();

		// 案例2：用java自带实现
		Observable officeB = new NewsOffice2();
		Observer observer = new CustomerC();

		officeB.addObserver(observer);
		((NewsOffice2) officeB).newsPaperCome();
	}
}
