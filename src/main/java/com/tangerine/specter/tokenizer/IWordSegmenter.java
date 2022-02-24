package com.tangerine.specter.tokenizer;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface IWordSegmenter {
	
	/**
	 * 获取文本的所有分词结果
	 *
	 * @param text 文本
	 * @return 所有的分词结果，去除重复
	 */
	default Set<String> seg(String text) {
		return new HashSet<>(segMore(text).values());
	}
	
	/**
	 * 获取文本的所有分词结果
	 *
	 * @param text 文本
	 * @return 所有的分词结果，KEY 为分词器模式，VALUE 为分词器结果
	 */
	Map<String, String> segMore(String text);
}
