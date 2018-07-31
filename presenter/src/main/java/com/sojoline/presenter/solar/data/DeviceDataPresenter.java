package com.sojoline.presenter.solar.data;

import com.sojoline.basiclib.rx.SimpleSubscriber;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.dagger.ApiComponentHolder;
import com.sojoline.model.response.BaseResponse;
import com.sojoline.model.response.CombinerDataResponse;
import com.sojoline.model.response.InverterDataResponse;
import com.sojoline.model.response.TransformerDataResponse;
import com.sojoline.model.response.MonitorDataResponse;
import rx.Subscription;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class DeviceDataPresenter extends DeviceDataContract.Presenter {
	private Subscription combinerSubscription;
	private Subscription transformerSubscription;
	private Subscription inverterSubscription;
    private Subscription mointorSubscription;
	@Override
	public void getDeviceData(String id) {
		ApiComponentHolder.apiComponent
				.solarService()
				.getDeviceData(id)
				.take(1)
				.compose(SchedulersCompat.<BaseResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<BaseResponse>() {
					@Override
					public void onError(Throwable e) {
						super.onError(e);
						e.printStackTrace();
						if (getView() != null) {
							getView().showNormal();
							getView().requestFailed(null);
						}
					}

					@Override
					public void onNext(BaseResponse response) {
						super.onNext(response);
						getView().showNormal();
						if (response.isSuccess()) {
							getView().success(null);
						} else {
							getView().requestFailed(response.getMsg());
						}
					}

					@Override
					public void onStart() {
						super.onStart();
						if (getView() != null) {
							getView().showLoading(null);
						}
					}
				});
	}

	@Override
	public void getCombinerData(String id) {
		if (combinerSubscription == null || combinerSubscription.isUnsubscribed()) {
			combinerSubscription = ApiComponentHolder.apiComponent
					.solarService()
					.getCombinerData(id)
					.take(1)
					.compose(SchedulersCompat.<CombinerDataResponse>applyNewSchedulers())
					.subscribe(new SimpleSubscriber<CombinerDataResponse>() {
						@Override
						public void onError(Throwable e) {
							super.onError(e);
							e.printStackTrace();
							if (getView() != null) {
								getView().showNormal();
								getView().requestFailed(null);
							}
						}

						@Override
						public void onNext(CombinerDataResponse response) {
							super.onNext(response);
							getView().showNormal();
							if (response.isSuccess()) {
								getView().success(response.getData());
							} else {
								getView().requestFailed(response.getMsg());
							}
						}

						@Override
						public void onStart() {
							super.onStart();
							if (getView() != null) {
								getView().showLoading(null);
							}
						}
					});
		}
	}

	@Override
	public void getTransformerData(String id) {
		if (transformerSubscription == null || transformerSubscription.isUnsubscribed()) {
			transformerSubscription = ApiComponentHolder.apiComponent
					.solarService()
					.getTransformerData(id)
					.take(1)
					.compose(SchedulersCompat.<TransformerDataResponse>applyNewSchedulers())
					.subscribe(new SimpleSubscriber<TransformerDataResponse>() {
						@Override
						public void onError(Throwable e) {
							super.onError(e);
							e.printStackTrace();
							if (getView() != null) {
								getView().showNormal();
								getView().requestFailed(null);
							}
						}

						@Override
						public void onNext(TransformerDataResponse response) {
							super.onNext(response);
							getView().showNormal();
							if (response.isSuccess()) {
								getView().success(response.getData());
							} else {
								getView().requestFailed(response.getMsg());
							}
						}

						@Override
						public void onStart() {
							super.onStart();
							if (getView() != null) {
								getView().showLoading(null);
							}
						}
					});
		}
	}

	@Override
	public void getInverterData(String id) {
		if (inverterSubscription == null || inverterSubscription.isUnsubscribed()) {
			inverterSubscription = ApiComponentHolder.apiComponent
					.solarService()
					.getInverterData(id)
					.take(1)
					.compose(SchedulersCompat.<InverterDataResponse>applyNewSchedulers())
					.subscribe(new SimpleSubscriber<InverterDataResponse>() {
						@Override
						public void onError(Throwable e) {
							super.onError(e);
							e.printStackTrace();
							if (getView() != null) {
								getView().showNormal();
								getView().requestFailed(null);
							}
						}

						@Override
						public void onNext(InverterDataResponse response) {
							super.onNext(response);
							getView().showNormal();
							if (response.isSuccess()) {
								getView().success(response.getData());
							} else {
								getView().requestFailed(response.getMsg());
							}
						}

						@Override
						public void onStart() {
							super.onStart();
							if (getView() != null) {
								getView().showLoading(null);
							}
						}
					});
		}

	}

	@Override
	public void getMonitorData(String id) {
		if (mointorSubscription == null || mointorSubscription.isUnsubscribed()) {
			mointorSubscription = ApiComponentHolder.apiComponent
					.solarService()
					.getMonitorData(id)
					.take(1)
					.compose(SchedulersCompat.<MonitorDataResponse>applyNewSchedulers())
					.subscribe(new SimpleSubscriber<MonitorDataResponse>() {
						@Override
						public void onError(Throwable e) {
							super.onError(e);
							e.printStackTrace();
							if (getView() != null) {
								getView().showNormal();
								getView().requestFailed(null);
							}
						}

						@Override
						public void onNext(MonitorDataResponse response) {
							super.onNext(response);
							getView().showNormal();
							if (response.isSuccess()) {
								getView().success(response.getData());
							} else {
								getView().requestFailed(response.getMsg());
							}
						}

						@Override
						public void onStart() {
							super.onStart();
							if (getView() != null) {
								getView().showLoading(null);
							}
						}
					});
		}

	}
}
