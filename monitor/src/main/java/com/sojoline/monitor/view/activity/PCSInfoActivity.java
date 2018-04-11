package com.sojoline.monitor.view.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.monitor.R;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Route(path = "/monitor/login/pcs_info")
public class PCSInfoActivity extends BaseCompatActivity {

	public static void navigation(){
		ARouter.getInstance().build("/monitor/login/pcs_info").navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.monitor_activity_pcs_info);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		initToolbarNav("PCS信息");
	}
}
