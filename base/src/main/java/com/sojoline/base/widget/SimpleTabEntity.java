package com.sojoline.base.widget;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/10/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SimpleTabEntity implements CustomTabEntity {
	private String title;
	private int selectedIcon;
	private int unselectedIcon;

	public SimpleTabEntity(String title, int selectedIcon, int unselectedIcon) {
		this.title = title;
		this.selectedIcon = selectedIcon;
		this.unselectedIcon = unselectedIcon;
	}

	@Override
	public String getTabTitle() {
		return title;
	}

	@Override
	public int getTabSelectedIcon() {
		return selectedIcon;
	}

	@Override
	public int getTabUnselectedIcon() {
		return unselectedIcon;
	}
}
