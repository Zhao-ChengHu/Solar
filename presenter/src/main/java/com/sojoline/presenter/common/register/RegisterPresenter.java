package com.sojoline.presenter.common.register;

import com.sojoline.basiclib.rx.SimpleSubscriber;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.dagger.ApiComponentHolder;
import com.sojoline.model.request.RegisterRequest;
import com.sojoline.model.response.RegisterResponse;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class RegisterPresenter extends RegisterContract.Presenter {

	@Override
	public void register(RegisterRequest request) {
		ApiComponentHolder.apiComponent.commonService()
				.register(request)
				.take(1)
				.compose(SchedulersCompat.<RegisterResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<RegisterResponse>() {
					@Override
					public void onError(Throwable e) {
						super.onError(e);
						if (getView() != null){
							getView().showNormal();
							getView().requestFailed(null);
						}
					}

					@Override
					public void onNext(RegisterResponse registerResponse) {
						super.onNext(registerResponse);
						getView().showNormal();
						if (registerResponse.isSuccess()){
							getView().registerSuccess();
						}else {
							getView().requestFailed(registerResponse.getMsg());
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
