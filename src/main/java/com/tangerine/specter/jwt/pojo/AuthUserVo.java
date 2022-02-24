package com.tangerine.specter.jwt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserVo {
	
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 社交账户ID
	 */
	private String socialId;
	
	/**
	 * 用户名
	 */
	private String name;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 角色主键 1 普通用户 2 admin
	 */
	private Long roleId;
	
	/**
	 * 头像
	 */
	private String avatar;
	
	private String token;
}
