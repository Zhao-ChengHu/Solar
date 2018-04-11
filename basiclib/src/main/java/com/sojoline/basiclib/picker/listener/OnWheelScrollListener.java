package com.sojoline.basiclib.picker.listener;

import com.sojoline.basiclib.picker.wheel.WheelView;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/01
 *     desc   :	Wheel scrolled listener interface.
 *     version: 1.0
 * </pre>
 */

public interface OnWheelScrollListener {
	/**
	 * Callback method to be invoked when scrolling started.
	 * @param wheel the wheel view whose state has changed.
	 */
	void onScrollingStarted(WheelView wheel);

	/**
	 * Callback method to be invoked when scrolling ended.
	 * @param wheel the wheel view whose state has changed.
	 */
	void onScrollingFinished(WheelView wheel);
}
