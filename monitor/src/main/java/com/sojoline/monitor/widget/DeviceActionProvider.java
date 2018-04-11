package com.sojoline.monitor.widget;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class DeviceActionProvider extends android.support.v4.view.ActionProvider {
	/**
	 * Creates a new instance. ActionProvider classes should always implement a
	 * constructor that takes a single Context parameter for inflating from menu XML.
	 *
	 * @param context Context for accessing resources.
	 */
	public DeviceActionProvider(Context context) {
		super(context);
	}

	@Override
	public View onCreateActionView() {
		return null;
	}

	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		subMenu.clear();
		subMenu.add("箱变")
				.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Log.i("TAG", "onMenuItemClick: 箱变");
						return true;
					}
				});
		subMenu.add("逆变器")
				.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Log.i("TAG", "onMenuItemClick: 逆变器");
						return true;
					}
				});
		subMenu.add("汇流箱")
				.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Log.i("TAG", "onMenuItemClick: 汇流箱");
						return true;
					}
				});
	}

	@Override
	public boolean hasSubMenu() {
		return true;
	}
}
