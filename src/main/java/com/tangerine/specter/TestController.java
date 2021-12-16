package com.tangerine.specter;

import com.tangerine.specter.pojo.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/load")
	public String hello(Integer index) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		for (Integer i = 0; i < index; i++) {
			try {
				time();
			}catch (Exception ignored) {

			}
			return null;
		}
		long endTime = System.currentTimeMillis();
		return endTime - startTime + "ms";
	}

	@RequestMapping("/load1")
	public String hello1(Integer index) throws Exception {
		List<CompletableFuture<User>> futures = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		for (Integer i = 0; i < index; i++) {
			futures.add(CompletableFuture.supplyAsync(() -> {
				try {
					time();
				}catch (Exception ignored) {}
				return null;
			}));
		}
		CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
		// 下面的方法可以帮助我们获得所有子任务的处理结果
		CompletableFuture<List<User>> finalResults = allFutures.thenApply(v ->
				futures.stream().map(CompletableFuture::join).collect(Collectors.toList())
		);
		finalResults.join();
		long endTime = System.currentTimeMillis();
		return endTime - startTime + "ms" + ",结果：" + finalResults.get();
	}

	private static AtomicInteger count;

	@RequestMapping("/load2")
	public String hello2(Integer index) throws Exception {
		count = new AtomicInteger(0);
		List<CompletableFuture<User>> futures = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		for (Integer i = 0; i < index; i++) {
			futures.add(CompletableFuture.supplyAsync(() -> {
				try {
					time();
				} catch (Exception e) {
					System.out.println("接收到异常");
				}
				return null;
			}));
		}
		CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
		long endTime = System.currentTimeMillis();
		return endTime - startTime + "ms" + ",结果：" ;
	}

	public User time() throws Exception{
		count.incrementAndGet();
		System.out.println(count);
		if(count.get() == 5) {
			System.out.println("haha");
			throw new RuntimeException("报错");
		}
		Thread.sleep(2000);
		return User.builder().id(1L).name("小红").build();
	}
}
