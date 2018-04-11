package com.sojoline.base.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * <pre>
 *     author : 李小勇
 *     time   : 2017/09/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class ScreenUtils {
	private ScreenUtils() {
		throw new UnsupportedOperationException("u can't instantiate me...");
	}

	/**
	 * 获取屏幕的宽度（单位：px）
	 *
	 * @return 屏幕宽px
	 */
	public static int getScreenWidth() {
		WindowManager windowManager = (WindowManager) Utils.getContext().getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
		windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
		return dm.widthPixels;
	}

	/**
	 * 获取屏幕的高度（单位：px）
	 *
	 * @return 屏幕高px
	 */
	public static int getScreenHeight() {
		WindowManager windowManager = (WindowManager) Utils.getContext().getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
		windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
		return dm.heightPixels;
	}

	/**
	 * dp转px
	 *
	 * @param dpValue dp值
	 * @return px值
	 */
	public static int dp2px(float dpValue) {
		final float scale = Utils.getContext().getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * px转dp
	 *
	 * @param pxValue px值
	 * @return dp值
	 */
	public static int px2dp( float pxValue) {
		final float scale = Utils.getContext().getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * sp转px
	 *
	 * @param spValue sp值
	 * @return px值
	 */
	public static int sp2px(float spValue) {
		final float fontScale = Utils.getContext().getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}
}
