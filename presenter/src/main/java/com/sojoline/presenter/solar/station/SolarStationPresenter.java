package com.sojoline.presenter.solar.station;

import com.sojoline.basiclib.rx.SimpleSubscriber;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.dagger.ApiComponentHolder;
import com.sojoline.model.response.SolarStationResponse;

import java.net.ConnectException;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarStationPresenter extends SolarStationContract.Presenter {
	@Override
	public void getStationList(String type) {
		ApiComponentHolder.apiComponent
				.solarService()
				.getSolarStationList(type)
				.take(1)
				.compose(SchedulersCompat.<SolarStationResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<SolarStationResponse>() {
					@Override
					public void onError(Throwable e) {
						e.printStackTrace();
						if (getView() != null){
							getView().showNormal();
							if (e instanceof ConnectException){
								getView().requestFailed("无法连接到服务器");
							}else {
								getView().requestFailed(null);
							}
						}
					}

					@Override
					public void onNext(SolarStationResponse response) {
						super.onNext(response);
						getView().showNormal();
						if (response.isSuccess()){
							getView().success(response.getList());
						}else {
							getView().requestFailed(response.getMsg());
						}
					}

					@Override
					public void onStart() {
						if (getView() != null){
							getView().showLoading(null);
						}
					}
				});
	}

	@Override
	public void searchStation(String name) {
		ApiComponentHolder.apiComponent
				.solarService()
				.searchSolarStation(name)
				.take(1)
				.compose(SchedulersCompat.<SolarStationResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<SolarStationResponse>() {
					@Override
					public void onError(Throwable e) {
						e.printStackTrace();
						if (getView() != null){
							getView().showNormal();
							getView().requestFailed(null);
						}
					}

					@Override
					public void onNext(SolarStationResponse response) {
						super.onNext(response);
						getView().showNormal();
						if (response.isSuccess()){
							getView().success(response.getList());
						}else {
							getView().requestFailed(response.getMsg());
						}
					}

					@Override
					public void onStart() {
						if (getView() != null){
							getView().showLoading(null);
						}
					}
				});
	}
}
