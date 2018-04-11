package com.sojoline.monitor.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sojoline.base.view.BaseFragment;
import com.sojoline.monitor.R;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MonitorLoadFragment extends BaseFragment {

	public static MonitorLoadFragment newInstance() {
		Bundle args = new Bundle();
		MonitorLoadFragment fragment = new MonitorLoadFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.monitor_fragment_load, container, false);
	}
}
