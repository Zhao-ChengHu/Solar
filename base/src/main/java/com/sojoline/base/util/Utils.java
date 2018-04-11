package com.sojoline.base.util;

import android.content.Context;

/**
 * <pre>
 *     author : 李小勇
 *     time   : 2017/09/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class Utils {
	private static Context context;

	private Utils() {
		throw new UnsupportedOperationException("u can't instantiate me...");
	}

	/**
	 * 初始化工具类
	 *
	 * @param context 上下文
	 */
	public static void init(Context context) {
		Utils.context = context.getApplicationContext();
	}

	/**
	 * 获取ApplicationContext
	 *
	 * @return ApplicationContext
	 */
	public static Context getContext() {
		if (context != null) return context;
		throw new NullPointerException("u should init first");
	}
}
