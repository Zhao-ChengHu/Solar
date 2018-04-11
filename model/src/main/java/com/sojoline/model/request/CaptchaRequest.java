package com.sojoline.model.request;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class CaptchaRequest {
	private String phone;
	private int type;

	public CaptchaRequest() {
	}

	public CaptchaRequest(String phone, int type) {
		this.phone = phone;
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
