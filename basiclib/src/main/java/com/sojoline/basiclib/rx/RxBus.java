package com.sojoline.basiclib.rx;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class RxBus {
	private static RxBus instance = null;

	private Subject<Object, Object> rxStandardBus;

	public RxBus(){
		rxStandardBus = new SerializedSubject<>(PublishSubject.create());
	}

	public static RxBus getInstance(){
	    if(instance == null){
	        synchronized(RxBus.class){
	            if(instance == null){
	                instance = new RxBus();
	            }
	        }
	    }
	    return instance;
	}

	public void postEvent(Object event){
		if (hasObservers()){
			rxStandardBus.onNext(event);
		}
	}

	public <T> Observable<T> toObservable(Class<T> clazz){
		return rxStandardBus.asObservable().onBackpressureBuffer().ofType(clazz);
	}

	private boolean hasObservers(){
		return rxStandardBus.hasObservers();
	}
}
