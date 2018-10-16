package com.sojoline.solar.view.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.sojoline.base.util.AppUtils;
import com.sojoline.base.util.ScreenUtils;
import com.sojoline.base.view.BaseFragment;
import com.sojoline.base.widget.SimpleMarkerView;
import com.sojoline.basiclib.picker.DatePicker;
import com.sojoline.basiclib.picker.widget.DateMode;
import com.sojoline.basiclib.util.DateUtils;
import com.sojoline.model.db.DateElectric;
import com.sojoline.model.request.SolarEnergyRequest;
import com.sojoline.model.storage.AppInfoPreferences;
import com.sojoline.presenter.solar.data.SolarEnergyContract;
import com.sojoline.presenter.solar.data.SolarEnergyPresenter;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;
import com.sojoline.solar.widget.ColorTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

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

public class SolarEnergyFragment extends BaseFragment implements SolarEnergyContract.View{
	@BindView(R2.id.top_bar)
	SegmentTabLayout topBar;
	@BindView(R2.id.barChart)
	BarChart barChart;
	@BindView(R2.id.barChartmonth)
	BarChart barChartmonth;
	@BindView(R2.id.barChartyear)
	BarChart barChartyear;
	@BindView(R2.id.lineChart)
	LineChart lineChart;
	@BindView(R2.id.iv_left)
	ImageView ivLeft;
	@BindView(R2.id.iv_right)
	ImageView ivRight;
	@BindView(R2.id.bt_date)
	Button btDate;
	@BindView(R2.id.tv_total)
	TextView tvTotal;

	private DatePicker picker;
	private Drawable icon;
	private SolarEnergyPresenter presenter;
	private SolarEnergyRequest request;
	private XAxis barXAxis;
	private XAxis barXAxisMonth;
	private XAxis barXAxisYear;
	private XAxis lineXAxis;

	public static final int DAY = 51;
	public static final int MONTH = 52;
	public static final int YEAR = 53;
	public static final int SUM = 54;
	private int current;
	private int year;
	private int month;
	private int day;
	private String createTime;

	public static SolarEnergyFragment newInstance(){
		Bundle args = new Bundle();
		SolarEnergyFragment fragment = new SolarEnergyFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.solar_fragment_energy, container, false);
	}

	@Override
	protected void initPresenter() {
		super.initPresenter();
		presenter = new SolarEnergyPresenter();
		presenter.attachView(this);
	}

	@Override
	protected void initView(@Nullable Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		String id = AppInfoPreferences.get().getStationId();
		request = new SolarEnergyRequest();
		request.setDPStationID(id);

		current = DAY;
		try {
			createTime = AppInfoPreferences.get().getCreateTime().split(" ")[0];
		}catch (Exception e){
			createTime = "2018-01-01";
		}
		initDate();
		setImageByDate();

		btDate.setText(String.format(Locale.CHINA,"%d-%s-%s", year, getFormat(month), getFormat(day)));
		request.setQueryType("day");
		request.setQueryTime(String.format(Locale.CHINA,"%d-%s-%s", year, getFormat(month), getFormat(day)));
		presenter.getSolarEnergy(request);
		String[] titles = getResources().getStringArray(R.array.barTitles);
		topBar.setTabData(titles);
		topBar.setOnTabSelectListener(new OnTabSelectListener() {
			@Override
			public void onTabSelect(int position) {
				switch (position){
					case 0:
						current = DAY;
						icon = ContextCompat.getDrawable(getContext(),R.mipmap.ic_day_makepower);
						icon.setBounds(0, 0, icon.getMinimumWidth(), icon.getMinimumHeight());
						tvTotal.setCompoundDrawables(icon, null, null, null);
						initDate();
						btDate.setText(String.format(Locale.CHINA,"%d-%s-%s", year, getFormat(month), getFormat(day)));
						request.setQueryType("day");
						request.setQueryTime(String.format(Locale.CHINA,"%d-%s-%s", year, getFormat(month), getFormat(day)));
						setImageByDate();
						break;
					case 1:
						current = MONTH;
						icon = ContextCompat.getDrawable(getContext(),R.mipmap.ic_month_makepower);
						icon.setBounds(0, 0, icon.getMinimumWidth(), icon.getMinimumHeight());
						tvTotal.setCompoundDrawables(icon, null, null, null);
						initDate();
						btDate.setText(String.format(Locale.CHINA,"%d-%s", year, getFormat(month)));
						request.setQueryType("month");
						request.setQueryTime(String.format(Locale.CHINA,"%d-%s", year, getFormat(month)));
						setImageByDate();
						break;
					case 2:
						current = YEAR;
						icon = ContextCompat.getDrawable(getContext(),R.mipmap.ic_year_makepower);
						icon.setBounds(0, 0, icon.getMinimumWidth(), icon.getMinimumHeight());
						tvTotal.setCompoundDrawables(icon, null, null, null);
						initDate();
						btDate.setText(String.format(Locale.CHINA,"%d", year));
						request.setQueryType("year");
						request.setQueryTime(String.format(Locale.CHINA,"%d", year));
						setImageByDate();
						break;
					case 3:
						current = SUM;
						icon = ContextCompat.getDrawable(getContext(),R.mipmap.ic_sum_makepower);
						icon.setBounds(0, 0, icon.getMinimumWidth(), icon.getMinimumHeight());
						tvTotal.setCompoundDrawables(icon, null, null, null);
						btDate.setText("--");
						request.setQueryType("total");
						request.setQueryTime(String.format(Locale.CHINA,"%d", DateUtils.getYear()));
						setLeftImage(false);
						setRightImage(false);
						break;
					default:
						break;
				}
				presenter.getSolarEnergy(request);
			}

			@Override
			public void onTabReselect(int position) {

			}
		});
		initBarChart();
		initLineChart();
		initBarChartMonth();
		initBarChartYear();
	}

	/**
	 * 初始化日期
	 */
	private void initDate(){
		year = DateUtils.getYear();
		month = DateUtils.getMonth();
		day = DateUtils.getDay();
	}
	/**
	 * 初始化BarChart
	 */
	private void initBarChart(){
		Description description = barChart.getDescription();
		description.setEnabled(false);

		barChart.setNoDataText("没有数据");
		barChart.setScaleXEnabled(true);
		barChart.setScaleYEnabled(false);
		barChart.setExtraLeftOffset(ScreenUtils.dp2px(5));

		barXAxis = barChart.getXAxis();
		barXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
		barXAxis.setTextColor(Color.BLACK);
		barXAxis.setTextSize(11f);
		barXAxis.setLabelCount(5);
		barXAxis.setDrawGridLines(false);
		barXAxis.setDrawAxisLine(true);
		barXAxis.setValueFormatter(new IAxisValueFormatter() {
			@Override
			public String getFormattedValue(float value, AxisBase axis) {
//				DebugLog.log("BarChart x value:" + value);
				return (int)value + "日";
			}
		});
		YAxis leftY = barChart.getAxisLeft();
		leftY.setDrawGridLines(false);
		leftY.setDrawAxisLine(true);
		leftY.setTextColor(Color.BLACK);
		leftY.setTextSize(11f);

		leftY.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int)value+"kwh";
            }
        });
		YAxis rightY = barChart.getAxisRight();
		rightY.setEnabled(false);

		Legend legend = barChart.getLegend();
		legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
		legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
		legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
		legend.setDrawInside(false);
		legend.setForm(Legend.LegendForm.SQUARE);
		legend.setFormSize(11f);

	}
	private void initBarChartMonth(){
		Description description = barChartmonth.getDescription();
		description.setEnabled(false);

		barChartmonth.setNoDataText("没有数据");
		barChartmonth.setScaleXEnabled(true);
		barChartmonth.setScaleYEnabled(false);
		barChartmonth.setExtraLeftOffset(ScreenUtils.dp2px(5));

		barXAxisMonth = barChartmonth.getXAxis();
		barXAxisMonth.setPosition(XAxis.XAxisPosition.BOTTOM);
		barXAxisMonth.setTextColor(Color.BLACK);
		barXAxisMonth.setTextSize(11f);
		barXAxisMonth.setLabelCount(5);
		barXAxisMonth.setDrawGridLines(false);
		barXAxisMonth.setDrawAxisLine(true);
		barXAxisMonth.setValueFormatter(new IAxisValueFormatter() {
			@Override
			public String getFormattedValue(float value, AxisBase axis) {
//				DebugLog.log("BarChart x value:" + value);
				return (int)value + "月";
			}
		});
		YAxis leftY = barChartmonth.getAxisLeft();
		leftY.setDrawGridLines(false);
		leftY.setDrawAxisLine(true);
		leftY.setTextColor(Color.BLACK);
		leftY.setTextSize(11f);

		leftY.setValueFormatter(new IAxisValueFormatter() {
			@Override
			public String getFormattedValue(float value, AxisBase axis) {
				return (int)value+"kwh";
			}
		});
		YAxis rightY = barChartmonth.getAxisRight();
		rightY.setEnabled(false);

		Legend legend = barChartmonth.getLegend();
		legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
		legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
		legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
		legend.setDrawInside(false);
		legend.setForm(Legend.LegendForm.SQUARE);
		legend.setFormSize(11f);

	}
	private void initBarChartYear(){
		Description description = barChartyear.getDescription();
		description.setEnabled(false);

		barChartyear.setNoDataText("没有数据");
		barChartyear.setScaleXEnabled(true);
		barChartyear.setScaleYEnabled(false);
		barChartyear.setExtraLeftOffset(ScreenUtils.dp2px(5));

		barXAxisYear = barChartyear.getXAxis();
		barXAxisYear.setPosition(XAxis.XAxisPosition.BOTTOM);
		barXAxisYear.setTextColor(Color.BLACK);
		barXAxisYear.setTextSize(11f);
		barXAxisYear.setLabelCount(5);
		barXAxisYear.setDrawGridLines(false);
		barXAxisYear.setDrawAxisLine(true);
		barXAxisYear.setValueFormatter(new IAxisValueFormatter() {
			@Override
			public String getFormattedValue(float value, AxisBase axis) {
//				DebugLog.log("BarChart x value:" + value);
				return (int)value + "年";
			}
		});
		YAxis leftY = barChartyear.getAxisLeft();
		leftY.setDrawGridLines(false);
		leftY.setDrawAxisLine(true);
		leftY.setTextColor(Color.BLACK);
		leftY.setTextSize(11f);

		leftY.setValueFormatter(new IAxisValueFormatter() {
			@Override
			public String getFormattedValue(float value, AxisBase axis) {
				return (int)value+"kwh";
			}
		});
		YAxis rightY = barChartyear.getAxisRight();
		rightY.setEnabled(false);

		Legend legend = barChartyear.getLegend();
		legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
		legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
		legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
		legend.setDrawInside(false);
		legend.setForm(Legend.LegendForm.SQUARE);
		legend.setFormSize(11f);

	}

	/**
	 * 初始化LineChart
	 */
	private void initLineChart(){
		lineChart.getDescription().setEnabled(false);
		lineChart.setScaleEnabled(false);
		lineChart.setNoDataText("没有数据");
		lineChart.setExtraLeftOffset(ScreenUtils.dp2px(5));

		SimpleMarkerView smv = new SimpleMarkerView(getContext(), R.layout.solar_view_marker);
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
//				DebugLog.log("LineChart x value:" + value);
				return (int)value + "h";
			}
		});

		YAxis leftAxis = lineChart.getAxisLeft();
		leftAxis.setTextColor(Color.BLACK);
		leftAxis.setDrawAxisLine(true);
		leftAxis.setDrawGridLines(false);
		leftAxis.setDrawZeroLine(false);

		leftAxis.setValueFormatter(new IAxisValueFormatter() {
			@Override
			public String getFormattedValue(float value, AxisBase axis) {
				return (int) value + "kwh";
			}
		});
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
	 * 设置LeftImageView
	 * @param enable
	 */
	private void setLeftImage(boolean enable){
		ivLeft.setImageDrawable(enable ? ContextCompat.getDrawable(getContext(), R.mipmap.ic_left_enable)
		: ContextCompat.getDrawable(getContext(), R.mipmap.ic_left_unenable));
		if (enable){
			ivLeft.setEnabled(true);
		}else {
			ivLeft.setEnabled(false);
		}
	}

	/**
	 * 设置RightImageView
	 * @param enable
	 */
	private void setRightImage(boolean enable){
		ivRight.setImageDrawable(enable ? ContextCompat.getDrawable(getContext(), R.mipmap.ic_right_enable)
				: ContextCompat.getDrawable(getContext(), R.mipmap.ic_right_unenable));
		if (enable){
			ivRight.setEnabled(true);
		}else {
			ivRight.setEnabled(false);
		}
	}

	/**
	 * 根据日期设置Image
	 */
	private void setImageByDate(){
		try {
			if (current == DAY){
				if (DateUtils.isBefore(createTime, year, month, day)){
					setLeftImage(true);
				}else {
					setLeftImage(false);
				}
			}else if (current == MONTH){
				if (DateUtils.isBefore(createTime, year, month)){
					setLeftImage(true);
				}else {
					setLeftImage(false);
				}
			}else {
				if (DateUtils.isBefore(createTime, year)){
					setLeftImage(true);
				}else {
					setLeftImage(false);
				}
			}

		} catch (ParseException e) {
			throw new RuntimeException("日期出现错误了");
		}

		if (DateUtils.isAfter(year, month, day)){
			setRightImage(true);
		}else {
			setRightImage(false);
		}
	}

	@OnClick({R2.id.iv_right, R2.id.iv_left, R2.id.bt_date})
	public void onClick(View view){
		if (view.getId() == R.id.iv_left){
			handleAction(true);
		}else if (view.getId() == R.id.iv_right){
			handleAction(false);
		}else if (view.getId() == R.id.bt_date){
			if (current == DAY){
				showPicker(DateMode.DAY);
			}else if (current == MONTH){
				showPicker(DateMode.MONTH);
			}else if (current == YEAR){
				showPicker(DateMode.YEAR);
			}
		}
	}

	/**
	 * 处理ImageView点击事件
	 * @param isLeft
	 */
	private void handleAction(boolean isLeft){
		if (isLeft){
			if (current == DAY){
				int[] date = DateUtils.lastDay(year, month, day);
				year = date[0];
				month = date[1];
				day = date[2];
				String str = String.format(Locale.CHINA,"%d-%s-%s", year, getFormat(month), getFormat(day));
				btDate.setText(str);
				request.setQueryType("day");
				request.setQueryTime(str);
			}else if (current == MONTH){
				int[] date = DateUtils.lastMonth(year, month);
				year = date[0];
				month = date[1];
				btDate.setText(String.format(Locale.CHINA,"%d-%s", year, getFormat(month)));
				request.setQueryType("month");
				request.setQueryTime(String.format(Locale.CHINA,"%d-%s", year, getFormat(month)));
			}else if (current == YEAR){
				year = DateUtils.lastYear(year);
				btDate.setText(String.format(Locale.CHINA,"%d", year));
				request.setQueryType("year");
				request.setQueryTime(String.format(Locale.CHINA,"%d", year));
			}
		}else {
			if (current == DAY){
				int[] date = DateUtils.nextDay(year, month, day);
				year = date[0];
				month = date[1];
				day = date[2];
				String str = String.format(Locale.CHINA,"%d-%s-%s", year, getFormat(month), getFormat(day));
				btDate.setText(str);
				request.setQueryType("day");
				request.setQueryTime(str);
			}else if (current == MONTH){
				int[] date = DateUtils.nextMonth(year, month);
				year = date[0];
				month = date[1];
				btDate.setText(String.format(Locale.CHINA,"%d-%s", year, getFormat(month)));
				request.setQueryType("month");
				request.setQueryTime(String.format(Locale.CHINA,"%d-%s", year, getFormat(month)));
			}else if (current == YEAR){
				year = DateUtils.nextYear(year);
				btDate.setText(String.format(Locale.CHINA,"%d", year));
				request.setQueryType("year");
				request.setQueryTime(String.format(Locale.CHINA,"%d", year));
			}
		}
		presenter.getSolarEnergy(request);
		setImageByDate();
	}

	/**
	 * 显示日期选择器
	 * @param mode
	 * 			模式
	 */
	private void showPicker(DateMode mode){
		if (picker == null) {
			Log.i("TAG", "showPicker: 初始化");
			picker = new DatePicker.Builder(getContext())
					.setTextSize(20)
					.setStartDate(createTime,"yyyy-MM-dd")
					.setTextColor(ContextCompat.getColor(getContext(), R.color.primary_text_black))
					.setItemPadding(10)
					.setTitleBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary))
					.build();
			picker.setMode(mode);
			picker.setCurrentDate(year, month, day);
			picker.setOnDateClickListener(new DatePicker.OnDateClickListener() {
				@Override
				public void onSelected(int... date) {
					int tempYear = year;
					int tempMonth = month;
					int tempDay = day;
					if (current == DAY){
						year = date[0];
						month = date[1];
						day = date[2];
						if(tempYear != year || tempMonth != month || tempDay != day){
							String str = String.format(Locale.CHINA,"%d-%s-%s", year, getFormat(month), getFormat(day));
							btDate.setText(str);
							request.setQueryType("day");
							request.setQueryTime(str);
							presenter.getSolarEnergy(request);
						}
					}else if (current == MONTH){
						year = date[0];
						month = date[1];
						if(tempYear != year || tempMonth != month){
							String str = String.format(Locale.CHINA,"%d-%s", year, getFormat(month));
							btDate.setText(str);
							request.setQueryType("month");
							request.setQueryTime(str);
							presenter.getSolarEnergy(request);
						}
					}else {
						year = date[0];
						if(tempYear != year){
							btDate.setText(String.format(Locale.CHINA,"%d", year));
							request.setQueryType("year");
							request.setQueryTime(String.format(Locale.CHINA,"%d", year));
							presenter.getSolarEnergy(request);
						}
					}

					setImageByDate();
				}

				@Override
				public void onCancel() {

				}
			});
			picker.show();
		}else {
			if (picker.getMode() != mode){
				picker.setMode(mode);
			}
			Log.i("TAG", "showPicker: 已初始化");
			picker.setCurrentDate(year, month, day);
			picker.show();
		}
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

	/**
	 * 发电量数据处理
	 * @param o
	 */
	@Override
	public void energySuccess(Object o) {
		float total = 0;
		List<DateElectric> list = (List<DateElectric>) o;
		if (current == DAY){
			//日发电量采用折线图
			List<Entry> entries = new ArrayList<>();
			if (list != null && list.size() > 0){
				if (list.size() > 1){
					Collections.sort(list);
					for (DateElectric electric : list){
						//计算总发电量
						total += electric.getElectric();
						entries.add(new Entry(electric.getNum(), electric.getElectric()));
					}
					lineXAxis.setLabelCount(6);
				}else {
					//一个数据时，在填充一个0，为了图表美观
					lineXAxis.setLabelCount(2);
					DateElectric electric = list.get(0);
					total = electric.getElectric();
					entries.add(new Entry(electric.getNum() - 1, 0));
					entries.add(new Entry(electric.getNum(), electric.getElectric()));
				}

			}else {
				//没有数据时填充0
				for (int i = 0; i < 12; i++){
					entries.add(new Entry(i, 0));
				}
				lineXAxis.setLabelCount(6);
			}

			//图表设置数据
			lineChart.setVisibility(View.VISIBLE);
			barChart.setVisibility(View.GONE);
			barChartmonth.setVisibility(View.GONE);
			barChartyear.setVisibility(View.GONE);
			lineChart.clear();
			LineDataSet set = new LineDataSet(entries, "发电曲线");
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
		}else if (current == MONTH){
			//其他发电量采用柱状图
			List<BarEntry> entries = new ArrayList<>();
			if (list != null && list.size() > 0) {
				if (list.size() > 1){
					Collections.sort(list);
				}

				for (DateElectric electric : list){
					total += electric.getElectric();
					entries.add(new BarEntry(electric.getNum(), electric.getElectric()));
				}
			}else {
				//没有数据时填充0
				for (int i = 0; i < 12; i++){
					entries.add(new BarEntry(i, 0));
				}
			}

			lineChart.setVisibility(View.GONE);
			barChart.setVisibility(View.VISIBLE);
			barChartmonth.setVisibility(View.GONE);
			barChartyear.setVisibility(View.GONE);
			barChart.clear();
			BarDataSet set = new BarDataSet(entries, "发电量/kWh");
			set.setDrawIcons(false);
			set.setColors(ColorTemplate.MATERIAL_COLORS);
			ArrayList<IBarDataSet> sets = new ArrayList<>();
			sets.add(set);
			BarData data = new BarData(sets);
			if (entries.size() < 6) {
				barXAxis.setLabelCount(entries.size());
				barXAxis.setSpaceMin(0.5f);
				barXAxis.setSpaceMax(0.5f);
				data.setBarWidth(0.45f);
			} else {
				barXAxis.setLabelCount(6);
				data.setBarWidth(0.8f);
			}

			barChart.setData(data);
		} else if (current == YEAR) {
			List<BarEntry> entries = new ArrayList<>();
			if (list != null && list.size() > 0) {
				if (list.size() > 1) {
					Collections.sort(list);
				}

				for (DateElectric electric : list) {
					total += electric.getElectric();
					entries.add(new BarEntry(electric.getNum(), electric.getElectric()));
				}
			} else {
				//没有数据时填充0
				for (int i = 0; i < 12; i++) {
					entries.add(new BarEntry(i, 0));
				}
			}

			lineChart.setVisibility(View.GONE);
			barChart.setVisibility(View.GONE);
			barChartmonth.setVisibility(View.VISIBLE);
			barChartyear.setVisibility(View.GONE);
			barChartmonth.clear();
			BarDataSet set = new BarDataSet(entries, "发电量/kWh");
			set.setDrawIcons(false);
			set.setColors(ColorTemplate.MATERIAL_COLORS);
			ArrayList<IBarDataSet> sets = new ArrayList<>();
			sets.add(set);
			BarData data = new BarData(sets);
			if (entries.size() < 6) {
				barXAxisMonth.setLabelCount(entries.size());
				barXAxisMonth.setSpaceMin(0.5f);
				barXAxisMonth.setSpaceMax(0.5f);
				data.setBarWidth(0.45f);
			} else {
				barXAxisMonth.setLabelCount(6);
				data.setBarWidth(0.8f);
			}

			barChartmonth.setData(data);
		} else if (current == SUM) {
			List<BarEntry> entries = new ArrayList<>();
			if (list != null && list.size() > 0) {
				if (list.size() > 1) {
					Collections.sort(list);
				}

				for (DateElectric electric : list) {
					total += electric.getElectric();
					entries.add(new BarEntry(electric.getNum(), electric.getElectric()));
				}
			} else {
				//没有数据时填充0
				for (int i = 0; i < 12; i++) {
					entries.add(new BarEntry(i, 0));
				}
			}

			lineChart.setVisibility(View.GONE);
			barChart.setVisibility(View.GONE);
			barChartmonth.setVisibility(View.GONE);
			barChartyear.setVisibility(View.VISIBLE);
			barChartyear.clear();
			BarDataSet set = new BarDataSet(entries, "发电量/kWh");
			set.setDrawIcons(false);
			set.setColors(ColorTemplate.MATERIAL_COLORS);
			ArrayList<IBarDataSet> sets = new ArrayList<>();
			sets.add(set);
			BarData data = new BarData(sets);
			if (entries.size() < 6) {
				barXAxisYear.setLabelCount(entries.size());
				barXAxisYear.setSpaceMin(0.5f);
				barXAxisYear.setSpaceMax(0.5f);
				data.setBarWidth(0.45f);
			} else {
				barXAxisYear.setLabelCount(6);
				data.setBarWidth(0.8f);
			}

			barChartyear.setData(data);
		}
		tvTotal.setText(total + " kWh");
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
	}
}
