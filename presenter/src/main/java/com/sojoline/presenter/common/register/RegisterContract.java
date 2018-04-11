package com.sojoline.presenter.common.register;

import com.sojoline.model.request.RegisterRequest;
import com.sojoline.presenter.BasePresenter;
import com.sojoline.presenter.IBaseView;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface RegisterContract {
	interface View extends IBaseView{
		/**
		 * 注册成功回调
		 */
		void registerSuccess();
	}

	abstract class Presenter extends BasePresenter<View> {
		/**
		 * 注册
		 * @param request
		 */
		public abstract void register(RegisterRequest request);
	}
}
