package com.sojoline.base;

import com.sojoline.base.view.BaseCompatActivity;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class StartActivityEvent {
	private Class<? extends BaseCompatActivity> activity;

	public StartActivityEvent(Class<? extends BaseCompatActivity> activity) {
		this.activity = activity;
	}

	public Class<? extends BaseCompatActivity> getActivity() {
		return activity;
	}
}
