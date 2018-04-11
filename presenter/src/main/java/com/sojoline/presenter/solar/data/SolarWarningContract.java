package com.sojoline.presenter.solar.data;

import com.sojoline.model.response.WarningResponse;
import com.sojoline.presenter.BasePresenter;
import com.sojoline.presenter.IBaseView;

import java.util.HashMap;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface SolarWarningContract {
	interface View extends IBaseView{
		void success(WarningResponse.Content content);
	}

	abstract class Presenter extends BasePresenter<View>{
		/**
		 * 获取光伏电站告警列表
		 * @param request 请求参数
		 */
		public abstract void getWarningList(HashMap<String, Object> request);
	}
}
