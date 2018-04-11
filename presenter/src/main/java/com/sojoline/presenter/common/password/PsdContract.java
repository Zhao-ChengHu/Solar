package com.sojoline.presenter.common.password;

import com.sojoline.presenter.BasePresenter;
import com.sojoline.presenter.IBaseView;

import java.util.HashMap;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface PsdContract {
	interface View extends IBaseView{
		void success();
	}

	abstract class Presenter extends BasePresenter<View>{
		/**
		 * 忘记密码
		 * @param map
		 */
		public abstract void forgetPsd(HashMap<String,String> map);

		/**
		 * 修改密码
		 * @param map
		 */
		public abstract void resetPsd(HashMap<String,String> map);
	}
}
