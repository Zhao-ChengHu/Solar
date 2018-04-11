package com.sojoline.presenter.solar.collect;

import com.sojoline.basiclib.rx.SimpleSubscriber;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.dagger.ApiComponentHolder;
import com.sojoline.model.response.BaseResponse;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class CollectSolarPresenter extends CollectSolarContract.Presenter {
	@Override
	public void collectSolarStation(String id) {
		ApiComponentHolder.apiComponent
				.solarService()
				.collectStation(id)
				.take(1)
				.compose(SchedulersCompat.<BaseResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<BaseResponse>() {
					@Override
					public void onError(Throwable e) {
						e.printStackTrace();
						if (getView() != null){
							getView().showNormal();
							getView().requestFailed(null);
						}
					}

					@Override
					public void onNext(BaseResponse baseResponse) {
						super.onNext(baseResponse);
						getView().showNormal();
						if (baseResponse.isSuccess()){
							getView().collectSuccess();
						}else {
							getView().requestFailed(baseResponse.getMsg());
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
	public void uncollectSolarStation(String id) {
		ApiComponentHolder.apiComponent
				.solarService()
				.uncollectStation(id)
				.take(1)
				.compose(SchedulersCompat.<BaseResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<BaseResponse>() {
					@Override
					public void onError(Throwable e) {
						e.printStackTrace();
						if (getView() != null){
							getView().showNormal();
							getView().requestFailed(null);
						}
					}

					@Override
					public void onNext(BaseResponse baseResponse) {
						super.onNext(baseResponse);
						getView().showNormal();
						if (baseResponse.isSuccess()){
							getView().uncollectSuccess();
						}else {
							getView().requestFailed(baseResponse.getMsg());
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
