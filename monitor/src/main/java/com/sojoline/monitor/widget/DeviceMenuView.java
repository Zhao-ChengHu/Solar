package com.sojoline.monitor.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ValueAnimator;
import com.sojoline.base.util.ScreenUtils;
import com.sojoline.base.widget.DeviceType;
import com.sojoline.monitor.R;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class DeviceMenuView extends FrameLayout implements View.OnClickListener{
	private View view;
	private TextView tvTransformer;
	private TextView tvInverter;
	private TextView tvCombiner;
	private TextView tvWarning;
	private OnDeviceClickListener listener;
	private boolean isShowing;

	public DeviceMenuView(@NonNull Context context) {
		this(context, null);
	}

	public DeviceMenuView(@NonNull Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DeviceMenuView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.monitor_view_device_menu, this);
		findViewById(R.id.fl_root).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hide();
			}
		});

		view = findViewById(R.id.ll_container);
		tvCombiner = (TextView) view.findViewById(R.id.tv_combiner);
		tvInverter = (TextView) view.findViewById(R.id.tv_inverter);
		tvTransformer = (TextView) view.findViewById(R.id.tv_transformer);
		tvWarning = (TextView) view.findViewById(R.id.tv_warning);
		tvTransformer.setOnClickListener(this);
		tvInverter.setOnClickListener(this);
		tvCombiner.setOnClickListener(this);
		tvWarning.setOnClickListener(this);
	}

	public void setListener(OnDeviceClickListener listener) {
		this.listener = listener;
	}

	public void show(Activity _mActivity){
		Log.i("TAG", "show: ");
		isShowing = true;
		final ViewGroup contentView = (ViewGroup) _mActivity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		contentView.addView(this, params);
		ValueAnimator animator1 = ValueAnimator.ofFloat(0.0f, 1.0f);
		animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = (float) animation.getAnimatedValue();
				view.setScaleX(value);
				view.setScaleY(value);
			}
		});
		ValueAnimator animator2 = ValueAnimator.ofFloat(-ScreenUtils.dp2px(60), 0f);
		animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = (float) animation.getAnimatedValue();
				view.setTranslationX(-value);
				view.setTranslationY(value);
			}
		});

		AnimatorSet set = new AnimatorSet();
		set.playTogether(animator1, animator2);
		set.setDuration(200);
		set.start();
	}

	public void hide(){
		Log.i("TAG", "hide: ");
		isShowing = false;
		ValueAnimator animator = ValueAnimator.ofFloat(1.0f, 0.0f);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = (float) animation.getAnimatedValue();
				view.setAlpha(value);
			}
		});
		animator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				if (DeviceMenuView.this.getParent() != null){
					((ViewGroup)DeviceMenuView.this.getParent()).removeView(DeviceMenuView.this);
				}
			}
		});
		animator.setDuration(200);
		animator.start();
	}

	public boolean isShowing(){
		return isShowing;
	}

	@Override
	public void onClick(View v) {
		if (v == tvCombiner){
			if (listener != null) {
				listener.onClick(DeviceType.COMBINER);
			}
		}else if (v == tvInverter){
			if (listener != null) {
				listener.onClick(DeviceType.INVERTER);
			}
		}else if (v == tvTransformer){
			if (listener != null) {
				listener.onClick(DeviceType.TRANSFORMER);
			}
		}else {
			//告警
			if (listener != null) {
				listener.onClick(DeviceType.NONE);
			}
		}
		hide();
	}

	public interface OnDeviceClickListener{
		void onClick(DeviceType type);
	}
}
