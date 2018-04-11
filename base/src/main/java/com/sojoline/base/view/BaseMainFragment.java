package com.sojoline.base.view;

import com.sojoline.base.R;

/**
 * <pre>
 *     author : 李小勇
 *     time   : 2017/10/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public abstract class BaseMainFragment extends BaseFragment {
	// 再点一次退出程序时间设置
	private static final long WAIT_TIME  = 2000L;
	private              long TOUCH_TIME = 0;

	/**
	 * 处理回退事件
	 */
	@Override
	public boolean onBackPressedSupport() {
		if (beforeBack()) {
			return true;
		}
		if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
			_mActivity.finish();
		} else {
			TOUCH_TIME = System.currentTimeMillis();
			showToast(R.string.press_again_exit);
		}
		return true;
	}

	protected boolean beforeBack() {
		return false;
	}
}
