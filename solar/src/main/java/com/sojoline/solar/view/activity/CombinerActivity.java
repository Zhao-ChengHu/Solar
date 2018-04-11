package com.sojoline.solar.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.bean.solar.CombinerData;
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
 *     @author : 李小勇
 *     date   : 2017/11/03
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Route(path = "/solar/login/combiner")
public class CombinerActivity extends BaseCompatActivity implements DeviceDataContract.View {
	@BindView(R2.id.tv_code)
	TextView tvCode;
	@BindView(R2.id.tv_updateTime)
	TextView tvUpdateTime;
	@BindView(R2.id.tv_voltage)
	TextView tvVoltage;
	@BindView(R2.id.tv_TotalCurrent)
	TextView tvTotalCurrent;
	@BindView(R2.id.tv_ExternalTemp)
	TextView tvExternalTemp;
	@BindView(R2.id.tv_TotalInverseCurrent)
	TextView tvTotalInverseCurrent;
	@BindView(R2.id.tv_LeakageCurrent)
	TextView tvLeakageCurrent;
	@BindView(R2.id.tv_TotalPower)
	TextView tvTotalPower;
	@BindView(R2.id.tv_NoteBusVoltage)
	TextView tvNoteBusVoltage;
	@BindView(R2.id.tv_NoteExTemp)
	TextView tvNoteExTemp;
	@BindView(R2.id.tv_NoteChannelCount)
	TextView tvNoteChannelCount;
	@BindView(R2.id.tv_NoteWirelessDistance)
	TextView tvNoteWirelessDistance;
	@BindView(R2.id.tv_WirelessConnectCount)
	TextView tvWirelessConnectCount;

	private DeviceDataPresenter presenter;
	private Subscription subscription;
	private String id;

	public static void navigation(String id) {
		ARouter.getInstance().build("/solar/login/combiner")
				.withString("id", id).
				navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.solar_activity_combiner);
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
		initToolbarNav("汇流箱");

		id = getIntent().getStringExtra("id");
		presenter.getCombinerData(id);
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
		CombinerData data = (CombinerData) o;
		if (data != null) {
			tvCode.setText(data.getCombinerId());
			tvUpdateTime.setText(data.getJoinTime());
			tvVoltage.setText(data.getVoltage() + "V");
			tvTotalCurrent.setText(data.getTotalCurrent() + "A");
			tvExternalTemp.setText(data.getExternalTemp() + "℃");
			tvTotalInverseCurrent.setText(data.getTotalInverseCurrent() + "A");
			tvLeakageCurrent.setText(data.getLeakageCurrent() + "");
			tvTotalPower.setText(data.getTotalPower() + "kW");
			tvNoteBusVoltage.setText(data.getNoteBusVoltage() + "V");
			tvNoteExTemp.setText(data.getNoteExTemp() + "℃");
			tvNoteChannelCount.setText(data.getNoteChannelCount() + "");
			tvNoteWirelessDistance.setText(data.getNoteWirelessDistance() + "");
			tvWirelessConnectCount.setText(data.getWirelessConnCount() + "");
		}
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
	}
}
