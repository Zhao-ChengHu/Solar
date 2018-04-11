package com.sojoline.energy.view;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.security.MD5Helper;
import com.sojoline.base.util.AppConstants;
import com.sojoline.base.util.AppUtils;
import com.sojoline.base.util.SpfEncryption;
import com.sojoline.base.util.ValidateUtils;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.energy.R;
import com.sojoline.model.request.LoginRequest;
import com.sojoline.model.storage.AppInfoPreferences;
import com.sojoline.monitor.view.activity.MonitorStationActivity;
import com.sojoline.presenter.common.login.LoginContract;
import com.sojoline.presenter.common.login.LoginPresenter;
import com.sojoline.solar.view.activity.CollectionActivity;
import com.sojoline.solar.view.activity.StationsActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/09/25
 *     desc   : 登录界面
 *     version: 1.0
 * </pre>
 */
@Route(path = "/app/login")
public class LoginActivity extends BaseCompatActivity implements LoginContract.View{
	@BindView(R.id.et_phone)
	EditText etPhone;
	@BindView(R.id.et_psd)
	EditText etPsd;

	private LoginPresenter presenter;
	private String phoneNum;
	private String password;
	private int module;

	private boolean isPrompt;

	/**
	 *
	 * @param isPrompt {true or false} true表示账号在其他地方登录了，token发生变化，需要提示用户
	 */
	public static void navigation(boolean isPrompt){
		ARouter.getInstance().build("/app/login")
				.withBoolean("isPrompt", isPrompt)
				.navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_login);
	}

	@Override
	protected void initPresenter() {
		super.initPresenter();
		presenter = new LoginPresenter();
		presenter.attachView(this);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		isPrompt = getIntent().getBooleanExtra("isPrompt", false);
		module = AppInfoPreferences.get().getModule();
		if (isPrompt) {
			new AlertDialog.Builder(this)
					.setTitle("温馨提示")
					.setMessage("您的账号在其他地方登陆，若非本人操作请修改密码后重新登录！")
					.setNegativeButton("确定", null)
					.setCancelable(false)
					.show();
		}
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
	}

	@OnClick({R.id.tv_forgot, R.id.bt_login, R.id.bt_register})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.tv_forgot:
				ForgotActivity.navigation();
				break;
			case R.id.bt_login:
				phoneNum = etPhone.getText().toString().trim();
				password = etPsd.getText().toString().trim();
				if (TextUtils.isEmpty(phoneNum)){
					showToast("请输入手机号");
					return;
				}else if (!ValidateUtils.validatePhone(phoneNum)){
					showToast("手机号错误");
					return;
				}
				if (TextUtils.isEmpty(password)){
					showToast("请输入密码");
					return;
				}
				String type;
				if (module == AppConstants.MODULE_MONITOR){
					type = "ecc";
				}else if (module == AppConstants.MODULE_SALE){
					type = "esc";
				}else if (module == AppConstants.MODULE_SOLAR){
					type = "pvc";
				}else {
					showToast("出现错误了");
					return;
				}
				LoginRequest request = new LoginRequest(phoneNum, password, type);
				presenter.login(request);
				break;
			case R.id.bt_register:
				RegisterActivity.navigation();
				break;
			default:
				break;
		}
	}

	@Override
	public void showLoading(String msg) {

	}

	@Override
	public void showNormal() {

	}

	@Override
	public void requestFailed(String msg) {
		if (msg != null) {
			showToast(msg);
		}else {
			showToast(R.string.network_not_available);
		}
	}

	@Override
	public void loginSuccess(String token, String userId, String userType) {
		//设置秘钥
		SpfEncryption.setSecretKey(MD5Helper.getMD5String(phoneNum));
		//保存数据
		AppInfoPreferences.get().setToken(token);

		if (isPrompt){
			//需要重新开始
			SplashActivity.navigation(true);
		}else {
			turnToActivity(userType);
		}
	}

	/**
	 * 跳转界面
	 * @param userType 用户类型
	 */
	private void turnToActivity(String userType){
		if (module == AppConstants.MODULE_SOLAR){
			AppInfoPreferences.get().setModule(AppConstants.MODULE_SOLAR);
			//普通用户
			if ("consumer".equals(userType)){
				CollectionActivity.navigation();
			}else {
				//运维人员
				StationsActivity.navigation();
			}
		}else if (module == AppConstants.MODULE_SALE){
			AppInfoPreferences.get().setModule(AppConstants.MODULE_SALE);
		}else if (module == AppConstants.MODULE_MONITOR){
			AppInfoPreferences.get().setModule(AppConstants.MODULE_MONITOR);
			MonitorStationActivity.navigation();
		}else {
			SplashActivity.navigation(false);
		}
		finish();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (!AppUtils.isLogin()){
			AppInfoPreferences.get().setModule(-1);
		}
	}
}
