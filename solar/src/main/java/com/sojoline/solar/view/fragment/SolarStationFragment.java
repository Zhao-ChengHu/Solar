package com.sojoline.solar.view.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.sojoline.base.util.AppUtils;
import com.sojoline.base.view.BaseFragment;
import com.sojoline.base.widget.SimpleMarkerView;
import com.sojoline.model.bean.solar.SolarData;
import com.sojoline.model.db.DateElectric;
import com.sojoline.model.storage.AppInfoPreferences;
import com.sojoline.presenter.solar.data.SolarDataContract;
import com.sojoline.presenter.solar.data.SolarDataPresenter;
import com.sojoline.presenter.solar.data.SolarEnergyContract;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;
import com.sojoline.solar.widget.MsgDialog;
import com.sojoline.solar.widget.PowerCapacityView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/10/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarStationFragment extends BaseFragment implements SolarDataContract.View, SolarEnergyContract.View{
	@BindView(R2.id.capacity_view)
	PowerCapacityView capacityView;
	@BindView(R2.id.tv_station_name)
	TextView tvStationName;
	@BindView(R2.id.tv_power)
	TextView tvPower;
	@BindView(R2.id.tv_energy)
	TextView tvEnergy;
	@BindView(R2.id.tv_co2)
	TextView tvCo2;
	@BindView(R2.id.tv_profit)
	TextView tvProfit;
	@BindView(R2.id.tv_total_energy)
	TextView tvTotalEnergy;
	@BindView(R2.id.tv_total_profit)
	TextView tvTotalProfit;
	@BindView(R2.id.tv_total_co2)
	TextView tvTotalCO2;
	@BindView(R2.id.tv_total_tree)
	TextView tvTotalTree;
	@BindView(R2.id.lineChart)
	LineChart lineChart;
	@BindView(R2.id.iv_3)
	ImageView ivCO2;
	@BindView(R2.id.iv_4)
	ImageView ivTree;

	private SolarDataPresenter dataPresenter;

	public static SolarStationFragment newInstance() {
		Bundle args = new Bundle();
		SolarStationFragment fragment = new SolarStationFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.solar_fragment_station, container, false);
	}

	@Override
	protected void initPresenter() {
		super.initPresenter();
		dataPresenter = new SolarDataPresenter();
		dataPresenter.attachView(this);
	}

	@Override
	protected void initView(@Nullable Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		initChart();
		String id = AppInfoPreferences.get().getStationId();
		//发起请求
		dataPresenter.getSolarStationData(id);

		capacityView.setCapacity(60);

		ivCO2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MsgDialog dialog = new MsgDialog(getContext(),"CO2减排",
						"根据统计：每节约1度(1kWh)电,可减少0.997千克二氧化碳污染排放。",
						"累计CO2排放量=累计发电量×0.997");
				dialog.setCanceledOnTouchOutside(true);
				dialog.show();
			}
		});

		ivTree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MsgDialog dialog = new MsgDialog(getContext(),"树木种植",
						"根据统计：每棵树平均吸收1800千克二氧化碳。",
						"累计种植=累计CO2排放量÷1800");
				dialog.setCanceledOnTouchOutside(true);
				dialog.show();
			}
		});
	}

	private void initChart(){
		lineChart.getDescription().setEnabled(false);
		lineChart.setScaleEnabled(false);

		SimpleMarkerView smv = new SimpleMarkerView(getContext(), R.layout.solar_view_marker);
		smv.setChartView(lineChart);
		lineChart.setMarker(smv);

		XAxis xAxis = lineChart.getXAxis();
		xAxis.setTextSize(11f);
		xAxis.setTextColor(Color.BLACK);
		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
		xAxis.setDrawAxisLine(true);
		xAxis.setDrawGridLines(false);

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
		legend.setForm(Legend.LegendForm.LINE);
		legend.setFormSize(11f);

	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		dataPresenter.detachView();
	}

	@Override
	public void showLoading(String msg) {
		showLoadingDialog();
	}

	@Override
	public void showNormal() {
		dismissLoadingDialog();
	}

	@Override
	public void requestFailed(String msg) {
		if (msg != null) {
			showToast(msg);
		}else {
			showToast(R.string.network_not_available);
		}
	}

	@Override
	public void solarDataSuccess(SolarData data) {
		if (data != null) {
			tvStationName.setText(data.getStationName());
			tvEnergy.setText(String.format(Locale.CHINA, "当天发电量：%.2fkWh", Float.parseFloat(data.getCurrentElectric())));
			tvPower.setText(String.format(Locale.CHINA, "当前功率：%.2fkW", Float.parseFloat(data.getCurrentPower())));
			tvProfit.setText(String.format(Locale.CHINA, "当天收益：%s￥", data.getCurrentIncome()));
			tvTotalCO2.setText(String.format(Locale.CHINA, "%.2f",data.getCO2TotalEmission() / 1000));
			tvTotalProfit.setText(String.format(Locale.CHINA, "%.2f", Float.parseFloat(data.getPvTotalIncome())));
			tvTotalEnergy.setText(String.format(Locale.CHINA, "%.1f", Float.parseFloat(data.getPvTotalElectric())));
			tvTotalTree.setText(data.getTreePlant() + "");
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
			lineChart.invalidate();
		}
	}
}
