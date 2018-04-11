package com.sojoline.presenter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public abstract class BasePresenter<R> implements IBasePresenter<R> {
	private WeakReference<R> wr;
	private R view;
	private List<Subscription> list;

	@Override
	public void attachView(R view) {
		this.view = view;
		wr = new WeakReference<>(view);
		list = new ArrayList<>();
	}

	@Override
	public void detachView() {
		wr.clear();
		clearSubscription();
	}

	public R getView(){
		if (view == null) {
			view = wr.get();
		}
		return view;
	}

	public void addSubscription(Subscription subscription){
		list.add(subscription);
	}

	private void clearSubscription(){
		for (Subscription s : list){
			if (s != null && !s.isUnsubscribed()){
				s.unsubscribe();
			}
		}
	}
}
