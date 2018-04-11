package com.sojoline.energy.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.util.AppConstants;
import com.sojoline.base.util.ValidateUtils;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.base.widget.ButtonTimer;
import com.sojoline.basiclib.DebugLog;
import com.sojoline.energy.R;
import com.sojoline.model.request.CaptchaRequest;
import com.sojoline.model.request.RegisterRequest;
import com.sojoline.model.storage.AppInfoPreferences;
import com.sojoline.presenter.common.captcha.CaptchaContract;
import com.sojoline.presenter.common.captcha.CaptchaPresenter;
import com.sojoline.presenter.common.register.RegisterContract;
import com.sojoline.presenter.common.register.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *     author : 李小勇
 *     time   : 2017/09/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Route(path = "/app/register")
public class RegisterActivity extends BaseCompatActivity implements CaptchaContract.View, RegisterContract.View{

	@BindView(R.id.et_phone)
	EditText etPhone;
	@BindView(R.id.et_code)
	EditText etCode;
	@BindView(R.id.bt_sendCode)
	Button btSendCode;
	@BindView(R.id.et_psd)
	EditText etPsd;
	@BindView(R.id.et_again)
	EditText etAgain;

	private CaptchaPresenter captchaPresenter;
	private RegisterPresenter registerPresenter;
	@Override
	protected void initPresenter() {
		super.initPresenter();
		captchaPresenter = new CaptchaPresenter();
		captchaPresenter.attachView(this);
		registerPresenter = new RegisterPresenter();
		registerPresenter.attachView(this);
	}

	public static void navigation() {
		ARouter.getInstance().build("/app/register").navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_register);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		initToolbarNav(R.string.register);
	}

	@OnClick({R.id.bt_sendCode, R.id.bt_register})
	public void onViewClicked(View view) {
		String phone = etPhone.getText().toString();
		switch (view.getId()) {
			case R.id.bt_sendCode:
				if (TextUtils.isEmpty(phone)){
					showToast("请输入手机号");
					return;
				}
				if (ValidateUtils.validatePhone(phone)){
					CaptchaRequest request = new CaptchaRequest(phone, 0);
					captchaPresenter.sentCaptcha(request);
					ButtonTimer buttonTimer = new ButtonTimer(btSendCode,60 * 1000, 1000);
					buttonTimer.start();
				}else {
					showToast("手机号不正确");
				}
				break;
			case R.id.bt_register:
				if (TextUtils.isEmpty(phone)){
					showToast("请输入手机号");
					return;
				}
				if (!ValidateUtils.validatePhone(phone)){
					showToast("手机号不正确");
					return;
				}

				String code = etCode.getText().toString();
				if (TextUtils.isEmpty(code)){
					showToast("请输入验证码");
					return;
				}

				String psd = etPsd.getText().toString();
				if (TextUtils.isEmpty(psd)){
					showToast("请输入密码");
					return;
				}
				if (!ValidateUtils.validatePsd(psd)){
					showToast("密码格式不正确");
					return;
				}
				if (!psd.equals(etAgain.getText().toString())){
					showToast("两次输入的密码不相同");
					return;
				}

				int module = AppInfoPreferences.get().getModule();
				DebugLog.log("module:" + module);
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
				RegisterRequest request = new RegisterRequest(phone, psd, code, type);
				registerPresenter.register(request);
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
		if (TextUtils.isEmpty(msg)){
			showToast(R.string.network_not_available);
		}else {
			showToast(msg);
		}
	}

	@Override
	public void captchaSuccess() {

	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		captchaPresenter.detachView();
		registerPresenter.detachView();
	}

	@Override
	public void registerSuccess() {
		showToast("注册成功");
		finish();
	}
}
