package com.sojoline.presenter.common.password;

import com.sojoline.basiclib.rx.SimpleSubscriber;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.dagger.ApiComponentHolder;
import com.sojoline.model.response.BaseResponse;

import java.util.HashMap;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class PsdPresenter extends PsdContract.Presenter {
	@Override
	public void forgetPsd(HashMap<String, String> map) {
		ApiComponentHolder.apiComponent.commonService()
				.forgetPsd(map)
				.take(1)
				.compose(SchedulersCompat.<BaseResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<BaseResponse>() {
					@Override
					public void onError(Throwable e) {
						super.onError(e);
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
							getView().success();
						}else {
							getView().requestFailed(baseResponse.getMsg());
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

	@Override
	public void resetPsd(HashMap<String, String> map) {
		ApiComponentHolder.apiComponent.commonService()
				.resetPsd(map)
				.take(1)
				.compose(SchedulersCompat.<BaseResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<BaseResponse>() {
					@Override
					public void onError(Throwable e) {
						super.onError(e);
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
							getView().success();
						}else {
							getView().requestFailed(baseResponse.getMsg());
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
