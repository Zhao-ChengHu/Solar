package com.sojoline.energy.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.util.ValidateUtils;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.base.widget.ButtonTimer;
import com.sojoline.energy.R;
import com.sojoline.model.request.CaptchaRequest;
import com.sojoline.presenter.common.captcha.CaptchaContract;
import com.sojoline.presenter.common.captcha.CaptchaPresenter;
import com.sojoline.presenter.common.password.PsdContract;
import com.sojoline.presenter.common.password.PsdPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *     author : 李小勇
 *     time   : 2017/09/25
 *     desc   : 忘记密码界面
 *     version: 1.0
 * </pre>
 */
@Route(path = "/app/forgot")
public class ForgotActivity extends BaseCompatActivity implements PsdContract.View, CaptchaContract.View{

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

	private PsdPresenter presenter;
	private CaptchaPresenter captchaPresenter;

	@Override
	protected void initPresenter() {
		super.initPresenter();
		presenter = new PsdPresenter();
		presenter.attachView(this);
		captchaPresenter = new CaptchaPresenter();
		captchaPresenter.attachView(this);
	}

	public static void navigation() {
		ARouter.getInstance().build("/app/forgot").navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_forgot);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		initToolbarNav(R.string.forgot_psd);
	}

	@OnClick({R.id.bt_sendCode, R.id.bt_change})
	public void onViewClicked(View view) {
		String phone = etPhone.getText().toString();
		switch (view.getId()) {
			case R.id.bt_sendCode:
				if (TextUtils.isEmpty(phone)){
					showToast("请输入手机号");
					return;
				}
				//验证手机号，成功后发送验证码，button倒计时
				if (ValidateUtils.validatePhone(phone)){
					CaptchaRequest request = new CaptchaRequest(phone, 1);
					captchaPresenter.sentCaptcha(request);
					ButtonTimer buttonTimer = new ButtonTimer(btSendCode,60 * 1000, 1000);
					buttonTimer.start();
				}else {
					showToast("手机号不正确");
				}
				break;
			case R.id.bt_change:
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
					showToast("请输入新密码");
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

				HashMap<String, String> map = new HashMap<>();
				map.put("NewPassword", psd);
				map.put("phone", phone);
				map.put("code", code);
				presenter.forgetPsd(map);
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
	public void success() {
		showToast("设置密码成功");
		finish();
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
		captchaPresenter.detachView();
	}

	@Override
	public void captchaSuccess() {

	}
}
