package com.sojoline.presenter.monitor.collect;

import com.sojoline.basiclib.rx.SimpleSubscriber;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.dagger.ApiComponentHolder;
import com.sojoline.model.response.BaseResponse;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class CollectMonitorPresenter extends CollectMonitorContract.Presenter {
	@Override
	public void collectMonitorStation(String id) {
		ApiComponentHolder.apiComponent
				.monitorService()
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
	public void uncollectMonitorStation(String id) {
		ApiComponentHolder.apiComponent
				.monitorService()
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
