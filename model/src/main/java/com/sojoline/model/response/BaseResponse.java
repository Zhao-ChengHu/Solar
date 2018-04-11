package com.sojoline.model.response;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class BaseResponse {
	/**
	 * 成功编码
	 */
	private static final int SUCCEED_CODE = 200;

	private int code = -1;
	private String msg;

	public boolean isSuccess(){
		return code == SUCCEED_CODE;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
