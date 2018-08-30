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
	@BindView(R2.id.ScrollviewInverter10)
	ScrollView  tvScrollviewInverter10;
	@BindView(R2.id.ScrollviewInverter13)
	ScrollView  tvScrollviewInverter13;
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
	@BindView(R2.id.tv_StartTime)
	TextView tvStartTime;
	@BindView(R2.id.tv_ShutdownTime)
	TextView tvShutdownTime;
	@BindView(R2.id.tv_InverterEfficiency)
	TextView tvInverterEfficiency;
	@BindView(R2.id.tv_InverterEfficiency13)
	TextView tvInverterEfficiency13;
	@BindView(R2.id.tv_CO2Emission13)
	TextView tvCO2Emission;
	@BindView(R2.id.tv_Invercode)
	TextView tvInvercode;
	@BindView(R2.id.tv_Time)
	TextView tvTime;
	@BindView(R2.id.tv_InverterState)
	TextView tvInverterState;
	@BindView(R2.id.tv_InverterTem)
	TextView tvInverterTem;
	@BindView(R2.id.tv_Invercode13)
	TextView tvInvercode13;
	@BindView(R2.id.tv_Time13)
	TextView tvTime13;
	@BindView(R2.id.tv_InverterState13)
	TextView tvInverterState13;
	@BindView(R2.id.tv_InverterTem13)
	TextView tvInverterTem13;
	@BindView(R2.id.tv_Invercode10)
	TextView tvInvercode10;
	@BindView(R2.id.tv_Time10)
	TextView tvTime10;
	@BindView(R2.id.tv_InverterState10)
	TextView tvInverterState10;
	@BindView(R2.id.tv_InverterTem10)
	TextView tvInverterTem10;
	@BindView(R2.id.tv_InverterDayElec)
	TextView tvInverterDayElec;
	@BindView(R2.id.tv_InverterDayElec13)
	TextView tvInverterDayElec13;
	@BindView(R2.id.tv_InverterDayElec10)
	TextView tvInverterDayElec10;
	@BindView(R2.id.tv_InverterMonthElec)
	TextView tvInverterMonthElec;
	@BindView(R2.id.tv_InverterMonthElec10)
	TextView tvInverterMonthElec10;
//	tvInverterYearElec
    @BindView(R2.id.tv_InverterYearElec10)
    TextView tvInverterYearElec10;
//    tvInputTotalPower
    @BindView(R2.id.tv_InputTotalPower10)
    TextView tvInputTotalPower10;
	@BindView(R2.id.tv_InputTotalPower13)
	TextView tvInputTotalPower13;
//    tvDayRunTime
    @BindView(R2.id.tv_DayRunTime13)
    TextView tvDayRunTime13;

	@BindView(R2.id.tv_VoltageA)
	TextView tvVoltageA;
	@BindView(R2.id.tv_VoltageB)
	TextView tvVoltageB;
	@BindView(R2.id.tv_VoltageC)
	TextView tvVoltageC;

	@BindView(R2.id.tv_VoltageA10)
	TextView tvVoltageA10;
	@BindView(R2.id.tv_VoltageB10)
	TextView tvVoltageB10;
	@BindView(R2.id.tv_VoltageC10)
	TextView tvVoltageC10;
	@BindView(R2.id.tv_VoltageA13)
	TextView tvVoltageA13;
	@BindView(R2.id.tv_VoltageB13)
	TextView tvVoltageB13;
	@BindView(R2.id.tv_VoltageC13)
	TextView tvVoltageC13;
	@BindView(R2.id.tv_CurrentA13)
	TextView tvCurrentA13;
	@BindView(R2.id.tv_CurrentB13)
	TextView tvCurrentB13;
	@BindView(R2.id.tv_CurrentC13)
	TextView tvCurrentC13;
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
	@BindView(R2.id.tv_DcVoltageOne13)
	TextView tvDcVoltageOne13;
	@BindView(R2.id.tv_DcCurrentOne13)
	TextView tvDcCurrentOne13;
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
	@BindView(R2.id.tv_TotalOperatingTime13)
	TextView tvTotalOperatingTime13;
	@BindView(R2.id.tv_PV1InputV)
	TextView tvPV1InputV;
	@BindView(R2.id.tv_PV2InputV)
	TextView tvPV2InputV;
	@BindView(R2.id.tv_PV3InputV)
	TextView tvPV3InputV;
	@BindView(R2.id.tv_PV4InputV)
	TextView tvPV4InputV;
	@BindView(R2.id.tv_PV5InputV)
	TextView tvPV5InputV;
	@BindView(R2.id.tv_PV6InputV)
	TextView tvPV6InputV;

	@BindView(R2.id.tv_ApparentPower13)
	TextView tvApparentPowerN13;
	@BindView(R2.id.tv_ReactivePower13)
	TextView tvReactivePowerN13;
	@BindView(R2.id.tv_InputPower13)
	TextView tvInputPower;
	@BindView(R2.id.tv_OutputPower13)
	TextView tvOutputPower13;
	@BindView(R2.id.tv_TotalElectric13)
	TextView tvTotalElectric13;
	@BindView(R2.id.tv_PV1InputA10)
	TextView tvPV1InputA10;
	@BindView(R2.id.tv_PV2InputA10)
	TextView tvPV2InputA10;
	@BindView(R2.id.tv_PV3InputA10)
	TextView tvPV3InputA10;
	@BindView(R2.id.tv_PV4InputA10)
	TextView tvPV4InputA10;
	@BindView(R2.id.tv_PV5InputA10)
	TextView tvPV5InputA10;
	@BindView(R2.id.tv_PV6InputA10)
	TextView tvPV6InputA10;
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
	@BindView(R2.id.tv_PowerFactor13)
	TextView tvPowerFactor13;
	@BindView(R2.id.tv_PowerFactor10)
	TextView tvPowerFactor10;
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
				tvScrollviewInverter10.setVisibility(View.GONE);
				tvScrollviewInverter13.setVisibility(View.GONE);
				tvInvercode.setText(data.getInverterId());
				tvTime.setText(data.getJoinTime());

				tvInverterState.setText(data.getInverterState());//运行状态

				tvInverterTem.setText(data.getInverterTem()/10+"℃");
				tvInverterDayElec.setText(data.getInverterDayElec()/1000+"kWh");
				tvInverterMonthElec.setText(data.getInverterMonthElec()/100+"kWh");
				tvVoltageA.setText(data.getVoltageA()/10+"V");
				tvVoltageB.setText(data.getVoltageB()/10+"V");
				tvVoltageC.setText(data.getVoltageC()/10+"V");
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

			} else if (data.getProtocolId().equals("013")) {
				tvScrollview.setVisibility(View.GONE);
				tvScrollviewInverter10.setVisibility(View.GONE);
				tvScrollviewInverter.setVisibility(View.GONE);
				tvInvercode13.setText(data.getInverterId());
				tvTime13.setText(data.getJoinTime());

				tvInverterState13.setText(data.getInverterState13());//运行状态

				tvInverterTem13.setText(data.getInverterTem()/10+"℃");
				tvInverterDayElec13.setText(data.getInverterDayElec()/1000+"kWh");
				tvInputTotalPower13.setText(data.getInputTotalPower()+"kW");

				tvInverterEfficiency13.setText(data.getInverterEfficiency()+"");
				tvVoltageA13.setText(data.getVoltageA()/10+"V");
				tvVoltageB13.setText(data.getVoltageB()/10+"V");
				tvVoltageC13.setText(data.getVoltageC()/10+"V");
				tvCurrentA13.setText(data.getCurrentA()+"A");
				tvCurrentB13.setText(data.getCurrentB()+"A");
				tvCurrentC13.setText(data.getCurrentC()+"A");
//				CO2减排
				tvCO2Emission.setText(data.getCO2Emission()+"kg");
//				日运行时间
				tvDayRunTime13.setText(data.getDayRunTime()+"min");
//				输出功率  OutputPower
				tvOutputPower13.setText(data.getOutputPower()+"Kwh");
//				发电功率 InputPower
				tvInputPower.setText(data.getInputPower()+"Kwh");
//				TotalElectric  总发电量
				tvTotalElectric13.setText(data.getTotalElectric()+"KWh");
				tvDcVoltageOne13.setText(data.getDcVoltageOne()+"V");
				tvDcCurrentOne13.setText(data.getDcCurrentOne()+"A");
				tvReactivePowerN13.setText(data.getReactivePower() + "kW");
				tvApparentPowerN13.setText(data.getApparentPower() + "kW");
				tvPowerFactor13.setText(String.valueOf(data.getPowerFactor()/1000));
				tvTotalOperatingTime13.setText(data.getTotalOperatingTime()+"h");

			} else if (data.getProtocolId().equals("010")) {
				tvScrollview.setVisibility(View.GONE);
				tvScrollviewInverter.setVisibility(View.GONE);
				tvScrollviewInverter13.setVisibility(View.GONE);
				tvInvercode10.setText(data.getInverterId());
				tvTime10.setText(data.getJoinTime());

				tvInverterState10.setText(data.getInverterState10());//运行状态

				tvInverterTem10.setText(data.getInverterTem()/10+"℃");
				tvInputTotalPower10.setText(data.getInputTotalPower()+"kW");
				tvInverterDayElec10.setText(data.getInverterDayElec()/100+"kWh");
				tvInverterMonthElec10.setText(data.getInverterMonthElec()/100+"kWh");
				tvInverterYearElec10.setText(data.getInverterYearElec()/100+"kWh");
				tvStartTime.setText( data.getStartTime());
				tvShutdownTime.setText(data.getShutdownTime());
				tvInverterEfficiency.setText(data.getInverterEfficiency()/100+"");
				tvVoltageA10.setText(data.getVoltageA()/10+"V");
				tvVoltageB10.setText(data.getVoltageB()/10+"V");
				tvVoltageC10.setText(data.getVoltageC()/10+"V");
				tvPV1InputA10.setText(data.getPV1InputA()/10+"A");
				tvPV2InputA10.setText(data.getPV2InputA()/10+"A");
				tvPV3InputA10.setText(data.getPV3InputA()/10+"A");
				tvPV4InputA10.setText(data.getPV4InputA()/10+"A");
				tvPV5InputA10.setText(data.getPV5InputA()/10+"A");
				tvPV6InputA10.setText(data.getPV6InputA()/10+"A");
				tvPV1InputV.setText(data.getPV1InputV()/10+"V");
				tvPV2InputV.setText(data.getPV2InputV()/10+"V");
				tvPV3InputV.setText(data.getPV3InputV()/10+"V");
				tvPV4InputV.setText(data.getPV4InputV()/10+"V");
				tvPV5InputV.setText(data.getPV5InputV()/10+"V");
				tvPV6InputV.setText(data.getPV6InputV()/10+"V");
				tvPowerFactor10.setText(String.valueOf(data.getPowerFactor()/1000));
			} else {
				tvScrollviewInverter.setVisibility(View.GONE);
				tvScrollviewInverter10.setVisibility(View.GONE);
				tvScrollviewInverter13.setVisibility(View.GONE);
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
