package com.sojoline.presenter.common.user;

import com.sojoline.basiclib.rx.SimpleSubscriber;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.dagger.ApiComponentHolder;
import com.sojoline.model.response.BaseResponse;
import com.sojoline.model.response.UserInfoResponse;

import java.util.HashMap;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class UserPresenter extends UserContract.Presenter {
	@Override
	public void getUserInfo() {
		ApiComponentHolder.apiComponent
				.commonService()
				.getUserInfo()
				.compose(SchedulersCompat.<UserInfoResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<UserInfoResponse>() {
					@Override
					public void onError(Throwable e) {
						if (getView() != null){
							getView().showNormal();
							getView().requestFailed(null);
						}
					}

					@Override
					public void onNext(UserInfoResponse response) {
						getView().showNormal();
						if (response.isSuccess()){
							getView().success(response.user);
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
	public void changeUserHeader(HashMap<String, String> map) {
		ApiComponentHolder.apiComponent.commonService()
				.updatedUserHeader(map)
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
							getView().changeHeaderSuccess();
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
	public void changeNick(HashMap<String, String> map) {
		ApiComponentHolder.apiComponent.commonService()
				.updateNickname(map)
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
							getView().changeNickSuccess();
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
	public void changeUserInfo(HashMap<String, String> map) {
		ApiComponentHolder.apiComponent.commonService()
				.updateUserInfo(map)
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
							getView().changeInfoSuccess();
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
