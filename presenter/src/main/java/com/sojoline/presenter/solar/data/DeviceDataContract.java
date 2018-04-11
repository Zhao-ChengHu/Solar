package com.sojoline.presenter.solar.data;

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

public interface DeviceDataContract {
	interface View extends IBaseView{
		void success(Object object);
	}

	abstract class Presenter extends BasePresenter<View>{
		/**
		 * 获取设备实时数据
		 * @param id 设备id
		 */
		public abstract void getDeviceData(String id);

		/**
		 * 获取汇流箱实时数据
		 * @param id 设备id
		 */
		public abstract void getCombinerData(String id);

		/**
		 * 获取箱变实时数据
		 * @param id 设备id
		 */
		public abstract void getTransformerData(String id);

		/**
		 * 获取逆变器实时数据
		 * @param id 设备id
		 */
		public abstract void getInverterData(String id);
	}
}
