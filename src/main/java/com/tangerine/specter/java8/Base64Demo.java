package com.tangerine.specter.java8;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * base64编码库
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月25日
 *
 */
public class Base64Demo {

	public static void main(String[] args) {
		// 使用基本编码
		try {
			String encodeToString = Base64.getEncoder().encodeToString("TangerineSpecter.github".getBytes("UTF-8"));
			System.out.println("Base64 编码字符串 (基本) :" + encodeToString);

			// 解码
			byte[] decode = Base64.getDecoder().decode(encodeToString);
			encodeToString = Base64.getUrlEncoder().encodeToString("TangerineSpecter.github".getBytes("utf-8"));
			System.out.println("Base64 编码字符串 (URL) :" + decode);

			StringBuilder stringBuilder = new StringBuilder();

			for (int i = 0; i < 10; ++i) {
				stringBuilder.append(UUID.randomUUID().toString());
			}

			byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
			String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
			System.out.println("Base64 编码字符串 (MIME) :" + mimeEncodedString);
			System.out.println("原始字符串: " + new String(decode, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
