package com.sojoline.solar.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
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
    @BindView(R2.id.Scrollview)
	ScrollView  tvScrollview;
	@BindView(R2.id.ScrollviewInverter)
	ScrollView  tvScrollviewInverter;

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
//	@BindView(R2.id.tv_InputTotalPower)
//	TextView tvInputTotalPower;
	@BindView(R2.id.tv_Invercode)
	TextView tvInvercode;
	@BindView(R2.id.tv_Time)
	TextView tvTime;
	@BindView(R2.id.tv_InverterState)
	TextView tvInverterState;
	@BindView(R2.id.tv_InverterTem)
	TextView tvInverterTem;
	@BindView(R2.id.tv_InverterDayElec)
	TextView tvInverterDayElec;
	@BindView(R2.id.tv_InverterMonthElec)
	TextView tvInverterMonthElec;
	@BindView(R2.id.tv_VoltageA)
	TextView tvVoltageA;
	@BindView(R2.id.tv_VoltageB)
	TextView tvVoltageB;
	@BindView(R2.id.tv_VoltageC)
	TextView tvVoltageC;
	@BindView(R2.id.tv_CurrentA1)
	TextView tvCurrentA1;
	@BindView(R2.id.tv_CurrentB1)
	TextView tvCurrentB1;
	@BindView(R2.id.tv_CurrentC1)
	TextView tvCurrentC1;
	@BindView(R2.id.tv_DcVoltageOne)
	TextView tvDcVoltageOne;
	@BindView(R2.id.tv_DcCurrentOne)
	TextView tvDcCurrentOne;
	@BindView(R2.id.tv_DcVoltageTwo)
	TextView tvDcVoltageTwo;
	@BindView(R2.id.tv_DcCurrentTwo)
	TextView tvDcCurrentTwo;
	@BindView(R2.id.tv_DcVoltageThree)
	TextView tvDcVoltageThree;
	@BindView(R2.id.tv_DcCurrentThree)
	TextView tvDcCurrentThree;
	@BindView(R2.id.tv_TotalDCPower)
	TextView tvTotalDCPower;
	@BindView(R2.id.tv_NegativeTurnGroundVoltage)
	TextView tvNegativeTurnGroundVoltage;
	@BindView(R2.id.tv_BusVoltage)
	TextView tvBusVoltage;
	@BindView(R2.id.tv_TotalOperatingTime)
	TextView tvTotalOperatingTime;
	@BindView(R2.id.tv_PV1InputA)
	TextView tvPV1InputA;
	@BindView(R2.id.tv_PV2InputA)
	TextView tvPV2InputA;
	@BindView(R2.id.tv_PV3InputA)
	TextView tvPV3InputA;
	@BindView(R2.id.tv_PV4InputA)
	TextView tvPV4InputA;
	@BindView(R2.id.tv_PV5InputA)
	TextView tvPV5InputA;
	@BindView(R2.id.tv_PV6InputA)
	TextView tvPV6InputA;
	@BindView(R2.id.tv_PV7InputA)
	TextView tvPV7InputA;
	@BindView(R2.id.tv_PV8InputA)
	TextView tvPV8InputA;
	@BindView(R2.id.tv_PV9InputA)
	TextView tvPV9InputA;
	@BindView(R2.id.tv_PV10InputA)
	TextView tvPV10InputA;
	@BindView(R2.id.tv_PV11InputA)
	TextView tvPV11InputA;
	@BindView(R2.id.tv_PV12InputA)
	TextView tvPV12InputA;
	@BindView(R2.id.tv_PV13InputA)
	TextView tvPV13InputA;
	@BindView(R2.id.tv_PV14InputA)
	TextView tvPV14InputA;
	@BindView(R2.id.tv_PowerFactor)
	TextView tvPowerFactor;
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
			if (data.getProtocolId().equals("011")) {
				tvScrollview.setVisibility(View.GONE);
				tvInvercode.setText(data.getInverterId());
				tvTime.setText(data.getJoinTime());
				tvInverterState.setText(data.getInverterState());
				tvInverterTem.setText(data.getInverterTem()/10+"℃");
				tvInverterDayElec.setText(data.getInverterDayElec()/1000+"kWh");
				tvInverterMonthElec.setText(data.getInverterMonthElec()/100+"kWh");
				tvVoltageA.setText(data.getVoltageA()/10+"V");
				tvVoltageB.setText(data.getVoltageB()/10+"V");
				tvVoltageC.setText(data.getVoltageC()/10+"V");
//				tvInputTotalPower.setText(data.getInputTotalPower()+"KW");
				tvCurrentA1.setText(data.getCurrentA()+"A");
				tvCurrentB1.setText(data.getCurrentB()+"A");
				tvCurrentC1.setText(data.getCurrentC()+"A");
				tvDcVoltageOne.setText(data.getDcVoltageOne()+"V");
				tvDcCurrentOne.setText(data.getDcCurrentOne()+"A");
				tvDcCurrentTwo.setText(data.getDcCurrentTwo()+"A");
				tvDcVoltageTwo.setText(data.getDcVoltageTwo()+"V");
				tvDcCurrentThree.setText(data.getDcCurrentThree()+"A");
				tvDcVoltageThree.setText(data.getDcVoltageThree()+"V");
				tvTotalDCPower.setText(data.getTotalDCPower()+"W");
				tvNegativeTurnGroundVoltage.setText(data.getNegativeTurnGroundVoltage()+"V");
				tvBusVoltage.setText(data.getBusVoltage()+"V");
				tvTotalOperatingTime.setText(data.getTotalOperatingTime()+"h");
				tvPV1InputA.setText(data.getPV1InputA()+"A");
				tvPV2InputA.setText(data.getPV2InputA()+"A");
				tvPV3InputA.setText(data.getPV3InputA()+"A");
				tvPV4InputA.setText(data.getPV4InputA()+"A");
				tvPV5InputA.setText(data.getPV5InputA()+"A");
				tvPV6InputA.setText(data.getPV6InputA()+"A");
				tvPV7InputA.setText(data.getPV7InputA()+"A");
				tvPV8InputA.setText(data.getPV8InputA()+"A");
				tvPV9InputA.setText(data.getPV9InputA()+"A");
				tvPV10InputA.setText(data.getPV10InputA()+"A");
				tvPV11InputA.setText(data.getPV11InputA()+"A");
				tvPV12InputA.setText(data.getPV12InputA()+"A");
				tvPV13InputA.setText(data.getPV13InputA()+"A");
				tvPV14InputA.setText(data.getPV14InputA()+"A");
				tvPowerFactor.setText(String.valueOf(data.getPowerFactor()/1000));
			} else {
				tvScrollviewInverter.setVisibility(View.GONE);
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
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
	}

}
