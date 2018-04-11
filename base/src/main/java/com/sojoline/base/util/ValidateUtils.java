package com.sojoline.base.util;

import android.text.TextUtils;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class ValidateUtils {
	private ValidateUtils(){
		throw new UnsupportedOperationException("u can't instantiate me...");
	}

	/**
	 * 验证手机号是否正确
	 * @param num 待验证手机号
	 * @return	{@code true}:匹配<br>{@code false}:不匹配
	 */
	public static boolean validatePhone(String num){
		if (TextUtils.isEmpty(num)){
			return false;
		}else {
			String numRegex = "[1][345678]\\d{9}";
			if (num.matches(numRegex)) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 验证密码
	 * 判断密码格式是否正确
	 *
	 * @param psd 待验证密码
	 * @return {@code true}:匹配<br>{@code false}:不匹配
	 */
	public static boolean validatePsd(String psd) {
		// 密码必须由数字和字母组成
		String psdRegex = ".*[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]";
		if (psd.length() < 6) {
			return false;
		} else if (!psd.matches(psdRegex)) {
			return false;
		}
		return true;
	}

	/**
	 * 验证邮箱
	 * @param email 带验证邮箱
	 * @return	{@code true}:匹配<br>{@code false}:不匹配
	 */
	public static boolean validateEmail(String email){
		String emailRegex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		if (email.matches(emailRegex)){
			return true;
		}else {
			return false;
		}
	}
}
