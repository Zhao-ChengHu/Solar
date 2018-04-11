package com.sojoline.energy.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.util.ActivityManager;
import com.sojoline.base.util.AppUtils;
import com.sojoline.base.util.CacheDataManager;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.energy.R;
import com.sojoline.model.storage.DaoSessionHolder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/22
 *     desc   : 设置界面
 *     version: 1.0
 * </pre>
 */
@Route(path = "/app/login/setting")
public class SettingActivity extends BaseCompatActivity {
	@BindView(R.id.tv_cacheSize)
	TextView tvCacheSize;

	public static void navigation() {
		ARouter.getInstance().build("/app/login/setting").navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_setting);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		initToolbarNav("设置");
		try {
			tvCacheSize.setText(CacheDataManager.getTotalCacheSize(this));
		} catch (Exception e) {
			showToast("获取缓存失败");
		}
	}

	@OnClick({R.id.tv_change_psd, R.id.tv_update, R.id.tv_suggest, R.id.tv_service, R.id.tv_about,
			R.id.bt_exit, R.id.ll_clean})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.tv_change_psd:
				//修改密码
				ResetPsdActivity.navigation();
				break;
			case R.id.tv_update:
				break;
			case R.id.tv_suggest:
				SuggestActivity.navigation();
				break;
			case R.id.tv_service:
				//客服
				callService();
				break;
			case R.id.tv_about:
				//关于
				AboutActivity.navigation();
				break;
			case R.id.bt_exit:
				//清除用户数据
				AppUtils.clearUserData();
				//弹出所以activity
				ActivityManager.getInstance().popAll();
				//跳转到引导界面
				SplashActivity.navigation(true);
				finish();
				break;
			case R.id.ll_clean:
				showDialog();
				break;
			default:
				break;
		}
	}

	/**
	 * 拨打客服电话
	 * tel:400-679-9948
	 */
	private void callService() {
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		if (tm != null && tm.getSimState() == TelephonyManager.SIM_STATE_READY){
			String call = "400-679-9948";
			Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + call));
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}else {
			showToast("不支持拨打电话");
		}
	}

	/**
	 * 清除缓存dialog
	 */
	private void showDialog(){
		new AlertDialog.Builder(this)
				.setMessage("确定清除缓存吗？")
				.setNegativeButton("取消", null)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						clearCache();
						clearDatabaseData();
					}
				})
				.show();
	}

	/**
	 * 清除缓存
	 */
	private void clearCache(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				CacheDataManager.clearAllCache(SettingActivity.this);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					if (CacheDataManager.getTotalCacheSize(SettingActivity.this).startsWith("0")){
						handler.sendEmptyMessage(0);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * 清除数据库中所有表的数据
	 */
	private void clearDatabaseData(){
		DaoSessionHolder.daoSession.getDateElectricDao().deleteAll();
		DaoSessionHolder.daoSession.getDBElectricDao().deleteAll();
	}

	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			showToast("清除成功");
			try {
				tvCacheSize.setText(CacheDataManager.getTotalCacheSize(SettingActivity.this));
			} catch (Exception e) {
				tvCacheSize.setText("0B");
			}
		}
	};
}
