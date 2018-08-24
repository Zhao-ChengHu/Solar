package com.sojoline.solar.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.bean.solar.MonitorDate;
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
@Route(path = "/solar/login/powermeter")
public class PowermeterActivity extends BaseCompatActivity implements DeviceDataContract.View {
	@BindView(R2.id.tv_code)
	TextView tvcode;
	@BindView(R2.id.tv_updateTime)
	TextView tvTime;
	@BindView(R2.id.tv_APhaseCurrent)
	TextView tvAPhaseCurrent;//相电流
	@BindView(R2.id.tv_BPhaseCurrent)
	TextView tvBPhaseCurrent;//B相电流
	@BindView(R2.id.tv_CPhaseCurrent)
	TextView tvCPhaseCurrent;//C相电流
	@BindView(R2.id.tv_AveragePhaseCurrent)
	TextView tvAveragePhaseCurrent;//平均相电流
	@BindView(R2.id.tv_APhaseVoltage)
	TextView tvAPhaseVoltage;//A相电压
	@BindView(R2.id.tv_BPhaseVoltage)
	TextView tvBPhaseVoltage;//B相电压
	@BindView(R2.id.tv_CPhaseVoltage)
	TextView tvCPhaseVoltage;
	@BindView(R2.id.tv_AveragePhaseVoltage)
	TextView tvAveragePhaseVoltage;//平均相电压
	@BindView(R2.id.tv_NegativeSequenceCurrent)
	TextView tvNegativeSequenceCurrent;//负序电流
	@BindView(R2.id.tv_AbPhaseLineVoltage)
	TextView tvAbPhaseLineVoltage;//AB相电压
	@BindView(R2.id.tv_BcPhaseLineVoltage)
	TextView tvBcPhaseLineVoltage;//BC相电ya
	@BindView(R2.id.tv_CaPhaseLineVoltage)
	TextView tvCaPhaseLineVoltage;//CA相电ya
	@BindView(R2.id.tv_AveragePhaseLineVoltage)
	TextView tvAveragePhaseLineVoltage;//pinjun相电压

	@BindView(R2.id.tv_TotalActivePower)
	TextView tvTotalActivePower;//总有功功率
	@BindView(R2.id.tv_TotalReactivePower)
	TextView tvTotalReactivePower;//总无功功率
	@BindView(R2.id.tv_TotalApparentPower)
	TextView tvTotalApparentPower;//总是在功率
	@BindView(R2.id.tv_AveragePowerFactor)
	TextView tvAveragePowerFactor;//平均功率因数
//	@BindView(R2.id.tv_Frequency)
//	TextView tvFrequency;//频率
	@BindView(R2.id.tv_AActivePower)
	TextView tvAActivePower;//a相有功功率
	@BindView(R2.id.tv_AReactivePower)
	TextView tvAReactivePower;//A相无功
	@BindView(R2.id.tv_AApparentPower)
	TextView tvAApparentPower;//A相是在功率
	@BindView(R2.id.tv_APowerFactor)
	TextView tvAPowerFactor;//A相功率因数
	@BindView(R2.id.tv_BActivePower)
	TextView tvBActivePower;//B相有功功率
	@BindView(R2.id.tv_BReactivePower)
	TextView tvBReactivePower;//B相无功
	@BindView(R2.id.tv_BApparentPower)
	TextView tvBApparentPower;//B相是在功率
	@BindView(R2.id.tv_BPowerFactor)
	TextView tvBPowerFactor;//B相功率因数
	@BindView(R2.id.tv_CActivePower)
	TextView tvCActivePower;//C相有功功率
	@BindView(R2.id.tv_CReactivePower)
	TextView tvCReactivePower;//C相无功
	@BindView(R2.id.tv_CApparentPower)
	TextView tvCApparentPower;//C相是在功率
	@BindView(R2.id.tv_CPowerFactor)
	TextView tvCPowerFactor;//C相功率因数

	@BindView(R2.id.tv_PhaseSequence)
	TextView tv_PhaseSequence;//相序
	@BindView(R2.id.tv_PositiveActiveEnergyLow)
	TextView tvPositiveActiveEnergyLow;//正向有功电能
	@BindView(R2.id.tv_PositiveReactiveEnergyLow)
	TextView tvPositiveReactiveEnergyLow;//正向无功电能

	private DeviceDataPresenter presenter;
	private Subscription subscription;
	private String id;

	public static void navigation(String id) {
		ARouter.getInstance().build("/solar/login/powermeter")
				.withString("id", id).
				navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.solar_activity_powermeter);
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
		initToolbarNav("多功能电力仪");

		id = getIntent().getStringExtra("id");
		presenter.getPowermeterData(id);
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
		PowermeterData  data = (PowermeterData) o;
		if (data != null) {
			tvcode.setText(data.getElectricPowerMeterID());
			tvTime.setText(data.getJoinTime());
			//tvDirectRadiationInstant.setText(data.getDirectRadiationInstant()/1000 + "W/㎡");

			tvAPhaseCurrent.setText(data.getAPhaseCurrent()/10000+"A");
			tvBPhaseCurrent.setText(data.getBPhaseCurrent()/10000+"A");
			tvCPhaseCurrent.setText(data.getCPhaseCurrent()/10000+"A");
			tvAveragePhaseCurrent.setText(data.getAveragePhaseCurrent()/10000+"A");
			tvAPhaseVoltage.setText(data.getAPhaseVoltage()/100+"V");
			tvBPhaseVoltage.setText(data.getBPhaseVoltage()/100+"V");
			tvCPhaseVoltage.setText(data.getCPhaseVoltage()/100+"V");
			tvAveragePhaseVoltage.setText(data.getAveragePhaseVoltage()/100+"V");
			tvNegativeSequenceCurrent.setText(data.getNegativeSequenceCurrent()/10000+"A");
			tvAbPhaseLineVoltage.setText(data.getAbPhaseLineVoltage()/100+"V");
			tvBcPhaseLineVoltage.setText(data.getBcPhaseLineVoltage()/100+"V");
			tvCaPhaseLineVoltage.setText(data.getCaPhaseLineVoltage()/100+"V");
			tvAveragePhaseLineVoltage.setText(data.getAveragePhaseLineVoltage()/100+"V");
			tvTotalActivePower.setText(data.getTotalActivePower()/10+"kW");
			tvTotalReactivePower.setText(data.getTotalReactivePower()/10+"Kvar");
			tvTotalApparentPower.setText(data.getTotalApparentPower()/10+"Kv.a");
			tvAveragePowerFactor.setText(String.valueOf(data.getAveragePowerFactor()/10000));
			tvAActivePower.setText(data.getAActivePower()/10000+"KW");
			tvAReactivePower.setText(data.getAReactivePower()/10000+"Kvar");
			tvAApparentPower.setText(data.getAApparentPower()/10000+"Kv.a");
			tvAPowerFactor.setText(String.valueOf(data.getAPowerFactor()/10000));

			tvBActivePower.setText(data.getBActivePower()/10000+"KW");
			tvBReactivePower.setText(data.getBReactivePower()/10000+"Kvar");
			tvBApparentPower.setText(data.getBApparentPower()/10000+"Kv.a");
			tvBPowerFactor.setText(String.valueOf(data.getBPowerFactor()/10000));

			tvCActivePower.setText(data.getCActivePower()/10000+"KW");
			tvCReactivePower.setText(data.getCReactivePower()/10000+"Kvar");
			tvCApparentPower.setText(data.getCApparentPower()/10000+"Kv.a");
			tvCPowerFactor.setText(String.valueOf(data.getCPowerFactor()/10000));
			tv_PhaseSequence.setText(String.valueOf(data.getPhaseSequence()));

			tvPositiveActiveEnergyLow.setText(data.getPositiveActiveEnergyLow()+"KWH");
			tvPositiveReactiveEnergyLow.setText(data.getPositiveReactiveEnergyLow()+"KWH");
		}
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
	}
}
