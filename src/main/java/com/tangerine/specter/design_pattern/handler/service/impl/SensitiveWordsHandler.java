package com.tangerine.specter.design_pattern.handler.service.impl;

import com.tangerine.specter.design_pattern.handler.pojo.Post;
import com.tangerine.specter.design_pattern.handler.service.PostHandler;

/**
 * 敏感词处理器
 */
public class SensitiveWordsHandler extends PostHandler {

	@Override
	public void handlerRequest(Post post) {
		// 敏感内容
		String content = post.getContent();

		content = content.replace("敏感词", "**");
		post.setContent(content);

		System.out.println("过滤敏感词...");
		// 传递给下一个处理器
		next(post);
	}

}
