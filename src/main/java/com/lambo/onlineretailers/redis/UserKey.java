package com.lambo.onlineretailers.redis;

public class UserKey extends BasePrefix{

	private UserKey(String prefix) {
		super(prefix);
	}

	private UserKey(int expireSeconds, String prefix){
		super(expireSeconds,prefix);
	}
	public static final UserKey TOKEN = new UserKey(60*60*24*2,"token");
}
