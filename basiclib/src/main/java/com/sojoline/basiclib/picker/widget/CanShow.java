package com.sojoline.basiclib.picker.widget;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface CanShow {
	/**
	 * 设置模式
	 * @param mode 模式
	 */
	void setMode(DateMode mode);

	/**
	 * 获取模式
	 * @return DateMode
	 */
	DateMode getMode();

	/**
	 * 显示view
	 */
	void show();

	/**
	 * 隐藏view
	 */
	void hide();

	/**
	 * 是否显示
	 * true 显示，false 隐藏
	 * @return true or false
	 */
	boolean isShow();
}
