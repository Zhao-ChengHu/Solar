package com.sojoline.presenter;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface IBasePresenter<R> {

	/**
	 * 绑定view
	 * @param view
	 */
	void attachView(R view);

	/**
	 * 分离
	 */
	void detachView();
}
