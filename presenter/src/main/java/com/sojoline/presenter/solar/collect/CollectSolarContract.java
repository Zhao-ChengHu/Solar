package com.sojoline.presenter.solar.collect;

import com.sojoline.presenter.BasePresenter;
import com.sojoline.presenter.IBaseView;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface CollectSolarContract {
	interface View extends IBaseView{
		/**
		 * 收藏成功回调
		 */
		void collectSuccess();

		/**
		 * 取消收藏成功回调
		 */
		void uncollectSuccess();
	}

	abstract class Presenter extends BasePresenter<View>{
		/**
		 * 收藏光伏电站
		 * @param id 电站id
		 */
		public abstract void collectSolarStation(String id);

		/**
		 * 取消收藏光伏电站
		 * @param id
		 */
		public abstract void uncollectSolarStation(String id);
	}

}
