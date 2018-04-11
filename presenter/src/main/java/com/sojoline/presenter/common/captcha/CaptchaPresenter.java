package com.sojoline.presenter.common.captcha;

import com.sojoline.basiclib.rx.SimpleSubscriber;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.dagger.ApiComponentHolder;
import com.sojoline.model.request.CaptchaRequest;
import com.sojoline.model.response.BaseResponse;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class CaptchaPresenter extends CaptchaContract.Presenter {
	@Override
	public void sentCaptcha(CaptchaRequest request) {
		ApiComponentHolder.apiComponent.commonService()
				.sendCaptcha(request)
				.take(1)
				.compose(SchedulersCompat.<BaseResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<BaseResponse>() {
					@Override
					public void onError(Throwable e) {
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
							getView().captchaSuccess();
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
