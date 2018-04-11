package com.sojoline.presenter.common.uploadFile;

import com.sojoline.presenter.BasePresenter;
import com.sojoline.presenter.IBaseView;

import okhttp3.RequestBody;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface UploadFileContract {
	interface View extends IBaseView{
		void uploadFileSuccess(String url);
		void uploadFileFailed();
	}

	abstract class Presenter extends BasePresenter<View>{
		/**
		 * 上传图片
		 * @param request
		 */
		public abstract void uploadImage(RequestBody request);
	}
}
