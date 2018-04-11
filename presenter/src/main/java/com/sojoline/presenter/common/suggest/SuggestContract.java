package com.sojoline.presenter.common.suggest;

import com.sojoline.presenter.BasePresenter;
import com.sojoline.presenter.IBaseView;

import java.util.HashMap;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface SuggestContract {
	interface View extends IBaseView{
		void success();
	}

	abstract class Presenter extends BasePresenter<View>{
		public abstract void suggest(HashMap<String,Object> map);
	}
}
