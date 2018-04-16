package com.sojoline.solar.widget;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.flyco.dialog.widget.base.BaseDialog;
import com.sojoline.base.toast.ToastUtils;
import com.sojoline.base.widget.DeviceType;
import com.sojoline.base.widget.WarningType;
import com.sojoline.solar.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/03/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class DeviceFilterDialog extends BaseDialog<DeviceFilterDialog> implements View.OnClickListener{
	private Button btDeviceALL, btCom, btInv, btTra;
	private Button btAll, btAlarm, btFault;
	private Button btTimeStart, btTimeEnd;
	private DeviceType deviceType, currentDeviceType;
	private WarningType warningType, currentWarningType;
	private OnFilterTypeChangedListener onFilterTypeChangedListener;
	private TimePickerView pvTime;
	private HashMap<String,String> timeMap,currentTimeMap;
	private LinearLayout timeLayout;
	private View bottomLine;
	private LinearLayout bottomComplete;
    private SimpleDateFormat simpleDateFormat;
	public DeviceFilterDialog(Context context, DeviceType deviceType, WarningType warningType,HashMap<String,String> timeMap) {
		super(context);
		this.deviceType = deviceType;
		this.warningType = warningType;
		currentDeviceType = deviceType;
		currentWarningType = warningType;
		currentTimeMap = timeMap;
		this.timeMap = new HashMap<>();
		this.timeMap.putAll(timeMap);
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	@Override
	public View onCreateView() {
		widthScale(0.85f);
		View view = View.inflate(mContext, R.layout.solar_dialog_filter, null);
		timeLayout = (LinearLayout) view.findViewById(R.id.ll_time_select);
		//时间选择器
		pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {
				String format = simpleDateFormat.format(date);
				if(v.getId() == R.id.bt_time_start){
					btTimeStart.setText(format);
					currentTimeMap.put("startTime",format);
				} else if (v.getId() == R.id.bt_time_end){
					btTimeEnd.setText(format);
					currentTimeMap.put("endTime",format);
				}
				bottomLine.setVisibility(View.VISIBLE);
				bottomComplete.setVisibility(View.VISIBLE);
			}
		   }).setType(new boolean[]{true, true, true, false, false, false})
			.setBackgroundId(getContext().getResources().getColor(R.color.background))
			.setBgColor(getContext().getResources().getColor(R.color.background))
			.setCancelColor(getContext().getResources().getColor(R.color.btn_focused))
			.setSubmitColor(getContext().getResources().getColor(R.color.btn_focused))
			.setTextColorCenter(getContext().getResources().getColor(R.color.btn_focused))
			.setDecorView(timeLayout)
			.build();
		pvTime.setOnDismissListener(new com.bigkoo.pickerview.listener.OnDismissListener() {
			@Override
			public void onDismiss(Object o) {
				bottomLine.setVisibility(View.VISIBLE);
				bottomComplete.setVisibility(View.VISIBLE);
			}
		});

		bottomLine = (View)view.findViewById(R.id.bottom_line_divider);
		bottomComplete = (LinearLayout)view.findViewById(R.id.ll_bottom_complete);
		btDeviceALL = (Button) view.findViewById(R.id.bt_device_all);
		btDeviceALL.setOnClickListener(this);
		btCom = (Button) view.findViewById(R.id.bt_device_com);
		btCom.setOnClickListener(this);
		btInv = (Button) view.findViewById(R.id.bt_device_inv);
		btInv.setOnClickListener(this);
		btTra = (Button) view.findViewById(R.id.bt_device_tra);
		btTra.setOnClickListener(this);
		btAll = (Button) view.findViewById(R.id.bt_all);
		btAll.setOnClickListener(this);
		btAlarm = (Button) view.findViewById(R.id.bt_alarm);
		btAlarm.setOnClickListener(this);
		btFault = (Button) view.findViewById(R.id.bt_fault);
		btFault.setOnClickListener(this);
		btTimeStart = (Button) view.findViewById(R.id.bt_time_start);
		btTimeStart.setOnClickListener(this);
		btTimeStart.setText(currentTimeMap.get("startTime"));
		btTimeEnd = (Button) view.findViewById(R.id.bt_time_end);
		btTimeEnd.setOnClickListener(this);
		btTimeEnd.setText(currentTimeMap.get("endTime"));
		Button btCancel = (Button) view.findViewById(R.id.bt_left);
		btCancel.setOnClickListener(this);
		Button btConfirm = (Button) view.findViewById(R.id.bt_right);
		btConfirm.setOnClickListener(this);
		return view;
	}

	@Override
	public void setUiBeforShow() {
		if (deviceType == DeviceType.COMBINER){
			btDeviceALL.setSelected(false);
			btTra.setSelected(false);
			btInv.setSelected(false);
			btCom.setSelected(true);
		}else if (deviceType == DeviceType.INVERTER){
			btDeviceALL.setSelected(false);
			btTra.setSelected(false);
			btInv.setSelected(true);
			btCom.setSelected(false);
		}else if (deviceType == DeviceType.TRANSFORMER){
			btDeviceALL.setSelected(false);
			btTra.setSelected(true);
			btInv.setSelected(false);
			btCom.setSelected(false);
		}else {
			btDeviceALL.setSelected(true);
			btTra.setSelected(false);
			btInv.setSelected(false);
			btCom.setSelected(false);
		}

		if (warningType == WarningType.ALARM){
			btAll.setSelected(false);
			btAlarm.setSelected(true);
			btFault.setSelected(false);
		}else if (warningType == WarningType.FAULT){
			btAll.setSelected(false);
			btAlarm.setSelected(false);
			btFault.setSelected(true);
		}else {
			btAll.setSelected(true);
			btAlarm.setSelected(false);
			btFault.setSelected(false);
		}
	}

	public void setOnFilterTypeChangedListener(OnFilterTypeChangedListener onFilterTypeChangedListener) {
		this.onFilterTypeChangedListener = onFilterTypeChangedListener;
	}

	@Override
	public void onClick(View v) {
		int i = v.getId();
		if (i == R.id.bt_device_all) {
			btDeviceALL.setSelected(true);
			btTra.setSelected(false);
			btInv.setSelected(false);
			btCom.setSelected(false);
			currentDeviceType = DeviceType.NONE;
		} else if (i == R.id.bt_device_com){
			btDeviceALL.setSelected(false);
			btTra.setSelected(false);
			btInv.setSelected(false);
			btCom.setSelected(true);
			currentDeviceType = DeviceType.COMBINER;
		} else if (i == R.id.bt_device_inv){
			btDeviceALL.setSelected(false);
			btTra.setSelected(false);
			btInv.setSelected(true);
			btCom.setSelected(false);
			currentDeviceType = DeviceType.INVERTER;
		} else if (i == R.id.bt_device_tra){
			btDeviceALL.setSelected(false);
			btTra.setSelected(true);
			btInv.setSelected(false);
			btCom.setSelected(false);
			currentDeviceType = DeviceType.TRANSFORMER;
		} else if (i == R.id.bt_all){
			btAll.setSelected(true);
			btAlarm.setSelected(false);
			btFault.setSelected(false);
			currentWarningType = WarningType.ALL;
		} else if (i == R.id.bt_alarm){
			btAll.setSelected(false);
			btAlarm.setSelected(true);
			btFault.setSelected(false);
			currentWarningType = WarningType.ALARM;
		} else if (i == R.id.bt_fault){
			btAll.setSelected(false);
			btAlarm.setSelected(false);
			btFault.setSelected(true);
			currentWarningType = WarningType.FAULT;
		}  else if (i == R.id.bt_time_start){
			bottomLine.setVisibility(View.GONE);
			bottomComplete.setVisibility(View.GONE);
			Calendar calendar = Calendar.getInstance();
			try {
				calendar.setTime(simpleDateFormat.parse(currentTimeMap.get("startTime")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			pvTime.setDate(calendar);
 			pvTime.show(btTimeStart);
		} else if (i == R.id.bt_time_end){
			Calendar calendar = Calendar.getInstance();
			try {
				calendar.setTime(simpleDateFormat.parse(currentTimeMap.get("endTime")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			pvTime.setDate(calendar);
			pvTime.show(btTimeEnd);
			bottomLine.setVisibility(View.GONE);
			bottomComplete.setVisibility(View.GONE);
		} else if (i == R.id.bt_right){
			try{
				Date startTime = simpleDateFormat.parse(currentTimeMap.get("startTime"));
				Date endTime = simpleDateFormat.parse(currentTimeMap.get("endTime"));
				if(startTime.getTime() > endTime.getTime()){
					ToastUtils.getInstance(getContext()).setText("请输入合理的时间段").show();
					return;
				}
			}catch (Exception e){
			}

			if (deviceType != currentDeviceType || warningType != currentWarningType ||
					(!timeMap.get("startTime").equals(currentTimeMap.get("startTime"))
							|| !timeMap.get("endTime").equals(currentTimeMap.get("endTime")))){
				if (onFilterTypeChangedListener != null) {
					onFilterTypeChangedListener.filterTypeChanged(currentDeviceType, currentWarningType,currentTimeMap);
				}
			}
			dismiss();
		} else if (i == R.id.bt_left){
			dismiss();
		}

	}

	public interface OnFilterTypeChangedListener{
		/**
		 * 	过滤类型改变回调
		 * @param deviceType 设备类型
		 * @param warningType 告警类型
		 */
		void filterTypeChanged(DeviceType deviceType, WarningType warningType, HashMap<String,String> timeMap);
	}
}
