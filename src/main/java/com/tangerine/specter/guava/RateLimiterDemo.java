package com.tangerine.specter.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * guava 限流工具类使用演示
 *
 * @author TangerineSpecter
 */
public class RateLimiterDemo {

	/**
	 * 初始化令牌桶大小，10个
	 */
	private RateLimiter rateLimiter = RateLimiter.create(10);

	/**
	 * 限流演示
	 */
	public void process() {
		//获取令牌，获取不到就阻塞
		rateLimiter.acquire();
		//执行业务操作，例如写入数据库
		this.doService();
	}

	/**
	 * 如果请求可以丢弃，需要快速失败
	 * 为了避免源源不断的请求将整体的系统资源耗尽
	 * 可以使用指定时间的tryAcquire方法
	 */
	public void tryProcess() {
		//未请求到limiter则5秒后返回false
		if (rateLimiter.tryAcquire(5, TimeUnit.SECONDS)) {
			doService();
		} else {
			doSomethingElse();
		}
	}

	/**
	 * 业务代码
	 */
	private void doService() {
		System.out.println("我获取到了令牌！");
	}

	/**
	 * 其他业务
	 */
	private void doSomethingElse() {
		System.out.println("我没有拿到令牌，去干别的事情了！");
	}
}
