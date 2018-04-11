package com.sojoline.presenter;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface IBaseView {
	/**
	 * 加载
	 * @param msg
	 */
	void showLoading(String msg);

	/**
	 * 取消加载
	 */
	void showNormal();

	/**
	 * 请求失败
	 * @param msg
	 */
	void requestFailed(String msg);
}
