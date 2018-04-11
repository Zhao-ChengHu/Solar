package com.sojoline.base.util;

import android.text.TextUtils;

import com.sojoline.annotation.ISpfEncryption;
import com.sojoline.base.security.DES3Helper;
import com.sojoline.base.security.MD5Helper;
import com.sojoline.model.storage.AppInfoPreferences;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/09/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SpfEncryption implements ISpfEncryption {
	private static String SECRET_KEY;
	private static String IV;

	@Override
	public String encodeStr(String src) {
		if (TextUtils.isEmpty(src)){
			return src;
		}
		String secretKey = getSecretKey();
		String iv = getIv();
		if (TextUtils.isEmpty(secretKey)) {
			new RuntimeException("找不到本地加密秘钥！").printStackTrace();
			return null;
		}
		try {
			return DES3Helper.encode(src, secretKey, iv);
		} catch (Exception e) {
			e.printStackTrace();
			new RuntimeException("加密保存失败了！").printStackTrace();
			return null;
		}
	}

	@Override
	public String decodeStr(String src) {
		if (TextUtils.isEmpty(src)){
			return src;
		}
		String secretKey = getSecretKey();
		String iv = getIv();
		if (TextUtils.isEmpty(secretKey)) {
			new RuntimeException("找不到本地加密秘钥！").printStackTrace();
			return null;
		}
		try {
			return DES3Helper.decode(src, secretKey, iv);
		} catch (Exception e) {
			e.printStackTrace();
			new RuntimeException("解密失败了！").printStackTrace();
			return null;
		}
	}

	/**
	 * 登录成功就需要保存一次秘钥，后面加密需要
	 */
	public static void setSecretKey(String key) {
		if (TextUtils.isEmpty(key)) {
			AppInfoPreferences.get().setSecretKey(key);
		} else {
			String temp = null;
			for (int i = 0; i < 2; i++) { // 连续三次加密
				temp = MD5Helper.getMD5StringWithSalt(key, AppConstants.MD5_SALT);
				key = temp;
			}
			AppInfoPreferences.get().setSecretKey(temp);
		}
		SECRET_KEY = null;
		IV = null;
	}

	/**
	 * 取28位秘钥
	 */
	private static String getSecretKey() {
		if (TextUtils.isEmpty(SECRET_KEY)) {
			String secretKey = AppInfoPreferences.get().getSecretKey();
			if (TextUtils.isEmpty(secretKey)) {
				SECRET_KEY = null;
			} else {
				int start = 2;
				int end = 30;
				SECRET_KEY = secretKey.substring(start, end);
			}
		}
		return SECRET_KEY;
	}

	/**
	 * 取8位向量
	 */
	private static String getIv() {
		if (TextUtils.isEmpty(IV)) {
			String secretKey = AppInfoPreferences.get().getSecretKey();
			if (TextUtils.isEmpty(secretKey)) {
				IV = null;
			} else {
				int length = secretKey.length();
				int start = length - 10;
				int end = length - 2;
				IV = secretKey.substring(start, end);
			}
		}
		return IV;
	}
}
