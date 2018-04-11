package com.sojoline.presenter.common.user;

import com.sojoline.model.bean.common.User;
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

public interface UserContract {
	interface View extends IBaseView{
		void changeHeaderSuccess();
		void changeInfoSuccess();
		void changeNickSuccess();
		void success(User user);
	}

	abstract class Presenter extends BasePresenter<View>{
		/**
		 * 获取用户信息
		 */
		public abstract void getUserInfo();

		/**
		 * 修改用户头像
		 * @param map
		 */
		public abstract void changeUserHeader(HashMap<String,String> map);

		/**
		 * 修改用户昵称
		 * @param map
		 */
		public abstract void changeNick(HashMap<String,String> map);

		/**
		 * 修改用户信息（性别，手机号，邮箱，地址）
		 *
		 * @param map
		 */
		public abstract void changeUserInfo(HashMap<String,String> map);
	}
}
