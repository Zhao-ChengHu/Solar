package com.sojoline.energy.view;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.energy.R;

import butterknife.BindView;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Route(path = "/app/about")
public class AboutActivity extends BaseCompatActivity {
	@BindView(R.id.tv_version)
	TextView tvVersion;

	public static void navigation(){
		ARouter.getInstance().build("/app/about").navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_about);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		initToolbarNav("关于");

		try {
			PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
			String version = info.versionName;
			tvVersion.setText("V" + version);
		} catch (PackageManager.NameNotFoundException e) {
			tvVersion.setText("V1.0.0");
		}
	}


}
