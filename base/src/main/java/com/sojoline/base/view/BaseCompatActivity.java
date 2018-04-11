package com.sojoline.base.view;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sojoline.base.R;
import com.sojoline.base.toast.IToast;
import com.sojoline.base.toast.ToastUtils;
import com.sojoline.base.util.ActivityManager;
import com.sojoline.base.util.Utils;
import com.sojoline.base.widget.LoadingDialog;
import com.sojoline.fragmentation.SupportActivity;

import butterknife.ButterKnife;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/09/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public abstract class BaseCompatActivity extends SupportActivity{
	private Toolbar toolbar;
	private Dialog loadingDialog;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.AppTheme_NoActionBar);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		ActivityManager.getInstance().pushActivity(this);
		setContentView(savedInstanceState);
		ButterKnife.bind(this);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		initPresenter();
		initView(savedInstanceState);
	}

	/**
	 * 加载布局
	 * @param savedInstanceState
	 */
	protected abstract void setContentView(Bundle savedInstanceState);

	/**
	 * 初始化
	 * @param savedInstanceState
	 */
	protected void initView(Bundle savedInstanceState) {
	}

	/**
	 * 初始化presenter
	 * 数据请求
	 */
	protected void initPresenter() {
	}

	/**
	 * 关闭presenter
	 * 数据请求
	 */
	protected void destroyPresenter() {

	}

	/**
	 * show toast
	 * @param text 文本
	 */
	protected void showToast(CharSequence text) {
		ToastUtils.getInstance(Utils.getContext()).makeTextShow(text, IToast.LENGTH_SHORT);
	}

	/**
	 * show toast
	 * @param resId 资源id
	 */
	protected void showToast(@StringRes int resId) {
		ToastUtils.getInstance(Utils.getContext()).makeTextShow(resId, IToast.LENGTH_SHORT);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		destroyPresenter();
		ActivityManager.getInstance().popActivity(this);
	}

	/**
	 * --------------
	 * Toolbar Settings
	 * --------------
	 */
	protected void initToolbarNav(@StringRes int resId) {
		initToolbarNav(getString(resId), null);
	}

	protected void initToolbarNav(String title) {
		initToolbarNav(title, null);
	}

	protected void initToolbarNav(String title, View.OnClickListener listener) {
		if (toolbar == null){
			return;
		}
		toolbar.setTitle(title);
		setSupportActionBar(toolbar);
		if (listener == null) {
			listener = new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			};
		}
		toolbar.setNavigationOnClickListener(listener);
	}

	protected void initToolbarNavBlack(String title, View.OnClickListener listener) {
		if (toolbar == null){
			return;
		}
		toolbar.setTitle(title);
		setSupportActionBar(toolbar);
		if (listener == null) {
			listener = new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			};
		}
		toolbar.setNavigationOnClickListener(listener);
	}

	/**
	 * 显示加载dialog
	 */
	protected void showLoadingDialog(){
		if (loadingDialog == null) {
			loadingDialog = LoadingDialog.createDialog(this);
		}
		if (!loadingDialog.isShowing()){
			loadingDialog.show();
		}
	}

	/**
	 * 关闭加载dialog
	 */
	protected void dismissLoadingDialog(){
		if (loadingDialog != null && loadingDialog.isShowing()){
			loadingDialog.dismiss();
			loadingDialog = null;
		}
	}
}
