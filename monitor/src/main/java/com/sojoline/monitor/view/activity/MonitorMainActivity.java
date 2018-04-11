package com.sojoline.monitor.view.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.util.AppUtils;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.monitor.R;
import com.sojoline.monitor.view.fragment.MonitorMainFragment;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Route(path = "/monitor/login/main")
public class MonitorMainActivity extends BaseCompatActivity {
	public static void navigation(){
		ARouter.getInstance().build("/monitor/login/main").navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.monitor_activity_main);
		loadRootFragment(R.id.fragment_container, MonitorMainFragment.newInstance());
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		AppUtils.appUpdate(this);
	}
}
