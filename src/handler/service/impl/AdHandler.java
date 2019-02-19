package handler.service.impl;

import handler.pojo.Post;
import handler.service.PostHandler;

/**
 * 广告处理器
 */
public class AdHandler extends PostHandler {

	@Override
	public void handlerRequest(Post post) {
		// 广告内容
		String content = post.getContent();

		content = content.replace("广告", "**");
		post.setContent(content);

		System.out.println("过滤广告...");
		// 传递给下一个处理器
		next(post);
	}

}
