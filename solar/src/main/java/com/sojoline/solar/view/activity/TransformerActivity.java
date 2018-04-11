package com.sojoline.solar.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.bean.solar.TransformerData;
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
@Route(path = "/solar/login/transformer")
public class TransformerActivity extends BaseCompatActivity implements DeviceDataContract.View {

	@BindView(R2.id.tv_code)
	TextView tvCode;
	@BindView(R2.id.tv_state)
	TextView tvState;
	@BindView(R2.id.tv_updateTime)
	TextView tvUpdateTime;
	@BindView(R2.id.tv_f)
	TextView tvF;
	@BindView(R2.id.tv_p)
	TextView tvP;
	@BindView(R2.id.tv_q)
	TextView tvQ;
	@BindView(R2.id.tv_ReactPower)
	TextView tvReactPower;
	@BindView(R2.id.tv_ActPower)
	TextView tvActPower;
	@BindView(R2.id.tv_InputState3)
	TextView tvInputState3;
	@BindView(R2.id.tv_InputState4)
	TextView tvInputState4;
	@BindView(R2.id.tv_InputState5)
	TextView tvInputState5;

	private DeviceDataPresenter presenter;
	private String id;

	public static void navigation(String id) {
		ARouter.getInstance().build("/solar/login/transformer")
				.withString("id", id)
				.navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.solar_activity_transformer);
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
		initToolbarNav("箱变");

		id = getIntent().getStringExtra("id");
		presenter.getTransformerData(id);
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
						presenter.getTransformerData(id);
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
		TransformerData data = (TransformerData) o;
		if (data != null) {
			tvCode.setText(data.getTransformerId());
			tvState.setText(data.getDeviceState());
			tvUpdateTime.setText(data.getJoinTime());
			tvF.setText(String.format(Locale.getDefault(), "%.1f", data.getF() / 100f));
			tvP.setText(data.getP1() + "");
			tvQ.setText(data.getQ1() + "");
			tvActPower.setText(data.getActPower() + "");
			tvReactPower.setText(data.getReactPower() + "");
			tvInputState3.setText(data.getInputState3());
			tvInputState4.setText(data.getInputState4());
			tvInputState5.setText(data.getInputState5());
		}
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
	}

}
