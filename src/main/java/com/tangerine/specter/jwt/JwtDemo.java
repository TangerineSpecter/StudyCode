package com.tangerine.specter.jwt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tangerine.specter.jwt.pojo.AuthUserVo;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

@Slf4j
public class JwtDemo {
	
	/**
	 * 过期时间 10秒
	 */
	private static final Long EXPIRE_TIME = 10 * 1000L;
	
	/**
	 * 生成Token
	 * Algorithm.HMAC256():使用HS256生成token,密钥则是用户的密码，唯一密钥的话可以保存在服务端。
	 * withAudience()存入需要保存在token的信息，这里我把用户ID存入token中
	 */
	public static String getToken(AuthUserVo authUserVO) {
		String sign = authUserVO.getPassword();
		authUserVO.setPassword(null);
		return JWT.create().withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
				.withAudience(JSON.toJSONString(authUserVO))
				.sign(Algorithm.HMAC256(sign));
	}
	
	public static void main(String[] args) {
		AuthUserVo vo = new AuthUserVo();
		vo.setPassword("123456");
		vo.setName("测试账号");
		String token = getToken(vo);
		log.info(token);
		AuthUserVo authUser1 = JSONObject.parseObject(JWT.decode(token).getAudience().get(0), AuthUserVo.class);
		log.info(JSON.toJSONString(authUser1));
		String errorToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ7XCJuYW1lXCI6XCLmtYvor5XotKblj7dcIn0iLCJleHAiOjE2NDMwMTMzNDd9.9avwcL5-oADY5lsIrIw45ZgFhS9_54rlJgNlKDhN6E8";
		AuthUserVo authUser2 = JSONObject.parseObject(JWT.decode(errorToken).getAudience().get(0), AuthUserVo.class);
		log.info(JSON.toJSONString(authUser2));
	}
}
