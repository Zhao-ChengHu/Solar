package com.sojoline.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sojoline.base.R;
import com.sojoline.base.util.AppUtils;
import com.sojoline.base.util.GlideLoader;
import com.sojoline.model.bean.common.User;
import com.sojoline.model.storage.AppInfoPreferences;
import com.sojoline.presenter.common.user.UserContract;
import com.sojoline.presenter.common.user.UserPresenter;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public abstract class BaseMimeFragment extends BaseFragment implements View.OnClickListener,UserContract.View{
	private TextView tvNick;
	private ImageView ivPhoto;
	private ImageButton ibSetting;

	private UserPresenter presenter;

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		tvNick = (TextView) view.findViewById(R.id.tv_nick);
		tvNick.setOnClickListener(this);
		ivPhoto = (ImageView) view.findViewById(R.id.iv_photo);
		ivPhoto.setOnClickListener(this);
		ibSetting = (ImageButton) view.findViewById(R.id.ib_setting);
		ibSetting.setOnClickListener(this);
	}

	@Override
	protected void initPresenter() {
		super.initPresenter();
		presenter = new UserPresenter();
		presenter.attachView(this);
	}

	@Override
	public void onSupportVisible() {
		super.onSupportVisible();
		User user = AppInfoPreferences.get().getUser();
		if (user == null){
			presenter.getUserInfo();
		}else {
			String nick = user.getNickname();
			String imgUrl = user.getHeader();
			String username = user.getUsername();
			if (TextUtils.isEmpty(nick)){
				tvNick.setText(username);
			}else {
				tvNick.setText(nick);
			}
			if (!TextUtils.isEmpty(imgUrl)){
				GlideLoader.getInstance().displayCircleImage(getContext(), imgUrl, ivPhoto);
			}
		}
	}

	@Override
	public void onClick(View v) {
		if (v == tvNick || v== ivPhoto){
			//跳转用户信息界面
			String userPath = "/app/login/user_info";
			AppUtils.startActivity(userPath);
		}else if (v == ibSetting){
			//跳转设置界面
			String settingPath = "/app/login/setting";
			AppUtils.startActivity(settingPath);
		}
	}

	@Override
	public void success(User user) {
		if (user != null) {
			// 保存用户信息
			AppInfoPreferences.get().setUser(user);
			String nick = user.getNickname();
			String imgUrl = user.getHeader();
			if (TextUtils.isEmpty(nick)){
				tvNick.setText(user.getUsername());
			}else {
				tvNick.setText(nick);
			}
			if (!TextUtils.isEmpty(imgUrl)){
				GlideLoader.getInstance().displayCircleImage(getContext(), imgUrl, ivPhoto);
			}
		}
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
	}

	@Override
	public void changeHeaderSuccess() {

	}

	@Override
	public void changeInfoSuccess() {

	}

	@Override
	public void changeNickSuccess() {

	}

	@Override
	public void showLoading(String msg) {

	}

	@Override
	public void showNormal() {

	}

	@Override
	public void requestFailed(String msg) {

	}
}
