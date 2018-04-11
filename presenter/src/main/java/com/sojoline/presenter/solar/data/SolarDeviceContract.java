package com.sojoline.presenter.solar.data;

import com.sojoline.model.bean.solar.SolarDevice;
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

public interface SolarDeviceContract {
	interface View extends IBaseView{
		void success(SolarDevice data);
	}

	abstract class Presenter extends BasePresenter<View>{
		/**
		 * 获取光伏电站设备
		 * @param id 电站id
		 */
		public abstract void getSolarDevices(String id);
	}
}
