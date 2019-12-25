package com.lambo.onlineretailers.redis;

public class UserKey extends BasePrefix{

	private UserKey(String prefix) {
		super(prefix);
	}
	public static final UserKey TOKEN = new UserKey("token");
}
