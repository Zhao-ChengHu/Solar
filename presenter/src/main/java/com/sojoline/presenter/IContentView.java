package com.sojoline.presenter;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/20
 *     desc   :	有加载内容的页面可以实现该接口
 *     version: 1.0
 * </pre>
 */

public interface IContentView {

	/**
	 * 加载错误
	 */
	void showError();

	/**
	 * 数据为空
	 */
	void showEmpty();

	/**
	 * 加载
	 */
	void showLoading();

	/**
	 * 取消加载
	 */
	void showNormal();
}
