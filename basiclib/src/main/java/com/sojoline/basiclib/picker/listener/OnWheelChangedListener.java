package com.sojoline.basiclib.picker.listener;

import com.sojoline.basiclib.picker.wheel.WheelView;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/01
 *     desc   :	Wheel changed listener interface.
 *     version: 1.0
 * </pre>
 */

public interface OnWheelChangedListener {
	/**
	 * Callback method to be invoked when current item changed
	 * @param wheel the wheel view whose state has changed
	 * @param oldValue the old value of current item
	 * @param newValue the new value of current item
	 */
	void onChanged(WheelView wheel, int oldValue, int newValue);
}
