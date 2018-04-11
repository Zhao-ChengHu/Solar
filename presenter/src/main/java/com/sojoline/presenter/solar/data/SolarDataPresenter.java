package com.sojoline.presenter.solar.data;

import com.sojoline.basiclib.rx.SimpleSubscriber;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.bean.solar.SolarData;
import com.sojoline.model.dagger.ApiComponentHolder;
import com.sojoline.model.response.SolarDataResponse;

import java.util.List;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarDataPresenter extends SolarDataContract.Presenter {
	@Override
	public void getSolarStationData(String id) {
		ApiComponentHolder.apiComponent
				.solarService()
				.getStationData(id)
				.take(1)
				.compose(SchedulersCompat.<SolarDataResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<SolarDataResponse>() {
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
					public void onNext(SolarDataResponse response) {
						super.onNext(response);
						getView().showNormal();
						if (response.isSuccess()){
							List<SolarData> list = response.getList();
							if (list.size() > 0){
								getView().solarDataSuccess(list.get(0));
							}else {
								getView().solarDataSuccess(null);
							}

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
