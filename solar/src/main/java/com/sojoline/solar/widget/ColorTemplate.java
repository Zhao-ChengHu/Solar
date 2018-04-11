package com.sojoline.solar.widget;

import android.graphics.Color;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/31
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class ColorTemplate {

	public static final int[] MATERIAL_COLORS = {
			rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db")
	};

	/**
	 * Converts the given hex-color-string to rgb.
	 *
	 * @param hex
	 * @return
	 */
	public static int rgb(String hex) {
		int color = (int) Long.parseLong(hex.replace("#", ""), 16);
		int r = (color >> 16) & 0xFF;
		int g = (color >> 8) & 0xFF;
		int b = (color >> 0) & 0xFF;
		return Color.rgb(r, g, b);
	}
}
