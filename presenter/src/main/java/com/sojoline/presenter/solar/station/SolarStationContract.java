package com.sojoline.presenter.solar.station;

import com.sojoline.model.bean.solar.SolarStation;
import com.sojoline.presenter.BasePresenter;
import com.sojoline.presenter.IBaseView;

import java.util.List;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface SolarStationContract {
	interface View extends IBaseView{
		/**
		 * 成功回调
		 */
		void success(List<SolarStation> list);
	}

	abstract class Presenter extends BasePresenter<View> {
		/**
		 * 获取光伏电站列表
		 * @param type 电站类型
		 */
		public abstract void getStationList(String type);

		/**
		 * 搜索光伏电站
		 * @param name 电站名称
		 */
		public abstract void searchStation(String name);
	}
}
