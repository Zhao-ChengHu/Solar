package com.sojoline.base.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sojoline.base.toast.IToast;
import com.sojoline.base.toast.ToastUtils;
import com.sojoline.base.util.Utils;
import com.sojoline.base.widget.LoadingDialog;
import com.sojoline.fragmentation.SupportFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/09/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public abstract class BaseFragment extends SupportFragment {
	private Dialog loadingDialog;
	private Unbinder unbinder;
	protected View view;
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = createView(inflater, container, savedInstanceState);
		unbinder = ButterKnife.bind(this, view);
		initPresenter();
		initView(savedInstanceState);
		return view;
	}

	/**
	 * 加载布局
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 */
	protected abstract View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

	protected void initPresenter() {
	}

	protected void initView(@Nullable Bundle savedInstanceState) {

	}

	protected void destroyPresenter() {
	}

	protected void showToast(CharSequence text) {
		ToastUtils.getInstance(Utils.getContext()).makeTextShow(text, IToast.LENGTH_SHORT);
	}

	protected void showToast(@StringRes int resId) {
		ToastUtils.getInstance(Utils.getContext()).makeTextShow(resId, IToast.LENGTH_SHORT);
	}

	/**
	 * 显示加载dialog
	 */
	protected void showLoadingDialog(){
		if (loadingDialog == null) {
			loadingDialog = LoadingDialog.createDialog(_mActivity);
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

	@Override
	public void onDestroy() {
		super.onDestroy();
		_mActivity = null;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (unbinder != null) {
			unbinder.unbind();
		}
		destroyPresenter();
	}
}
