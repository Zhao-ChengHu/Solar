package com.sojoline.presenter.monitor.station;

import com.sojoline.model.bean.solar.SolarStation;
import com.sojoline.presenter.BasePresenter;
import com.sojoline.presenter.IBaseView;

import java.util.List;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface MonitorStationContract {
	interface View extends IBaseView {
		/**
		 * 成功回调
		 */
		void success(List<SolarStation> list);
	}

	abstract class Presenter extends BasePresenter<View> {
		/**
		 * 获取能耗监测电站列表
		 * @param type 电站类型
		 */
		public abstract void getStationList(String type);

		/**
		 * 搜索能耗监测电站
		 * @param name 电站名称
		 */
		public abstract void searchStation(String name);
	}
}
