package com.sojoline.solar.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sojoline.base.view.BaseFragment;
import com.sojoline.base.widget.DrawableCenterTextView;
import com.sojoline.model.bean.solar.CombinerInfo;
import com.sojoline.model.bean.solar.InverterInfo;
import com.sojoline.model.bean.solar.SolarDevice;
import com.sojoline.model.bean.solar.TransformerInfo;
import com.sojoline.model.storage.AppInfoPreferences;
import com.sojoline.presenter.solar.data.SolarDeviceContract;
import com.sojoline.presenter.solar.data.SolarDevicePresenter;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;
import com.sojoline.solar.view.activity.DeviceListActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/10/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarDeviceFragment extends BaseFragment implements SolarDeviceContract.View {
	@BindView(R2.id.tv_transformer_total)
	TextView 				tvTransformerTotal;
	@BindView(R2.id.tv_transformer_running)
	DrawableCenterTextView 	tvTransformerRunning;
	@BindView(R2.id.tv_transformer_warning)
	DrawableCenterTextView 	tvTransformerWarning;
	@BindView(R2.id.tv_transformer_off)
	DrawableCenterTextView 	tvTransformerOff;
	@BindView(R2.id.tv_inverter_total)
	TextView 				tvInverterTotal;
	@BindView(R2.id.tv_inverter_running)
	DrawableCenterTextView 	tvInverterRunning;
	@BindView(R2.id.tv_inverter_warning)
	DrawableCenterTextView 	tvInverterWarning;
	@BindView(R2.id.tv_inverter_off)
	DrawableCenterTextView 	tvInverterOff;
	@BindView(R2.id.tv_combiner_total)
	TextView 				tvCombinerTotal;
	@BindView(R2.id.tv_combiner_running)
	DrawableCenterTextView 	tvCombinerRunning;
	@BindView(R2.id.tv_combiner_warning)
	DrawableCenterTextView 	tvCombinerWarning;
	@BindView(R2.id.tv_combiner_off)
	DrawableCenterTextView 	tvCombinerOff;

	private SolarDevicePresenter presenter;
	private ArrayList<TransformerInfo> transformers;
	private ArrayList<InverterInfo> inverters;
	private ArrayList<CombinerInfo> combiners;

	public static SolarDeviceFragment newInstance() {
		Bundle args = new Bundle();
		SolarDeviceFragment fragment = new SolarDeviceFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.solar_fragment_device, container, false);
	}

	@Override
	protected void initPresenter() {
		super.initPresenter();
		presenter = new SolarDevicePresenter();
		presenter.attachView(this);
	}

	@Override
	protected void initView(@Nullable Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		String id = AppInfoPreferences.get().getStationId();
		//发起请求
		presenter.getSolarDevices(id);
	}

	@OnClick({R2.id.ll_transformer, R2.id.ll_inverter, R2.id.ll_combiner})
	public void onViewClicked(View view) {
		if (view.getId() == R.id.ll_transformer) {
			DeviceListActivity.navigation(DeviceListActivity.DEVICE_TRANSFORMER, transformers);
		} else if (view.getId() == R.id.ll_inverter) {
			DeviceListActivity.navigation(DeviceListActivity.DEVICE_INVERTER, inverters);
		} else if (view.getId() == R.id.ll_combiner) {
			DeviceListActivity.navigation(DeviceListActivity.DEVICE_COMBINER, combiners);
		}
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
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
	public void success(SolarDevice data) {
		if (data != null) {
			combiners = (ArrayList<CombinerInfo>) data.getCombinerList();
			inverters = (ArrayList<InverterInfo>) data.getInverterList();
			transformers = (ArrayList<TransformerInfo>) data.getTransformerList();

			tvCombinerOff.setText(data.getCombinerOffLineNum() + "");
			tvCombinerRunning.setText(data.getCombinerRunningNum() + "");
			tvCombinerWarning.setText(data.getCombinerAlarmNum() + "");
			tvCombinerTotal.setText("共" + data.getTotalCombiner() + "个");

			tvInverterOff.setText(data.getInverterOffLineNum() + "");
			tvInverterRunning.setText(data.getInverterRunningNum() + "");
			tvInverterWarning.setText(data.getInverterAlarmNum() + "");
			tvInverterTotal.setText("共" + data.getTotalInverters() + "个");

			tvTransformerOff.setText(data.getTransformerFaultNum() + "");
			tvTransformerRunning.setText(data.getTransformerRunningNum() + "");
			tvTransformerWarning.setText(data.getTransformerAlarmNum() + "");
			tvTransformerTotal.setText("共" + data.getTotalTransformer() + "个");
		}
	}
}
