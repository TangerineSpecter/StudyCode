package com.tangerine.specter.design_pattern.handler;

import com.tangerine.specter.design_pattern.handler.pojo.Post;
import com.tangerine.specter.design_pattern.handler.service.PostHandler;
import com.tangerine.specter.design_pattern.handler.service.impl.AdHandler;
import com.tangerine.specter.design_pattern.handler.service.impl.SensitiveWordsHandler;
import com.tangerine.specter.design_pattern.handler.service.impl.YellowHandler;

/**
 * 责任链模式
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月19日
 *
 */
public class UseDemo {

	/**
	 * 责任链模式 符合开闭原则
	 * 
	 * 在责任链模式中，N个handler子类处理同一个请求，拥有同一个父类，只不过职责上有所差别。
	 * 
	 * 责任链模式为某个请求创建一个对象链，每个对象依次检查此请求，并对其进行处理，或者将它传给链中的下一个对象。
	 * 
	 * 优点：请求者和接受者解耦；可以动态增加/减少责任链上的对象，或修改执行顺序。
	 * 
	 * 缺点：调用者不知道能被哪些责任链对象处理，不利于排错；
	 * 用户请求可能被责任链中途拦截，最终未必被真正执行，这点既是优点也是缺点，我们可以利用它做权限控制拦截器。
	 */
	public static void main(String[] args) {
		// 创建责任对象
		PostHandler adHandler = new AdHandler();
		PostHandler yellowHandler = new YellowHandler();
		PostHandler swHandler = new SensitiveWordsHandler();

		// 形成责任链
		yellowHandler.setSuccessor(swHandler);
		adHandler.setSuccessor(yellowHandler);

		Post post = new Post();
		post.setContent("我是正常内容，我是广告，我是色情，我是敏感词，我是正常内容");
		System.out.println("过滤前的内容为：" + post.getContent());
		adHandler.handlerRequest(post);
		System.out.println("过滤后的内容为：" + post.getContent());
	}
}
