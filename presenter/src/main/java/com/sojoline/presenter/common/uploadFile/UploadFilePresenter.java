package com.sojoline.presenter.common.uploadFile;

import com.sojoline.basiclib.rx.SimpleSubscriber;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.dagger.ApiComponentHolder;
import com.sojoline.model.response.UploadFileResponse;

import okhttp3.RequestBody;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class UploadFilePresenter extends UploadFileContract.Presenter{
	@Override
	public void uploadImage(RequestBody request) {
		ApiComponentHolder.apiComponent.commonService()
				.uploadImage(request)
				.take(1)
				.compose(SchedulersCompat.<UploadFileResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<UploadFileResponse>() {
					@Override
					public void onError(Throwable e) {
						super.onError(e);
						if (getView() != null){
							getView().showNormal();
							getView().uploadFileFailed();
						}
					}

					@Override
					public void onNext(UploadFileResponse uploadFileResponse) {
						getView().showNormal();
						if (uploadFileResponse.isSuccess()){
							getView().uploadFileSuccess(uploadFileResponse.getUrl());
						}else {
							getView().uploadFileFailed();
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
