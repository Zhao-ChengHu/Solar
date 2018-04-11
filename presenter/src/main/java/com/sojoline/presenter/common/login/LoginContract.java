package com.sojoline.presenter.common.login;


import com.sojoline.model.request.LoginRequest;
import com.sojoline.presenter.BasePresenter;
import com.sojoline.presenter.IBaseView;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface LoginContract {
	interface View extends IBaseView{
		/**
		 * 登录成功回调
		 * @param token
		 * @param userId
		 */
		void loginSuccess(String token, String userId, String userType);
	}

	abstract class Presenter extends BasePresenter<View> {

		/**
		 * 登录
		 * @param request
		 */
		public abstract void login(LoginRequest request);
	}
}
