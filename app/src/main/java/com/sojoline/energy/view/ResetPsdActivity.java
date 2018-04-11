package com.sojoline.energy.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.util.ValidateUtils;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.energy.R;
import com.sojoline.presenter.common.password.PsdContract;
import com.sojoline.presenter.common.password.PsdPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Route(path = "/app/login/reset")
public class ResetPsdActivity extends BaseCompatActivity implements PsdContract.View{

	@BindView(R.id.et_psd_old)
	EditText etPsdOld;
	@BindView(R.id.et_psd_new)
	EditText etPsdNew;
	@BindView(R.id.et_psd_again)
	EditText etPsdAgain;

	private PsdPresenter presenter;

	public static void navigation() {
		ARouter.getInstance().build("/app/login/reset").navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_reset);
	}

	@Override
	protected void initPresenter() {
		super.initPresenter();
		presenter = new PsdPresenter();
		presenter.attachView(this);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		initToolbarNav("修改密码");
	}

	@OnClick(R.id.bt_change)
	public void onViewClicked() {
		String oldPsd = etPsdOld.getText().toString();
		String newPsd = etPsdNew.getText().toString();
		if (TextUtils.isEmpty(oldPsd)){
			showToast("请输入旧密码");
			return;
		}

		if (TextUtils.isEmpty(newPsd)){
			showToast("请输入新密码");
			return;
		}
		if (!ValidateUtils.validatePsd(newPsd)){
			showToast("新密码格式不正确");
			return;
		}
		if (!newPsd.equals(etPsdAgain.getText().toString())){
			showToast("两次输入的密码不相同");
			return;
		}
		HashMap<String, String> map = new HashMap<>();
		map.put("NewPassword", newPsd);
		map.put("OldPassword", oldPsd);
		presenter.resetPsd(map);
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
		showToast("修改成功");
		finish();
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
	}
}
