package com.tangerine.specter.jwt.anno;

public enum RoleEnum {
	ADMIN(1),
	LEADER(2),
	SUPPORT(3),
	VOLUNTEER(4),
	SECURITY_ADMIN(5),
	SUPER_ADMIN(6);

	private int key;

	private RoleEnum(int key) {
		this.key = key;
	}

	public int toIntValue() {
		return this.key;
	}

	public static RoleEnum toKey(int key) {
		if (ADMIN.key == key) {
			return ADMIN;
		} else if (LEADER.key == key) {
			return LEADER;
		} else if (SUPPORT.key == key) {
			return SUPPORT;
		} else if (VOLUNTEER.key == key) {
			return VOLUNTEER;
		} else if (SECURITY_ADMIN.key == key) {
			return SECURITY_ADMIN;
		} else if (SUPER_ADMIN.key == key) {
			return SUPER_ADMIN;
		} else {
			throw new RuntimeException("Unknown RoleKey[" + key + "].");
		}
	}
}