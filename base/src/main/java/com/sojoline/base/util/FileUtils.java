package com.sojoline.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class FileUtils {
	/**
	 * 获取文件指纹
	 */
	public static String getFileMD5_32(File file) {
		if (!file.isFile()) {
			return null;
		}
		MessageDigest digest;
		FileInputStream in;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bytesToHexString(digest.digest());
	}

	public static String getFileMd5_16(File file) {
		String fileMD5_32 = getFileMD5_32(file);
		if (fileMD5_32 != null)
			return fileMD5_32.substring(8, 24);
		return null;
	}

	private static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
}
