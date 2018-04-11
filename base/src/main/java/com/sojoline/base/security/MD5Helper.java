package com.sojoline.base.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <pre>
 *     author : 李小勇
 *     time   : 2017/09/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MD5Helper {
	private final static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
			'c', 'd', 'e', 'f'};

	private static MessageDigest messagedigest = null;

	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.err.println(MD5Helper.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
			e.printStackTrace();
		}
	}

	public static String getMD5StringWithSalt(String password, String salt) {
		if (password == null) {
			throw new IllegalArgumentException("password不能为null");
		}
		if (salt.equals("") || salt.length() == 0) {
			throw new IllegalArgumentException("salt不能为空");
		}
		if ((salt.toString().lastIndexOf("{") != -1) || (salt.toString().lastIndexOf("}") != -1)) {
			throw new IllegalArgumentException("salt中不能包含 { 或者 }");
		}
		return getMD5String(password + "{" + salt.toString() + "}");
	}

	public static String getMD5String(String str) {
		return getMD5String(str.getBytes());
	}

	private static String getMD5String(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int i = m; i < k; i++) {
			appendHexPair(bytes[i], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}
}
