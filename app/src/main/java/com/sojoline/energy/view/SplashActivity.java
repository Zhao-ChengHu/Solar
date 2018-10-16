package com.sojoline.energy.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ValueAnimator;
import com.sojoline.base.util.AppConstants;
import com.sojoline.base.util.AppUtils;
import com.sojoline.base.util.ScreenUtils;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.base.widget.CircleMenu;
import com.sojoline.energy.R;
import com.sojoline.model.storage.AppInfoPreferences;
import com.sojoline.monitor.view.activity.MonitorMainActivity;
import com.sojoline.monitor.view.activity.MonitorStationActivity;
import com.sojoline.solar.view.activity.CollectionActivity;
import com.sojoline.solar.view.activity.SolarMainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/09/21
 *     desc   : 引导界面
 *     version: 1.0
 * </pre>
 */
@Route(path = "/app/splash")
public class SplashActivity extends BaseCompatActivity {
	@BindView(R.id.circle_menu)
	CircleMenu menu;
	@BindView(R.id.iv_splash)
	ImageView ivSplash;

	private  boolean isAuto;
	private int module;
	private int tempModule;

	public static void navigation(boolean isAnimated){
		ARouter.getInstance().build("/app/splash")
				.withBoolean("isAnimated", isAnimated)
				.navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_splash);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		boolean isAnimated = getIntent().getBooleanExtra("isAnimated", false);
		isAuto = false;
		module = AppInfoPreferences.get().getModule();
		if (isAnimated){
			showMenu();
		}else {
			if (module == AppConstants.MODULE_MONITOR || module == AppConstants.MODULE_SALE || module == AppConstants.MODULE_SOLAR){
				isAuto = true;
				startAnimation();
				menu.setVisibility(View.GONE);
			}else {
				showMenu();
				startAnimation();
			}
		}
	}

	/**
	 * 底部动画
	 */
	private void startAnimation(){
		long duration = 2000;
		float distY = ScreenUtils.dp2px(40);
		AnimatorSet set = new AnimatorSet();
		ValueAnimator valueAnimator = ValueAnimator.ofFloat(20f, - distY);
		valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = (float) animation.getAnimatedValue();
				ivSplash.setTranslationY(value);
			}
		});
		ValueAnimator valueAnimator1 = ValueAnimator.ofFloat(0.0f, 1.0f);
		valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = (float) animation.getAnimatedValue();
				ivSplash.setAlpha(value);
			}
		});
		set.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				//动画完成后，自动跳转界面
				if (isAuto){
					turnToActivity();
					isAuto = false;
				}
			}
		});
		set.playTogether(valueAnimator, valueAnimator1);
		set.setDuration(duration);
		set.start();
	}

	/**
	 * 显示模块选择菜单
	 */
	private void showMenu(){
		final List<String> list = new ArrayList<>();
		list.add(new String("售电"));
		list.add(new String("光伏"));
		list.add(new String("能耗"));
		menu.setContent(list);
		menu.setListener(new CircleMenu.OnCircleClickListener() {
			@Override
			public void onClick(String content) {
				if (list.get(0).equals(content)){
					tempModule = AppConstants.MODULE_SALE;
				}else if (list.get(1).equals(content)){
					tempModule = AppConstants.MODULE_SOLAR;
				}else {
					tempModule = AppConstants.MODULE_MONITOR;
				}

				if (module == tempModule && AppUtils.isLogin()){
					turnToActivity();
				}else {
					AppUtils.clearUserData();
					AppInfoPreferences.get().setModule(tempModule);
					LoginActivity.navigation(false);
					finish();
				}
			}
		});
	}

	/**
	 * 跳转界面
	 */
	private void turnToActivity(){
		String id = AppInfoPreferences.get().getStationId();
		if (!AppUtils.isLogin()){
			LoginActivity.navigation(false);
			finish();
			return;
		}
		if (module == AppConstants.MODULE_SOLAR){
			//光伏
			if (TextUtils.isEmpty(id)){
				CollectionActivity.navigation();
			}else {
				SolarMainActivity.navigation();
			}
		}else if (module == AppConstants.MODULE_SALE){
			//售电
			Toast.makeText(SplashActivity.this,"售电系统研发中,请退出登陆重新选择",Toast.LENGTH_LONG).show();
			SettingActivity.navigation();
//			SolarMimeFragment.newInstance();
		}else if (module == AppConstants.MODULE_MONITOR){
			//能耗监测
			if (TextUtils.isEmpty(id)){
				MonitorStationActivity.navigation();
			}else {
				MonitorMainActivity.navigation();
			}
		}else {
			showMenu();
		}
		AppInfoPreferences.get().setModule(module);
		finish();
	}
}
