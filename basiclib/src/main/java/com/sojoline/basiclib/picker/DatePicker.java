package com.sojoline.basiclib.picker;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sojoline.basiclib.R;
import com.sojoline.basiclib.picker.adapter.ArrayWheelAdapter;
import com.sojoline.basiclib.picker.listener.OnWheelChangedListener;
import com.sojoline.basiclib.picker.wheel.WheelView;
import com.sojoline.basiclib.picker.widget.CanShow;
import com.sojoline.basiclib.picker.widget.DateMode;
import com.sojoline.basiclib.util.DateUtils;

import java.text.ParseException;
import java.util.Locale;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class DatePicker implements CanShow, OnWheelChangedListener{
	private Context context;

	/**
	 * 弹窗
	 */
	private PopupWindow popWindow;

	/**
	 * 弹窗view
	 */
	private View popView;

	/**
	 * 年滑轮
	 */
	private WheelView mViewYear;

	/**
	 * 月滑轮
	 */
	private WheelView mViewMonth;

	/**
	 * 天滑轮
	 */
	private WheelView mViewDay;

	private RelativeLayout mRelativeTitleBg;

	private TextView mTvOK;

	private TextView mTvTitle;

	private TextView mTvCancel;

	/**
	 * 当前年
	 */
	protected int mCurrentYear;

	/**
	 * 当前月
	 */
	protected int mCurrentMonth;

	/**
	 * 当前天
	 */
	protected int mCurrentDay;

	private OnDateClickListener listener;

	public interface OnDateClickListener {

		/**
		 * 选择的日期
		 * @param date
		 * 			{year,month,day}
		 */
		void onSelected(int...date);

		/**
		 * 取消
		 */
		void onCancel();
	}

	public void setOnDateClickListener(OnDateClickListener listener) {
		this.listener = listener;
	}

	/**
	 * Default text color
	 */
	public static final int DEFAULT_TEXT_COLOR = 0xFF585858;

	/**
	 * Default text size
	 */
	public static final int DEFAULT_TEXT_SIZE = 18;

	private int textColor = DEFAULT_TEXT_COLOR;

	private int textSize = DEFAULT_TEXT_SIZE;

	/**
	 * Default item count
	 */
	private static final int DEF_VISIBLE_ITEMS = 5;

	/**
	 * 滚轮显示的item个数
	 */
	private int visibleItems = DEF_VISIBLE_ITEMS;

	/**
	 * 年滚轮是否循环滚动
	 */
	private boolean isYearCyclic = false;

	/**
	 * 月滚轮是否循环滚动
	 */
	private boolean isMonthCyclic = true;

	/**
	 * 天滚轮是否循环滚动
	 */
	private boolean isDayCyclic = true;

	/**
	 * item间距
	 */
	private int padding = 5;

	/**
	 * Color.WHITE
	 */
	private int cancelTextColor = 0xffffffff;

	/**
	 * Color.WHITE
	 */
	private int confirmTextColor = 0xffffffff;

	/**
	 * 标题背景颜色
	 */
	private int titleBackgroundColor = 0xffe9e9e9;

	/**
	 * 标题颜色
	 */
	private int titleTextColor = 0xffffffff;

	/**
	 * 标题
	 */
	private String mTitle = "选择日期";

	/**
	 * popWindow的背景
	 */
	private int backgroundPop = 0xa0000000;

	private DateMode mode = DateMode.YEAR;

	/**起始日期*/
	private int startYear = 2010;
	private int startMonth = 1;
	private int startDay = 1;

	/**手机系统现在日期*/
	private int nowYear;
	private int nowMonth;
	private int nowDay;

	/**日期数组*/
	private String[] years;
	private String[] months;
	private String[] days;

	private int mDayId = 0;
	private int mMonthId = 0;
	private int mYearId = 0;

	private ArrayWheelAdapter<String> dayAdapter;
	private ArrayWheelAdapter<String> monthAdapter;

	private DatePicker(Builder builder) {
		//初始化参数
		this.textColor = builder.textColor;
		this.textSize = builder.textSize;
		this.visibleItems = builder.visibleItems;
		this.isYearCyclic = builder.isYearCyclic;
		this.isMonthCyclic = builder.isMonthCyclic;
		this.isDayCyclic = builder.isDayCyclic;
		this.context = builder.mContext;
		this.padding = builder.padding;
		this.mTitle = builder.mTitle;
		this.titleBackgroundColor = builder.titleBackgroundColor;
		this.confirmTextColor = builder.confirmTextColor;
		this.cancelTextColor = builder.cancelTextColor;
		this.backgroundPop = builder.backgroundPop;
		this.titleTextColor = builder.titleTextColor;
		this.startDay = builder.startDay;
		this.startMonth = builder.startMonth;
		this.startYear = builder.startYear;

		//初始化日期
		nowDay = DateUtils.getDay();
		nowMonth = DateUtils.getMonth();
		nowYear = DateUtils.getYear();
		mCurrentDay = nowDay;
		mCurrentMonth = nowMonth;
		mCurrentYear = nowYear;

		if (startYear >= nowYear){
			startYear = nowYear;
			if (startMonth >= nowMonth){
				startMonth = nowMonth;
				if (startDay > nowDay){
					startDay = nowDay;
				}
			}
		}

		//加载布局
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		popView = layoutInflater.inflate(R.layout.view_picker, null);

		//关联控件
		mViewYear = (WheelView) popView.findViewById(R.id.wheel_year);
		mViewMonth = (WheelView) popView.findViewById(R.id.wheel_month);
		mViewDay = (WheelView) popView.findViewById(R.id.wheel_day);
		mRelativeTitleBg = (RelativeLayout) popView.findViewById(R.id.rl_title);
		mTvOK = (TextView) popView.findViewById(R.id.tv_confirm);
		mTvTitle = (TextView) popView.findViewById(R.id.tv_title);
		mTvCancel = (TextView) popView.findViewById(R.id.tv_cancel);

		//初始化popWindow
		popWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		popWindow.setBackgroundDrawable(new ColorDrawable(backgroundPop));
		popWindow.setAnimationStyle(R.style.AnimBottom);
		popWindow.setTouchable(true);
		popWindow.setOutsideTouchable(false);
		popWindow.setFocusable(true);

		initYearWheel();

		/**
		 * 设置标题背景颜色
		 */
		mRelativeTitleBg.setBackgroundColor(this.titleBackgroundColor);


		/**
		 * 设置标题
		 */
		if (!TextUtils.isEmpty(this.mTitle)) {
			mTvTitle.setText(this.mTitle);
		}
		//设置确认按钮文字颜色
		mTvTitle.setTextColor(this.titleTextColor);
		//设置确认按钮文字颜色
		mTvOK.setTextColor(this.confirmTextColor);
		//设置取消按钮文字颜色
		mTvCancel.setTextColor(this.cancelTextColor);

		// 添加change事件
		mViewYear.addChangingListener(this);
		// 添加change事件
		mViewMonth.addChangingListener(this);
		// 添加change事件
		mViewDay.addChangingListener(this);
		// 添加onclick事件
		mTvCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onCancel();
				hide();
			}
		});
		mTvOK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onSelected(mCurrentYear, mCurrentMonth, mCurrentDay);
				hide();
			}
		});

	}

	/**
	 * 设置当前的日期（滑轮中间显示的日期）
	 *
	 * @param year
	 * 			年
	 * @param month
	 * 			月
	 * @param day
	 * 			日
	 */
	public void setCurrentDate(int year, int month, int day){
		mCurrentYear = year;
		mCurrentMonth = month;
		mCurrentDay = day;
		resetDateWheel();
	}

	/**
	 * 设置当前的日期（滑轮中间显示的日期）
	 *
	 * @param year
	 * 			年
	 * @param month
	 * 			月
	 */
	public void setCurrentDate(int year, int month){
		mCurrentYear = year;
		mCurrentMonth = month;
		resetDateWheel();
	}

	/**
	 * 设置当前的日期（滑轮中间显示的日期）
	 *
	 * @param year
	 * 			年
	 */
	public void setCurrentDate(int year){
		mCurrentYear = year;
		resetDateWheel();
	}

	/**
	 * 重新初始化wheel
	 */
	private void resetDateWheel(){
		initYearWheel();
		if (mode == DateMode.MONTH){
			setMonthWheel();
		}else if (mode == DateMode.DAY){
			setMonthWheel();
			setDayWheel();
		}
	}

	public static class Builder {
		/**
		 * Default text color
		 */
		public static final int DEFAULT_TEXT_COLOR = 0xFF585858;

		/**
		 * Default text size
		 */
		public static final int DEFAULT_TEXT_SIZE = 18;

		private int textColor = DEFAULT_TEXT_COLOR;

		private int textSize = DEFAULT_TEXT_SIZE;

		/**
		 * Default item count
		 */
		private static final int DEF_VISIBLE_ITEMS = 5;

		/**
		 * 滚轮显示的item个数
		 */
		private int visibleItems = DEF_VISIBLE_ITEMS;

		/**
		 * 年滚轮是否循环滚动
		 */
		private boolean isYearCyclic = false;

		/**
		 * 月滚轮是否循环滚动
		 */
		private boolean isMonthCyclic = true;

		/**
		 * 天滚轮是否循环滚动
		 */
		private boolean isDayCyclic = true;

		private Context mContext;

		/**
		 * item间距
		 */
		private int padding = 5;


		/**
		 * Color.WHITE
		 */
		private int cancelTextColor = 0xffffffff;


		/**
		 * Color.WHITE
		 */
		private int confirmTextColor = 0xffffffff;

		/**
		 * 标题背景颜色
		 */
		private int titleBackgroundColor = 0xffe9e9e9;

		/**
		 * 标题颜色
		 */
		private int titleTextColor = 0xffffffff;

		/**
		 * 标题
		 */
		private String mTitle = "选择时间";

		/**
		 * popWindow的背景
		 */
		private int backgroundPop = 0xffffffff;

		/**起始日期*/
		private int startYear = 2010;
		private int startMonth = 1;
		private int startDay = 1;

		public Builder(Context context) {
			this.mContext = context;
		}

		/**
		 * 设置popWindow的背景
		 *
		 * @param color
		 * @return
		 */
		public Builder setBackgroundPop(int color) {
			this.backgroundPop = color;
			return this;
		}

		/**
		 * 设置标题背景颜色
		 *
		 * @param color
		 * @return
		 */
		public Builder setTitleBackgroundColor(int color) {
			this.titleBackgroundColor = color;
			return this;
		}

		/**
		 * 设置标题背景颜色
		 *
		 * @param color
		 * 			标题背景颜色
		 *
		 * @return	Builder
		 */
		public Builder setTitleTextColor(int color) {
			this.titleTextColor = color;
			return this;
		}


		/**
		 * 设置标题
		 *
		 * @param title
		 * 			标题文字
		 *
		 * @return builder
		 */
		public Builder setTitle(String title) {
			this.mTitle = title;
			return this;
		}

		/**
		 * 设置确认按钮文字颜色
		 *
		 * @param color
		 * 			确认按钮文字颜色
		 * @return	Builder
		 */
		public Builder setConfirTextColor(int color) {
			this.confirmTextColor = color;
			return this;
		}

		/**
		 * 设置取消按钮文字颜色
		 *
		 * @param color
		 * 			取消按钮文字颜色
		 *
		 * @return	Builder
		 */
		public Builder setCancelTextColor(int color) {
			this.cancelTextColor = color;
			return this;
		}

		/**
		 * 设置item文字颜色
		 *
		 * @param textColor
		 * 			滑轮文字颜色
		 *
		 * @return	Builder
		 */
		public Builder setTextColor(int textColor) {
			this.textColor = textColor;
			return this;
		}

		/**
		 * 设置item文字大小
		 *
		 * @param textSize
		 * 			滑轮文字大小
		 *
		 * @return Builder
		 */
		public Builder setTextSize(int textSize) {
			this.textSize = textSize;
			return this;
		}

		/**
		 * 设置滚轮显示的item个数
		 *
		 * @param visibleItems
		 * 			滚轮显示的item个数
		 *
		 * @return	Builder
		 */
		public Builder setVisibleItemsCount(int visibleItems) {
			this.visibleItems = visibleItems;
			return this;
		}

		/**
		 * 设置年滚轮是否循环滚动
		 * @param isYearCyclic
		 * 			年滚轮是否循环滚动
		 *
		 * @return	Builder
		 */
		public Builder setYearCyclic(boolean isYearCyclic) {
			this.isYearCyclic = isYearCyclic;
			return this;
		}

		/**
		 * 设置月滚轮是否循环滚动
		 *
		 * @param isMonthCyclic
		 * 			月滚轮是否循环滚动
		 *
		 * @return	Builder
		 */
		public Builder setMonthCyclic(boolean isMonthCyclic) {
			this.isMonthCyclic = isMonthCyclic;
			return this;
		}

		/**
		 * 设置天滚轮是否循环滚动
		 *
		 * @param isDayCyclic
		 * 			天滚轮是否循环滚动
		 *
		 * @return	Builder
		 */
		public Builder setDayCyclic(boolean isDayCyclic) {
			this.isDayCyclic = isDayCyclic;
			return this;
		}

		/**
		 * 设置item间距
		 *
		 * @param itemPadding
		 * 			滑轮文字的间距
		 *
		 * @return	Builder
		 */
		public Builder setItemPadding(int itemPadding) {
			this.padding = itemPadding;
			return this;
		}

		/**
		 * 设置滑轮起始日期
		 *
		 * @param source
		 * 			日期字符串
		 * @param pattern
		 * 			日期格式
		 *
		 * @return	Builder
		 */
		public Builder setStartDate(String source, String pattern){
			try {
				int[] date = DateUtils.dateToArray(source,pattern);
				this.startYear = date[0];
				this.startMonth = date[1];
				this.startDay = date[2];
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return this;
		}

		public DatePicker build() {
			DatePicker picker = new DatePicker(this);
			return picker;
		}
	}

	private void initYearWheel() {
		if (nowYear < startYear){
			return;
		}else {
			years = new String[nowYear - startYear + 1];
		}
		
		for (int i = 0; i < years.length; i++) {
			years[i] = String.format(Locale.CHINA, "%d", startYear + i);
			if (startYear + i == mCurrentYear){
				mYearId = i;
			}
		}
		
		ArrayWheelAdapter<String> yearAdapter = new ArrayWheelAdapter<>(context,years);
		mViewYear.setViewAdapter(yearAdapter);
		mViewYear.setCurrentItem(mYearId);
		mViewYear.setVisibleItems(visibleItems);
		mViewYear.setCyclic(isYearCyclic);
		yearAdapter.setPadding(padding);
		yearAdapter.setTextColor(textColor);
		yearAdapter.setTextSize(textSize);
	}

	private void setMonthWheel(){
		int start;
		if (mCurrentYear == nowYear){
			start = 1;
			months = new String[nowMonth];
		}else if (mCurrentYear == startYear){
			start = startMonth;
			months = new String[12 - startMonth + 1];
		}else {
			start = 1;
			months = new String[12];
		}
		int mMonthId = 0;
		
		for (int i = 0; i < months.length; i++) {
			int temp = i + start;
			if (temp < 10){
				months[i] = "0" + temp;
			}else {
				months[i] = temp + "";
			}
			
			if (temp == mCurrentMonth){
				mMonthId = i;
			}
		}

		if (months.length < 4){
			isMonthCyclic = false;
		}else {
			isMonthCyclic = true;
		}

		if (monthAdapter == null){
			monthAdapter = new ArrayWheelAdapter<>(context,months);
			mViewMonth.setViewAdapter(monthAdapter);
			mViewMonth.setCurrentItem(mMonthId);
			mViewMonth.setVisibleItems(visibleItems);
			mViewMonth.setCyclic(isMonthCyclic);
			monthAdapter.setPadding(padding);
			monthAdapter.setTextColor(textColor);
			monthAdapter.setTextSize(textSize);
		}else {
			monthAdapter = (ArrayWheelAdapter<String>)mViewMonth.getViewAdapter();
			monthAdapter.setItems(months);
			mViewMonth.setCurrentItem(mMonthId);
			mViewMonth.invalidateWheel(true);
		}

	}

	private void setDayWheel(){
		int start;
		int total = DateUtils.getDaysOfMonth(mCurrentYear, mCurrentMonth);
		if (mCurrentYear == nowYear){
			if (mCurrentMonth == nowMonth){
				start = 1;
				days = new String[nowDay];
			}else {
				start = 1;
				days = new String[total];
			}
		}else if (mCurrentYear == startYear){
			if (mCurrentMonth == startMonth){
				start = startDay;
				days = new String[total - startDay + 1];
			}else {
				start = 1;
				days = new String[total];
			}
		}else {
			start = 1;
			days = new String[total];
		}

		for (int i = 0; i < days.length; i++) {
			int temp = i + start;
			if (temp < 10){
				days[i] = "0" + temp;
			}else {
				days[i] = temp + "";
			}
			
			if (temp == mCurrentDay){
				mDayId = i;
			}
		}

		if (days.length < 4){
			isDayCyclic = false;
		}else {
			isDayCyclic = true;
		}

		if (dayAdapter == null){
			Log.i("TAG", "setDayWheel: adapter 初始化");
			dayAdapter = new ArrayWheelAdapter<>(context,days);
			mViewDay.setViewAdapter(dayAdapter);
			mViewDay.setCurrentItem(mDayId);
			mViewDay.setVisibleItems(visibleItems);
			mViewDay.setCyclic(isDayCyclic);
			dayAdapter.setPadding(padding);
			dayAdapter.setTextColor(textColor);
			dayAdapter.setTextSize(textSize);
		}else {
			Log.i("TAG", "setDayWheel: adapter 已初始化");
			dayAdapter = (ArrayWheelAdapter<String>) mViewDay.getViewAdapter();
			dayAdapter.setItems(days);
			mViewDay.setCurrentItem(mDayId);
			mViewDay.invalidateWheel(true);
		}

	}


	@Override
	public void setMode(DateMode mode) {
		this.mode = mode;
		//显示或隐藏滑轮
		if (mode == DateMode.DAY){
			mViewDay.setVisibility(View.VISIBLE);
			mViewMonth.setVisibility(View.VISIBLE);
			mViewYear.setVisibility(View.VISIBLE);
			setMonthWheel();
			setDayWheel();
		}else if (mode == DateMode.MONTH){
			mViewDay.setVisibility(View.GONE);
			mViewMonth.setVisibility(View.VISIBLE);
			mViewYear.setVisibility(View.VISIBLE);
			setMonthWheel();
		}else {
			mViewDay.setVisibility(View.GONE);
			mViewMonth.setVisibility(View.GONE);
			mViewYear.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public DateMode getMode() {
		return mode;
	}

	@Override
	public void show() {
		if (!isShow()) {
			popWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 0);
		}
	}

	@Override
	public void hide() {
		if (isShow()) {
			popWindow.dismiss();
		}
	}

	@Override
	public boolean isShow() {
		return popWindow.isShowing();
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if ( oldValue != newValue){
			if (wheel == mViewYear) {
				mYearId = newValue;
				mCurrentYear = Integer.parseInt(years[mYearId]);
				if (mode == DateMode.MONTH){
					setMonthWheel();
				}else if (mode == DateMode.DAY){
					setMonthWheel();
					setDayWheel();
				}
			} else if (wheel == mViewMonth) {
				mMonthId = newValue;
				mCurrentMonth = Integer.parseInt(months[mMonthId]);
				if (mode == DateMode.DAY){
					setDayWheel();
				}
			} else if (wheel == mViewDay) {
				mDayId = newValue;
				mCurrentDay = Integer.parseInt(days[mDayId]);
			}
		}
	}
}
