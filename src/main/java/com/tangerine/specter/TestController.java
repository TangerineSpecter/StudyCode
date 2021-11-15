package com.tangerine.specter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/load")
	public String hello(Integer index) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		for (Integer i = 0; i < index; i++) {
			time();
		}
		long endTime = System.currentTimeMillis();
		return endTime - startTime + "ms";
	}

	@RequestMapping("/load1")
	public String hello1(Integer index) throws Exception {
		List<CompletableFuture<String>> futures = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		for (Integer i = 0; i < index; i++) {
			futures.add(CompletableFuture.supplyAsync(this::time));
		}
		CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
		// 下面的方法可以帮助我们获得所有子任务的处理结果
		CompletableFuture<List<String>> finalResults = allFutures.thenApply(v ->
				futures.stream().map(CompletableFuture::join).collect(Collectors.toList())
		);
		finalResults.join();
		long endTime = System.currentTimeMillis();
		return endTime - startTime + "ms" + ",结果：" + finalResults.get();
	}

	public String time() {
		try {
			Thread.sleep(2000);
		} catch (Exception ignored) {

		}
		return "test";
	}
}
