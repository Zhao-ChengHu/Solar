package com.sojoline.presenter.solar.data;

import com.sojoline.basiclib.rx.SimpleSubscriber;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.dagger.ApiComponentHolder;
import com.sojoline.model.response.WarningResponse;

import java.util.HashMap;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarWarningPresenter extends SolarWarningContract.Presenter {
	@Override
	public void getWarningList(HashMap<String, Object> request) {
		ApiComponentHolder.apiComponent
				.solarService()
				.getWarningList(request)
				.take(1)
				.compose(SchedulersCompat.<WarningResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<WarningResponse>() {
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
					public void onNext(WarningResponse response) {
						super.onNext(response);
						getView().showNormal();
						if (response.isSuccess()){
							getView().success(response.getContent());
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
