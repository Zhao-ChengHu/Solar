package com.sojoline.presenter.solar.data;

import com.sojoline.model.request.SolarEnergyRequest;
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

public interface SolarEnergyContract {
	interface View extends IBaseView{
		void energySuccess(Object o);
	}

	abstract class Presenter extends BasePresenter<View>{
		/**
		 * 获取光伏发电量
		 * 总，年，月，时
		 * @param request 请求参数
		 */
		public abstract void getSolarEnergy(SolarEnergyRequest request);

		/**
		 * 获取光伏发电量(天)
		 * @param request 请求参数
		 */
		@Deprecated
		public abstract void getDayEnergy(SolarEnergyRequest request);

		/**
		 * 获取光伏发电量(月)
		 * @param request 请求参数
		 */
		@Deprecated
		public abstract void getMonthEnergy(SolarEnergyRequest request);

		/**
		 * 获取光伏发电量(年)
		 * @param request 请求参数
		 */
		@Deprecated
		public abstract void getYearEnergy(SolarEnergyRequest request);

		/**
		 * 获取光伏发电量(总)
		 * @param request 请求参数
		 */
		@Deprecated
		public abstract void getTotalEnergy(SolarEnergyRequest request);
	}
}
