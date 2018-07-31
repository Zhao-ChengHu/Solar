package com.sojoline.monitor.view.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.sojoline.base.util.AppUtils;
import com.sojoline.base.util.ScreenUtils;
import com.sojoline.base.view.BaseFragment;
import com.sojoline.base.widget.DeviceType;
import com.sojoline.base.widget.SimpleMarkerView;
import com.sojoline.basiclib.DebugLog;
import com.sojoline.basiclib.util.DateUtils;
import com.sojoline.model.bean.solar.CombinerInfo;
import com.sojoline.model.bean.solar.InverterInfo;
import com.sojoline.model.bean.solar.MonitorInfo;
import com.sojoline.model.bean.solar.SolarDevice;
import com.sojoline.model.bean.solar.TransformerInfo;
import com.sojoline.model.db.DateElectric;
import com.sojoline.model.request.SolarEnergyRequest;
import com.sojoline.model.storage.AppInfoPreferences;
import com.sojoline.monitor.R;
import com.sojoline.monitor.R2;
import com.sojoline.monitor.widget.DeviceMenuView;
import com.sojoline.presenter.solar.data.SolarDeviceContract;
import com.sojoline.presenter.solar.data.SolarDevicePresenter;
import com.sojoline.presenter.solar.data.SolarEnergyContract;
import com.sojoline.presenter.solar.data.SolarEnergyPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MonitorSolarFragment extends BaseFragment implements SolarDeviceContract.View, SolarEnergyContract.View{
	@BindView(R2.id.tv_device)
	TextView tvDevice;
	@BindView(R2.id.lineChart)
	LineChart lineChart;
	@BindView(R2.id.tv_total_energy)
	TextView tvTotalEnergy;
	@BindView(R2.id.tv_energy)
	TextView tvEnergy;
	@BindView(R2.id.tv_total_CO2)
	TextView tvTotalCO2;
	@BindView(R2.id.tv_CO2)
	TextView tvCO2;
	@BindView(R2.id.tv_total_profit)
	TextView tvTotalProfit;
	@BindView(R2.id.tv_profit)
	TextView tvProfit;
	@BindView(R2.id.tv_temp)
	TextView tvTemp;
	@BindView(R2.id.tv_humidity)
	TextView tvHumidity;
	@BindView(R2.id.tv_sunshine)
	TextView tvSunshine;
	@BindView(R2.id.tv_wind)
	TextView tvWind;

	private ArrayList<TransformerInfo> transformers;
	private ArrayList<InverterInfo> inverters;
	private ArrayList<CombinerInfo> combiners;
	private ArrayList<MonitorInfo> monitors;
	private SolarDevicePresenter presenter;
	private SolarEnergyPresenter energyPresenter;
	private DeviceMenuView menuView;
	private XAxis lineXAxis;

	public static MonitorSolarFragment newInstance() {
		Bundle args = new Bundle();
		MonitorSolarFragment fragment = new MonitorSolarFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.monitor_fragment_solar, container, false);
	}

	@Override
	protected void initPresenter() {
		super.initPresenter();
		presenter = new SolarDevicePresenter();
		presenter.attachView(this);
		energyPresenter = new SolarEnergyPresenter();
		energyPresenter.attachView(this);
	}

	@Override
	protected void initView(@Nullable Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		initLineChart();
		String id = AppInfoPreferences.get().getStationId();
		//发起请求
//		presenter.getSolarDevices(id);
		SolarEnergyRequest request = new SolarEnergyRequest();
		request.setDPStationID(id);
		request.setQueryType("day");
		int year = DateUtils.getYear();
		int month = DateUtils.getMonth();
		int day = DateUtils.getDay();
		request.setQueryTime(String.format(Locale.CHINA,"%d-%s-%s", year, getFormat(month), getFormat(day)));
//		energyPresenter.getDayEnergy(request);

		tvDevice.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//创建选择菜单并显示
				menuView = new DeviceMenuView(getContext());
				menuView.setListener(new DeviceMenuView.OnDeviceClickListener() {
					@Override
					public void onClick(DeviceType type) {
						if (type == DeviceType.INVERTER){
							ARouter.getInstance().build("/solar/login/device_list")
									.withInt("device",0x1)
									.withParcelableArrayList("object", inverters)
									.navigation();
						}else if (type == DeviceType.TRANSFORMER){
							ARouter.getInstance().build("/solar/login/device_list")
									.withInt("device",0x0)
									.withParcelableArrayList("object", transformers)
									.navigation();
						}else if (type == DeviceType.COMBINER){
							ARouter.getInstance().build("/solar/login/device_list")
									.withInt("device",0x2)
									.withParcelableArrayList("object", combiners)
									.navigation();
						}else if (type == DeviceType.MONITOR){
							ARouter.getInstance().build("/solar/login/device_list")
									.withInt("device",0x2)
									.withParcelableArrayList("object", monitors)
									.navigation();
						}else {
							//告警
							ARouter.getInstance().build("/solar/login/warning_list").navigation();
						}
					}
				});
				menuView.show(_mActivity);
			}
		});
	}

	/**
	 * 初始化LineChart
	 */
	private void initLineChart(){
		lineChart.getDescription().setEnabled(false);
		lineChart.setScaleEnabled(false);
		lineChart.setNoDataText("没有数据");
		lineChart.setExtraLeftOffset(ScreenUtils.dp2px(5));

		SimpleMarkerView smv = new SimpleMarkerView(getContext(), R.layout.monitor_view_marker);
		smv.setChartView(lineChart);
		lineChart.setMarker(smv);

		lineXAxis = lineChart.getXAxis();
		lineXAxis.setTextSize(11f);
		lineXAxis.setTextColor(Color.BLACK);
		lineXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
		lineXAxis.setDrawAxisLine(true);
		lineXAxis.setDrawGridLines(false);
		lineXAxis.setValueFormatter(new IAxisValueFormatter() {
			@Override
			public String getFormattedValue(float value, AxisBase axis) {
				DebugLog.log("LineChart x value:" + value);
				return (int)value + "";
			}
		});

		YAxis leftAxis = lineChart.getAxisLeft();
		leftAxis.setTextColor(Color.BLACK);
		leftAxis.setDrawAxisLine(true);
		leftAxis.setDrawGridLines(false);
		leftAxis.setDrawZeroLine(false);

		YAxis rightAxis = lineChart.getAxisRight();
		rightAxis.setEnabled(false);

		Legend legend = lineChart.getLegend();
		legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
		legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
		legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
		legend.setDrawInside(false);
		legend.setForm(Legend.LegendForm.SQUARE);
		legend.setFormSize(11f);

	}

	/**
	 * 填充日期格式，把一位变成两位：“1”--“01”
	 * @param num 日期
	 * @return string
	 */
	private String getFormat(int num){
		if (num > 9){
			return num + "";
		}else {
			return "0" + num;
		}
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
		if (data != null){
			combiners = (ArrayList<CombinerInfo>) data.getCombinerList();
			inverters = (ArrayList<InverterInfo>) data.getInverterList();
			transformers = (ArrayList<TransformerInfo>) data.getTransformerList();
			monitors = (ArrayList <MonitorInfo>) data.getMonitorList();
		}
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
		energyPresenter.detachView();
	}

	@Override
	public void onSupportInvisible() {
		super.onSupportInvisible();
		if (menuView != null && menuView.isShowing()){
			menuView.hide();
		}
	}

	@Override
	public void energySuccess(Object o) {
		List<DateElectric> list = (List<DateElectric>) o;
		List<Entry> entries = new ArrayList<>();
		if (list != null && list.size() > 0){
			if (list.size() > 1){
				Collections.sort(list);
			}

			for (DateElectric electric : list){
				entries.add(new Entry(electric.getNum(), electric.getElectric()));
			}

		}else {
			//没有数据时填充0
			for (int i = 0; i < 12; i++){
				entries.add(new Entry(i, 0));
			}
		}

		//图表设置数据
		LineDataSet set;
		if (lineChart.getData() != null &&
				lineChart.getData().getDataSetCount() > 0){
			set = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
			set.setValues(entries);
			lineChart.getData().notifyDataChanged();
			lineChart.notifyDataSetChanged();
		}else {
			set = new LineDataSet(entries, "发电曲线");
			set.setDrawValues(false);
			set.setColor(Color.RED);
			set.setDrawCircles(false);
			set.setDrawCircleHole(false);
			set.setDrawFilled(true);
			set.setMode(LineDataSet.Mode.CUBIC_BEZIER);

			if (AppUtils.getSDKInt() > 18){
				Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.fade_red);
				set.setFillDrawable(drawable);
			}else {
				set.setFillColor(0xffffc1c1);
			}

			ArrayList<ILineDataSet> dataSets = new ArrayList<>();
			dataSets.add(set);
			LineData data = new LineData(dataSets);
			lineChart.setData(data);
		}
	}
}
