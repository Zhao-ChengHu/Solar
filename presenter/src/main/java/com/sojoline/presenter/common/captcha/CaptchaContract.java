package com.sojoline.presenter.common.captcha;

import com.sojoline.model.request.CaptchaRequest;
import com.sojoline.presenter.BasePresenter;
import com.sojoline.presenter.IBaseView;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface CaptchaContract {
	interface View extends IBaseView{
		void captchaSuccess();
	}

	abstract class Presenter extends BasePresenter<View>{
		/**
		 * 发送验证码
		 * @param request
		 */
		public abstract void sentCaptcha(CaptchaRequest request);
	}
}
