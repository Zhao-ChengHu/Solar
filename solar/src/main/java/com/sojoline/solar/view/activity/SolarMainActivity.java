package com.sojoline.solar.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.update.PgyUpdateManager;
import com.sojoline.base.util.AppUtils;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.basiclib.event.UnauthorizedEvent;
import com.sojoline.basiclib.rx.RxBus;
import com.sojoline.basiclib.rx.SimpleSubscriber;
import com.sojoline.solar.R;
import com.sojoline.solar.view.fragment.SolarMainFragment;
import com.trello.rxlifecycle.ActivityEvent;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/10/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Route(path = "/solar/login/main")
public class SolarMainActivity extends BaseCompatActivity{

	public static void navigation(){
		ARouter.getInstance().build("/solar/login/main")
				.navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.solar_activity_main);
		if (savedInstanceState == null) {
			loadRootFragment(R.id.fragment_container, SolarMainFragment.newInstance());
			PgyCrashManager.register(this);
			PgyUpdateManager.setIsForced(true);
			PgyUpdateManager.register(this);

		}
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		//AppUtils.appUpdate(this);
		RxBus.getInstance().toObservable(UnauthorizedEvent.class)
				.compose(this.<UnauthorizedEvent>bindUntilEvent(ActivityEvent.DESTROY))
				.subscribe(new SimpleSubscriber<UnauthorizedEvent>() {
					@Override
					public void onNext(UnauthorizedEvent unauthorizedEvent) {
						super.onNext(unauthorizedEvent);
						if (AppUtils.isLogin()){
							AppUtils.clearUserData();
							ARouter.getInstance().build("/app/login")
									.withBoolean("isPrompt", true)
									.withFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
									.navigation();
							finish();
						}

					}
				});
	}
}
