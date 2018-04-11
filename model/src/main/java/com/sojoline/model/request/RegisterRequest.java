package com.sojoline.model.request;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class RegisterRequest {
	private String username;
	private String password;
	private String code;
	private String cloudType;

	public RegisterRequest() {
	}

	public RegisterRequest(String username, String password, String code, String cloudType) {
		this.username = username;
		this.password = password;
		this.code = code;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCloudType() {
		return cloudType;
	}

	public void setCloudType(String cloudType) {
		this.cloudType = cloudType;
	}
}
