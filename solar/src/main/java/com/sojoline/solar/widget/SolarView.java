package com.sojoline.solar.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nineoldandroids.animation.ValueAnimator;
import com.sojoline.model.bean.solar.SolarStation;
import com.sojoline.model.storage.AppInfoPreferences;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;
import com.sojoline.solar.view.activity.SolarMainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarView extends FrameLayout {
	String TAG = SolarView.class.getSimpleName();
	@BindView(R2.id.tv_station_name)
	TextView tvStationName;
	@BindView(R2.id.iv_solar)
	ImageView ivSolar;
	@BindView(R2.id.tv_station_power)
	TextView tvStationPower;
	@BindView(R2.id.tv_total_energy)
	TextView tvTotalEnergy;
	@BindView(R2.id.tv_station_address)
	TextView tvStationAddress;
	@BindView(R2.id.fl_container)
	SwipeFrameLayout flContainer;

	private int height;
	private boolean isHiding;
	private SolarStation station;

	public SolarView(@NonNull Context context) {
		this(context, null);
	}

	public SolarView(@NonNull Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SolarView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		isHiding = false;
		View view = LayoutInflater.from(getContext()).inflate(R.layout.solar_view_station, this);
		ButterKnife.bind(this, view);
		this.setVisibility(INVISIBLE);
		height = flContainer.getMeasuredHeight();
		Log.i(TAG,"height:" + height);
		flContainer.setOnSwipeListener(new SwipeFrameLayout.OnSwipeListener() {
			@Override
			public void onVerticalSwipe() {
				hide();
			}

			@Override
			public void onHorizontalSwipe() {

			}
		});
	}

	@OnClick(R2.id.bt_detail)
	public void onViewClicked() {
		//跳转到电站主界面
		AppInfoPreferences.get().setStationId(station.getDPStationID());
		AppInfoPreferences.get().setCreateTime(station.getCreateTime());
		SolarMainActivity.navigation();
	}

	/**
	 * 显示view
	 */
	public void show(){
		if (isShown()){
			return;
		}

		this.setVisibility(VISIBLE);
		flContainer.setTranslationY(height);
		ValueAnimator animator = ValueAnimator.ofFloat(height, 0f);
		animator.setDuration(300);
		animator.setInterpolator(new LinearInterpolator());
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = (float) animation.getAnimatedValue();
				flContainer.setTranslationY(value);
			}
		});
		animator.start();
	}

	/**
	 * 隐藏view
	 */
	public void hide(){
		if (!isShown() || isHiding){
			return;
		}

		isHiding = true;
		ValueAnimator animator = ValueAnimator.ofFloat(0f, height);
		animator.setDuration(300);
		animator.setInterpolator(new LinearInterpolator());
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				Float value = (Float) animation.getAnimatedValue();
				flContainer.setTranslationY(value);
				if (value == height){
					SolarView.this.setVisibility(INVISIBLE);
					isHiding = false;
				}
			}
		});
		animator.start();
	}

	public float getViewHeight(){
		return flContainer.getMeasuredHeight();
	}

	public boolean isHiding(){
		return isHiding;
	}

	public void refreshData(SolarStation station){
		this.station = station;
		tvStationName.setText(station.getDPStationName());
		tvStationAddress.setText("地址：" + station.getAddress());
		tvStationPower.setText("装机容量：" + station.getInstalledCapacity() + "kW");
		tvTotalEnergy.setText("累计发电量：" + station.getPvTotalElectric() + "kWh");
	}
}
