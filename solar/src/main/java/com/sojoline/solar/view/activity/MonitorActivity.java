package com.sojoline.solar.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.bean.solar.MonitorDate;
import com.sojoline.presenter.solar.data.DeviceDataContract;
import com.sojoline.presenter.solar.data.DeviceDataPresenter;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;
import com.trello.rxlifecycle.ActivityEvent;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * <pre>
 *     @author : zhaochenghu
 *     date   : 2018/7/31
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Route(path = "/solar/login/monitor")
public class MonitorActivity extends BaseCompatActivity implements DeviceDataContract.View {
	/**
	 * 编号：EnvDetectorID
	 采集时间
	 运行状态
	 环境温度 AmbTemperature
	 湿度、EnvHumidity
	 风速、WindSpeedInstant
	 风向、WindInstant
	 总辐照量、DayTotalRadiation
	 直射辐照量、DirectRadiationInstant
	 散射辐照量、DayScatteredRadiation
	 日累计辐照量 DayPhoRadiation
	 */
	@BindView(R2.id.tv_EnvDetectorID)
	TextView tvEnvDetectorID;
	@BindView(R2.id.tv_Time)
	TextView tvTime;
	@BindView(R2.id.tv_AmbTemperature)
	TextView tvAmbTemperature;
	@BindView(R2.id.tv_EnvHumidity)
	TextView tvEnvHumidity;
	@BindView(R2.id.tv_WindSpeedInstant)
	TextView tvWindSpeedInstant;
	@BindView(R2.id.tv_WindInstant)
	TextView tvWindInstant;
	@BindView(R2.id.tv_DayTotalRadiation)
	TextView tvDayTotalRadiation;
	@BindView(R2.id.tv_DirectRadiationInstant)
	TextView tvDirectRadiationInstant;
	@BindView(R2.id.tv_DayScatteredRadiation)
	TextView tvDayScatteredRadiation;
	@BindView(R2.id.tv_DayPhoRadiation)
	TextView tvDayPhoRadiation;

	private DeviceDataPresenter presenter;
	private Subscription subscription;
	private String id;

	public static void navigation(String id) {
		ARouter.getInstance().build("/solar/login/monitor")
				.withString("id", id).
				navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.solar_activity_monitor);
	}

	@Override
	protected void initPresenter() {
		super.initPresenter();
		presenter = new DeviceDataPresenter();
		presenter.attachView(this);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		initToolbarNav("环境监测");

		id = getIntent().getStringExtra("id");
		presenter.getMonitorData(id);
	}

	@Override
	protected void onResume() {
		super.onResume();
		pollQueryData();
	}

	/**
	 * 轮询查询数据
	 * 时间间隔为5秒
	 */
	private void pollQueryData(){
		subscription = Observable.interval(5, TimeUnit.MINUTES)
				.compose(this.<Long>bindUntilEvent(ActivityEvent.STOP))
				.compose(SchedulersCompat.<Long>applyNewSchedulers())
				.subscribe(new Action1<Long>() {
					@Override
					public void call(Long aLong) {
						presenter.getCombinerData(id);
					}
				});
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
		} else {
			showToast(R.string.network_not_available);
		}
	}

	@Override
	public void success(Object o) {
		MonitorDate  data = (MonitorDate) o;
		if (data != null) {

		}
		if (data != null) {
			tvEnvDetectorID.setText( data.getEnvDetectorID());
			tvTime.setText(data.getJoinTime());
			tvAmbTemperature.setText(data.getAmbTemperature()/10 + "℃");
			tvEnvHumidity.setText(data.getEnvHumidity()/10 + "%");
			tvWindSpeedInstant.setText(data.getWindSpeedInstant() + "m/s");
			tvWindInstant.setText(data.getWindInstant() + "°");
			tvDayTotalRadiation.setText((data.getDayTotalRadiation2() + data.getDayTotalRadiation1())/1000 + "MJ/㎡");
			tvDirectRadiationInstant.setText(data.getDirectRadiationInstant()/1000 + "W/㎡");
			tvDayScatteredRadiation.setText(data.getDayScatteredRadiation()/1000 + "W/㎡");
			tvDayPhoRadiation.setText(data.getDaySunshine() + "MJ/㎡");
		}
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
	}
}
