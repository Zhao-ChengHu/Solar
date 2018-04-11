package com.sojoline.solar.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * <pre>
 *     @author : 李小勇
 *     @date   : 2017/10/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SwipeFrameLayout extends FrameLayout {
	private GestureDetector gestureDetector;
	private OnSwipeListener listener;
	public SwipeFrameLayout(@NonNull Context context) {
		super(context);
		init();
	}

	public SwipeFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public void setOnSwipeListener(OnSwipeListener listener) {
		this.listener = listener;
	}

	private void init(){
		gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener(){
			@Override
			public boolean onDown(MotionEvent e) {
				return false;
			}

			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				float distX = e2.getX() - e1.getX();
				float distY = e2.getY() - e1.getY();
				boolean isHorizontal = distX > distY || (distX == distY && velocityX > velocityY);
				if (isHorizontal){
					if (listener != null) {
						listener.onHorizontalSwipe();
					}
				}else {
					if (listener != null) {
						listener.onVerticalSwipe();
					}
				}
				return true;
			}
		});
	}

	/**
	 * @return true 表示消费了事件 false 则事件继续向父控件传递
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		gestureDetector.onTouchEvent(event);
		return true;
	}

	public interface OnSwipeListener{
		/**
		 * 竖直滑动
		 */
		void onVerticalSwipe();

		/**
		 * 水平滑动
		 */
		void onHorizontalSwipe();
	}
}
