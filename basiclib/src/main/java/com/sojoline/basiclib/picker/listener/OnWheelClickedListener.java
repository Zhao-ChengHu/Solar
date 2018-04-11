package com.sojoline.basiclib.picker.listener;

import com.sojoline.basiclib.picker.wheel.WheelView;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface OnWheelClickedListener {
	/**
	 * Callback method to be invoked when current item clicked
	 * @param wheel the wheel view
	 * @param itemIndex the index of clicked item
	 */
	void onItemClicked(WheelView wheel, int itemIndex);
}
