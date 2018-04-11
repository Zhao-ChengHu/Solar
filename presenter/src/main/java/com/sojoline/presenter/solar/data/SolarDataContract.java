package com.sojoline.presenter.solar.data;

import com.sojoline.model.bean.solar.SolarData;
import com.sojoline.presenter.BasePresenter;
import com.sojoline.presenter.IBaseView;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface SolarDataContract {
	interface View extends IBaseView{
		void solarDataSuccess(SolarData data);
	}

	abstract class Presenter extends BasePresenter<View>{
		/**
		 * 获取光伏电站数据（统计）
		 * @param id 电站id
		 */
		public abstract void getSolarStationData(String id);
	}
}
