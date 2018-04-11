package com.sojoline.presenter.solar.data;

import com.sojoline.basiclib.rx.SimpleSubscriber;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.dagger.ApiComponentHolder;
import com.sojoline.model.response.SolarDeviceResponse;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarDevicePresenter extends SolarDeviceContract.Presenter {
	@Override
	public void getSolarDevices(String id) {
		ApiComponentHolder.apiComponent
				.solarService()
				.getStationDeviceInfo(id)
				.take(1)
				.compose(SchedulersCompat.<SolarDeviceResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<SolarDeviceResponse>() {
					@Override
					public void onError(Throwable e) {
						super.onError(e);
						e.printStackTrace();
						if (getView() != null){
							getView().showNormal();
							getView().requestFailed(null);
						}
					}

					@Override
					public void onNext(SolarDeviceResponse response) {
						super.onNext(response);
						getView().showNormal();
						if (response.isSuccess()){
							getView().success(response.getData());
						}else {
							getView().requestFailed(response.getMsg());
						}
					}

					@Override
					public void onStart() {
						super.onStart();
						if (getView() != null){
							getView().showLoading(null);
						}
					}
				});
	}
}
