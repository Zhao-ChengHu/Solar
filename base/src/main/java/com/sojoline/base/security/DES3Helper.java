package com.sojoline.base.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * <pre>
 *     author : 李小勇
 *     time   : 2017/09/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class DES3Helper {
	// 加解密统一使用的编码方式
	private final static String encoding = "UTF-8";
	/**
	 * 密钥算法
	 * */
	public static final String KEY_ALGORITHM="DESede";

	/**
	 * 加密
	 * 解密算法/工作模式/填充方式
	 * */
	public static final String CIPHER_ALGORITHM="DESede/CBC/PKCS5Padding";

	/**
	 * 3DES加密
	 *
	 * @param plainText 普通文本
	 * @param secretKey 秘钥，长度28位的字符串
	 * @param iv        向量，长度8位的字符串
	 * @throws Exception
	 */
	public static String encode(String plainText, String secretKey, String iv) throws Exception {
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		Key key = keyFactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, key, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
		return Base64Helper.encode(encryptData);
	}

	/**
	 * 3DES解密
	 *
	 * @param encryptText 加密文本
	 * @throws Exception
	 */
	public static String decode(String encryptText, String secretKey, String iv) throws Exception {
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		Key key = factory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, key, ips);
		byte[] decryptData = cipher.doFinal(Base64Helper.decode(encryptText));

		return new String(decryptData, encoding);
	}
}
