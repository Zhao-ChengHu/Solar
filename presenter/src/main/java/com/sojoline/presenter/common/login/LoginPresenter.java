package com.sojoline.presenter.common.login;

import com.sojoline.basiclib.rx.SimpleSubscriber;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.dagger.ApiComponentHolder;
import com.sojoline.model.request.LoginRequest;
import com.sojoline.model.response.LoginResponse;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class LoginPresenter extends LoginContract.Presenter {

	@Override
	public void login(LoginRequest request) {
		ApiComponentHolder.apiComponent
				.commonService()
				.login(request)
				.compose(SchedulersCompat.<LoginResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<LoginResponse>() {
					@Override
					public void onError(Throwable e) {
						if (getView() != null){
							getView().showNormal();
							getView().requestFailed(null);
						}
					}

					@Override
					public void onNext(LoginResponse loginResponse) {
						super.onNext(loginResponse);
						getView().showNormal();
						if (loginResponse.isSuccess()){
							getView().loginSuccess(loginResponse.content.token, loginResponse.content.user_id,
									loginResponse.content.userType);
						}else {
							getView().requestFailed(loginResponse.getMsg());
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
