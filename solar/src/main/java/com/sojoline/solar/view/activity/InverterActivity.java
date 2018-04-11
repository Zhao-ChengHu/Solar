package com.sojoline.solar.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.bean.solar.InverterData;
import com.sojoline.presenter.solar.data.DeviceDataContract;
import com.sojoline.presenter.solar.data.DeviceDataPresenter;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;
import com.trello.rxlifecycle.ActivityEvent;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/03
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Route(path = "/solar/login/inverter")
public class InverterActivity extends BaseCompatActivity implements DeviceDataContract.View {

	@BindView(R2.id.tv_code)
	TextView tvCode;
	@BindView(R2.id.tv_mode)
	TextView tvMode;
	@BindView(R2.id.tv_energy)
	TextView tvEnergy;
	@BindView(R2.id.tv_updateTime)
	TextView tvUpdateTime;
	@BindView(R2.id.tv_VoltageAB)
	TextView tvVoltageAB;
	@BindView(R2.id.tv_VoltageBC)
	TextView tvVoltageBC;
	@BindView(R2.id.tv_VoltageCA)
	TextView tvVoltageCA;
	@BindView(R2.id.tv_ArrayVoltage)
	TextView tvArrayVoltage;
	@BindView(R2.id.tv_CurrentA)
	TextView tvCurrentA;
	@BindView(R2.id.tv_CurrentB)
	TextView tvCurrentB;
	@BindView(R2.id.tv_CurrentC)
	TextView tvCurrentC;
	@BindView(R2.id.tv_ArrayCurrent)
	TextView tvArrayCurrent;
	@BindView(R2.id.tv_PowFactorA)
	TextView tvPowFactorA;
	@BindView(R2.id.tv_PowFactorB)
	TextView tvPowFactorB;
	@BindView(R2.id.tv_PowFactorC)
	TextView tvPowFactorC;
	@BindView(R2.id.tv_ActivePower)
	TextView tvActivePower;
	@BindView(R2.id.tv_ReactivePower)
	TextView tvReactivePower;
	@BindView(R2.id.tv_ApparentPower)
	TextView tvApparentPower;
	@BindView(R2.id.tv_ArrayPower)
	TextView tvArrayPower;

	private DeviceDataPresenter presenter;
	private String id;

	public static void navigation(String id) {
		ARouter.getInstance().build("/solar/login/inverter")
				.withString("id", id)
				.navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.solar_activity_inverter);
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
		initToolbarNav("逆变器");

		id = getIntent().getStringExtra("id");
		presenter.getInverterData(id);
	}

	@Override
	protected void onResume() {
		super.onResume();
		pollQueryData();
	}

	/**
	 * 轮询查询数据
	 * 时间间隔为5分钟
	 */
	private void pollQueryData(){
		Observable.interval(5, TimeUnit.MINUTES)
				.compose(this.<Long>bindUntilEvent(ActivityEvent.STOP))
				.compose(SchedulersCompat.<Long>applyNewSchedulers())
				.subscribe(new Action1<Long>() {
					@Override
					public void call(Long aLong) {
						presenter.getInverterData(id);
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
		InverterData data = (InverterData) o;
		if (data != null) {
			tvCode.setText(data.getInverterId());
			tvUpdateTime.setText(data.getJoinTime());
			tvMode.setText(data.getRunMode());
			tvEnergy.setText(String.format(Locale.getDefault(), "%.1fkWh", data.getTotalElectric() / 10));
			tvVoltageAB.setText(String.format(Locale.getDefault(), "%.1fV", data.getVoltageAB() / 10));
			tvVoltageBC.setText(String.format(Locale.getDefault(), "%.1fV", data.getVoltageBC() / 10));
			tvVoltageCA.setText(String.format(Locale.getDefault(), "%.1fV", data.getVoltageCA() / 10));
			tvArrayVoltage.setText(String.format(Locale.getDefault(), "%.1fV", data.getArrayVoltage() / 10));

			tvCurrentA.setText(String.format(Locale.getDefault(), "%.1fA", data.getCurrentA() / 10));
			tvCurrentB.setText(String.format(Locale.getDefault(), "%.1fA", data.getCurrentB() / 10));
			tvCurrentC.setText(String.format(Locale.getDefault(), "%.1fA", data.getCurrentC() / 10));
			tvArrayCurrent.setText(String.format(Locale.getDefault(), "%.1fA", data.getArrayCurrent() / 10));

			tvPowFactorA.setText(String.format(Locale.getDefault(), "%.1f", data.getPowFactorA() / 100));
			tvPowFactorB.setText(String.format(Locale.getDefault(), "%.1f", data.getPowFactorB() / 100));
			tvPowFactorC.setText(String.format(Locale.getDefault(), "%.1f", data.getPowFactorC() / 100));

			tvActivePower.setText(data.getActivePower() + "");
			tvApparentPower.setText(data.getApparentPower() + "");
			tvArrayPower.setText(data.getArrayPower() + "");
			tvReactivePower.setText(data.getReactivePower() + "");
		}
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
	}

}
