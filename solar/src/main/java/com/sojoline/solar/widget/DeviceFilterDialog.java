package com.sojoline.solar.widget;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.flyco.dialog.widget.base.BaseDialog;
import com.sojoline.base.widget.DeviceType;
import com.sojoline.base.widget.WarningType;
import com.sojoline.solar.R;

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
	private DeviceType deviceType, currentDeviceType;
	private WarningType warningType, currentWarningType;
	private OnFilterTypeChangedListener onFilterTypeChangedListener;

	public DeviceFilterDialog(Context context, DeviceType deviceType, WarningType warningType) {
		super(context);
		this.deviceType = deviceType;
		this.warningType = warningType;
		currentDeviceType = deviceType;
		currentWarningType = warningType;
	}

	@Override
	public View onCreateView() {
		widthScale(0.85f);
		View view = View.inflate(mContext, R.layout.solar_dialog_filter, null);
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
		} else if (i == R.id.bt_right){
			if (deviceType != currentDeviceType || warningType != currentWarningType){
				if (onFilterTypeChangedListener != null) {
					onFilterTypeChangedListener.filterTypeChanged(currentDeviceType, currentWarningType);
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
		void filterTypeChanged(DeviceType deviceType, WarningType warningType);
	}
}
