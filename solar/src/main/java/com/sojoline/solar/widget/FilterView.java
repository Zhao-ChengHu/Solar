package com.sojoline.solar.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.FrameLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ValueAnimator;
import com.sojoline.base.util.ScreenUtils;
import com.sojoline.base.widget.DeviceType;
import com.sojoline.solar.R;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class FilterView extends FrameLayout {
	private View view;
	private SwitchCompat scAll;
	private SwitchCompat scTransformer;
	private SwitchCompat scInverter;
	private SwitchCompat scCombiner;

	private DeviceType type = DeviceType.NONE;
	private OnFilterTypeChangedListener listener;

	public FilterView(@NonNull Context context) {
		this(context, DeviceType.NONE);
	}

	public FilterView(@NonNull Context context, DeviceType type) {
		this(context, null, type);
	}

	public FilterView(@NonNull Context context, @Nullable AttributeSet attrs, DeviceType type) {
		super(context, attrs);
		this.type = type;
		init();
	}

	public void setOnFilterTypeChangedListener(OnFilterTypeChangedListener listener){
		this.listener = listener;
	}

	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.solar_view_filter, this);
		findViewById(R.id.fl_root).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hide();
			}
		});

		CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					DeviceType deviceType;
					if (buttonView == scAll){
						deviceType = DeviceType.NONE;
					}else if (buttonView == scTransformer){
						deviceType = DeviceType.TRANSFORMER;
					}else if (buttonView == scInverter){
						deviceType = DeviceType.INVERTER;
					}else {
						deviceType = DeviceType.COMBINER;
					}

					if (deviceType != type){
						type = deviceType;
						if (listener != null) {
							listener.filterTypeChanged(deviceType);
						}
						setSwitchDisplay();
						hide();
					}
				}
			}
		};

		view = findViewById(R.id.cl_container);

		scCombiner = (SwitchCompat) findViewById(R.id.sc_combiner);
		scCombiner.setOnCheckedChangeListener(checkedChangeListener);

		scInverter = (SwitchCompat) findViewById(R.id.sc_inverter);
		scInverter.setOnCheckedChangeListener(checkedChangeListener);

		scTransformer = (SwitchCompat) findViewById(R.id.sc_transformer);
		scTransformer.setOnCheckedChangeListener(checkedChangeListener);

		scAll = (SwitchCompat) findViewById(R.id.sc_all);
		scAll.setOnCheckedChangeListener(checkedChangeListener);

		setSwitchDisplay();
	}

	private void setSwitchDisplay(){
		scAll.setChecked(type == DeviceType.NONE);
		scTransformer.setChecked(type == DeviceType.TRANSFORMER);
		scInverter.setChecked(type == DeviceType.INVERTER);
		scCombiner.setChecked(type == DeviceType.COMBINER);
	}

	public void show(Activity _mActivity){
		Log.i("TAG", "show: ");
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
		set.setDuration(300);
		set.start();
	}

	public void hide(){
		Log.i("TAG", "hide: ");
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
				if (FilterView.this.getParent() != null){
					((ViewGroup)FilterView.this.getParent()).removeView(FilterView.this);
				}
			}
		});
		animator.setDuration(300);
		animator.start();
	}

	public interface OnFilterTypeChangedListener{
		/**
		 * 	过滤类型改变回调
		 * @param type 过滤类型
		 */
		void filterTypeChanged(DeviceType type);
	}
}
