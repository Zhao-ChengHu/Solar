package com.sojoline.solar.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.bean.solar.MeterData;
import com.sojoline.model.bean.solar.PowermeterData;
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
 *     date   : 2018/8/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Route(path = "/solar/login/meter")
public class MeterActivity extends BaseCompatActivity implements DeviceDataContract.View {
	@BindView(R2.id.tv_code)
	TextView tvcode;
	@BindView(R2.id.tv_updateTime)
	TextView tvTime;
	@BindView(R2.id.tv_PosActElectric)
	TextView tvPosActElectric;//
	@BindView(R2.id.tv_ActivePower)
	TextView tvActivePower;//
	@BindView(R2.id.tv_DecFowardJElectric)
	TextView tvDecFowardJElectric;//
	@BindView(R2.id.tv_DecFowardPElectric)
	TextView tvDecFowardPElectric;//
	@BindView(R2.id.tv_DecFowardFElectric)
	TextView tvDecFowardFElectric;//
	@BindView(R2.id.tv_DecFowardGElectric)
	TextView tvDecFowardGElectric;//
	@BindView(R2.id.tv_DecBackwardFElectric)
	TextView tvDecBackwardFElectric;//
	@BindView(R2.id.tv_DecBackwardJElectric)
	TextView tvDecBackwardJElectric;//
	@BindView(R2.id.tv_DecBackwardPElectric)
	TextView tvDecBackwardPElectric;
	@BindView(R2.id.tv_DecBackwardGElectric)
	TextView tvDecBackwardGElectric;//
	@BindView(R2.id.tv_DecFowardReactiveJElectric)
	TextView tvDecFowardReactiveJElectric;//
	@BindView(R2.id.tv_DecFowardReactiveFElectric)
	TextView tvDecFowardReactiveFElectric;
	@BindView(R2.id.tv_DecFowardReactivePElectric)
	TextView tvDecFowardReactivePElectric;
	@BindView(R2.id.tv_DecFowardReactiveGElectric)
	TextView tvDecFowardReactiveGElectric;
	private DeviceDataPresenter presenter;
	private Subscription subscription;
	private String id;

	public static void navigation(String id) {
		ARouter.getInstance().build("/solar/login/meter")
				.withString("id", id).
				navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.solar_activity_meter);
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
		initToolbarNav("电表");

		id = getIntent().getStringExtra("id");
		presenter.getMeterData(id);
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

	@SuppressLint("SetTextI18n")
	@Override
	public void success(Object o) {
		MeterData data = (MeterData) o;
		if (data != null) {
			tvcode.setText(data.getMeterID());
			tvTime.setText(data.getJoinTime());
			//tvDirectRadiationInstant.setText(data.getDirectRadiationInstant()/1000 + "W/㎡");
			tvDecFowardPElectric.setText(data.getDecFowardPElectric()+"kWh");
			tvDecFowardFElectric.setText(data.getDecFowardFElectric()+"kWh");
			tvPosActElectric.setText(data.getPosActElectric()/100+"kWh");
			tvActivePower.setText(data.getActivePower()/10000+"kW");
			tvDecFowardJElectric.setText(data.getDecFowardJElectric()+"kWh");
			tvDecFowardGElectric.setText(data.getDecFowardGElectric()+"kWh");
			tvDecBackwardFElectric.setText(data.getDecBackwardFElectric()+"kWh");
			tvDecBackwardJElectric.setText(data.getDecBackwardJElectric()+"kWh");
			tvDecBackwardPElectric.setText(data.getDecBackwardPElectric()+"kWh");
			tvDecBackwardGElectric.setText(data.getDecBackwardGElectric()+"kWh");
			tvDecFowardReactiveJElectric.setText(data.getDecFowardReactiveJElectric()+"kWh");
			tvDecFowardReactiveFElectric.setText(data.getDecFowardReactiveFElectric()+"kWh");
			tvDecFowardReactivePElectric.setText(data.getDecFowardReactivePElectric()+"kWh");
			tvDecFowardReactiveGElectric.setText(data.getDecFowardReactiveGElectric()+"kWh");

		}
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
	}
}
