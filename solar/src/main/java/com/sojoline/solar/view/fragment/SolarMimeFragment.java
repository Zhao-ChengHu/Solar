package com.sojoline.solar.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.util.ActivityManager;
import com.sojoline.base.view.BaseMimeFragment;
import com.sojoline.model.storage.AppInfoPreferences;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;
import com.sojoline.solar.view.activity.CollectionActivity;
import com.sojoline.solar.view.activity.WarningListActivity;

import butterknife.OnClick;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/10/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarMimeFragment extends BaseMimeFragment {

	public static SolarMimeFragment newInstance() {
		Bundle args = new Bundle();
		SolarMimeFragment fragment = new SolarMimeFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.solar_fragment_mime, container, false);
	}

	@Override
	protected void initView(@Nullable Bundle savedInstanceState) {
		super.initView(savedInstanceState);
	}

	@OnClick({R2.id.tv_station, R2.id.tv_warning, R2.id.tv_switch})
	public void onViewClicked(View view) {
		int i = view.getId();
		if (i == R.id.tv_station) {
			//我的电站
			CollectionActivity.navigation();
		} else if (i == R.id.tv_warning) {
			//告警列表
			WarningListActivity.navigation();
		} else if (i == R.id.tv_switch) {
			//切换应用
			AppInfoPreferences.get().setStationId(null);
			ActivityManager.getInstance().popAll();
			String splashPath = "/app/splash";
			ARouter.getInstance().build(splashPath).withBoolean("isAnimated", true).navigation();
		}
	}
}
