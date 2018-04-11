package com.sojoline.monitor.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.util.ActivityManager;
import com.sojoline.base.view.BaseMimeFragment;
import com.sojoline.model.storage.AppInfoPreferences;
import com.sojoline.monitor.R;
import com.sojoline.monitor.R2;
import com.sojoline.monitor.view.activity.MonitorStationActivity;

import butterknife.OnClick;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MonitorMimeFragment extends BaseMimeFragment {

	public static MonitorMimeFragment newInstance() {
		Bundle args = new Bundle();
		MonitorMimeFragment fragment = new MonitorMimeFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.monitor_fragment_mime, container, false);
	}

	@OnClick({R2.id.tv_station, R2.id.tv_switch})
	public void onViewClicked(View view) {
		int i = view.getId();
		if (i == R.id.tv_station) {
			//我的电站
			MonitorStationActivity.navigation();
		} else if (i == R.id.tv_switch) {
			//切换应用
			AppInfoPreferences.get().setStationId(null);
			ActivityManager.getInstance().popAll();
			String splashPath = "/app/splash";
			ARouter.getInstance().build(splashPath).withBoolean("isAnimated", true).navigation();
		}
	}
}
