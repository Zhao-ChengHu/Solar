package com.sojoline.basiclib.rx;

import rx.Subscriber;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public abstract class SimpleSubscriber<T> extends Subscriber<T>{
	@Override
	public void onCompleted() {

	}

	@Override
	public void onError(Throwable e) {
		e.printStackTrace();
	}

	@Override
	public void onNext(T t) {
	}

}
