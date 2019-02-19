package handler.service.impl;

import handler.pojo.Post;
import handler.service.PostHandler;

/**
 * 色情处理器
 */
public class YellowHandler extends PostHandler {

	@Override
	public void handlerRequest(Post post) {
		// 黄色内容
		String content = post.getContent();

		content = content.replace("色情", "**");
		post.setContent(content);

		System.out.println("过滤色情...");
		// 传递给下一个处理器
		next(post);
	}
}
