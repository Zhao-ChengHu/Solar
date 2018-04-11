package com.sojoline.model.request;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class LoginRequest {
	private String username;
	private String password;
	private String cloudType;

	public LoginRequest() {
	}

	public LoginRequest(String username, String password, String cloudType) {
		this.username = username;
		this.password = password;
		this.cloudType = cloudType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCloudType() {
		return cloudType;
	}

	public void setCloudType(String cloudType) {
		this.cloudType = cloudType;
	}
}
