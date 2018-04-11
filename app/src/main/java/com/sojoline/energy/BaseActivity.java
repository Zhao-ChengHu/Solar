package com.sojoline.energy;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sojoline.base.view.BaseCompatActivity;

import butterknife.BindView;

/**
 * <pre>
 *     author : 李小勇
 *     time   : 2017/10/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public abstract class BaseActivity extends BaseCompatActivity {
	@Nullable
	@BindView(com.sojoline.base.R.id.toolbar)
	Toolbar toolbar;

	/**
	 * --------------
	 * Toolbar Settings
	 * --------------
	 */
	@Override
	protected void initToolbarNav(int resId) {
		initToolbarNav(getString(resId), null);
	}

	@Override
	protected void initToolbarNav(String title) {
		initToolbarNav(title, null);
	}

	@Override
	protected void initToolbarNav(String title, View.OnClickListener listener) {
		toolbar.setTitle(title);
		toolbar.setTitleTextColor(Color.WHITE);
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

	@Override
	protected void initToolbarNavBlack(String title, View.OnClickListener listener) {
		toolbar.setTitle(title);
		setSupportActionBar(toolbar);
		toolbar.setNavigationIcon(ContextCompat.getDrawable(this,R.mipmap.ic_back));
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
}
