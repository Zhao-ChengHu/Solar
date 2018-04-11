package com.sojoline.solar.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.basiclib.rx.RxBus;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.bean.solar.SolarStation;
import com.sojoline.presenter.solar.station.SolarStationContract;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;
import com.sojoline.solar.event.SolarViewEvent;
import com.sojoline.solar.view.fragment.SolarListFragment;
import com.sojoline.solar.view.fragment.SolarMapFragment;
import com.sojoline.solar.widget.SolarView;
import com.trello.rxlifecycle.ActivityEvent;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/10/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Route(path = "/solar/login/stations")
public class StationsActivity extends BaseCompatActivity implements SolarStationContract.View{
	@BindView(R2.id.ib_list)
	ImageButton ibList;
	@BindView(R2.id.solar_view)
	SolarView solarView;

	private SolarListFragment listFragment;
	private SolarMapFragment mapFragment;

	public static void navigation(){
		ARouter.getInstance().build("/solar/login/stations").navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.solar_activity_stations);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		if (savedInstanceState == null) {
			listFragment = SolarListFragment.newInstance();
			mapFragment = SolarMapFragment.newInstance();
			loadMultipleRootFragment(R.id.sub_fragment_container, 0, mapFragment, listFragment);
		}else {
			listFragment = findFragment(SolarListFragment.class);
			mapFragment = findFragment(SolarMapFragment.class);
		}

		//注册底部弹窗显示事件
		RxBus.getInstance().toObservable(SolarViewEvent.class)
				.compose(this.<SolarViewEvent>bindUntilEvent(ActivityEvent.STOP))
				.compose(SchedulersCompat.<SolarViewEvent>observeOnMainThread())
				.subscribe(new Action1<SolarViewEvent>() {
					@Override
					public void call(SolarViewEvent solarViewEvent) {
						if (solarViewEvent.isShow()){
							solarView.show();
							solarView.refreshData(solarViewEvent.getStation());
						}else {
							solarView.hide();
						}
					}
				});
	}

	@OnClick(R2.id.ib_list)
	public void clickTurnList(View view){
		if (listFragment.isHidden()){
			ibList.setImageResource(R.mipmap.ic_solar_map);
			showHideFragment(listFragment, mapFragment);
			if (!solarView.isHiding()){
				solarView.hide();
			}
		}else {
			ibList.setImageResource(R.mipmap.ic_solar_list);
			showHideFragment(mapFragment, listFragment);
		}
	}

	public float getSolarViewHeight(){
		return solarView.getViewHeight();
	}

	@Override
	public void showLoading(String msg) {

	}

	@Override
	public void showNormal() {

	}

	@Override
	public void requestFailed(String msg) {
		if (msg != null) {
			showToast(msg);
		}else {
			showToast(R.string.network_not_available);
		}
	}

	@Override
	public void success(List<SolarStation> list) {
		listFragment.transferData(list);
		mapFragment.transferData(list);
	}
}
