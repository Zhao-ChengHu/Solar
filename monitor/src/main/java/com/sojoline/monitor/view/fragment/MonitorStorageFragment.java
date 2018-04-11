package com.sojoline.monitor.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sojoline.base.view.BaseFragment;
import com.sojoline.monitor.R;
import com.sojoline.monitor.R2;
import com.sojoline.monitor.view.activity.BMSInfoActivity;
import com.sojoline.monitor.view.activity.BMSListActivity;
import com.sojoline.monitor.view.activity.BMSWarningActivity;
import com.sojoline.monitor.view.activity.PCSDataActivity;
import com.sojoline.monitor.view.activity.PCSInfoActivity;
import com.sojoline.monitor.view.activity.PCSWarningActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MonitorStorageFragment extends BaseFragment {

	@BindView(R2.id.tv_state)
	TextView tvState;
	@BindView(R2.id.tv_voltage)
	TextView tvVoltage;
	@BindView(R2.id.tv_current)
	TextView tvCurrent;
	@BindView(R2.id.tv_power)
	TextView tvPower;
	@BindView(R2.id.tv_soc)
	TextView tvSoc;

	public static MonitorStorageFragment newInstance() {
		Bundle args = new Bundle();
		MonitorStorageFragment fragment = new MonitorStorageFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.monitor_fragment_storage, container, false);
	}

	@OnClick({R2.id.tv_pcs_info, R2.id.tv_pcs_data, R2.id.tv_pcs_warning, R2.id.tv_bms_info, R2.id.tv_bms_warning})
	public void onViewClicked(View view) {
		int i = view.getId();
		if (i == R.id.tv_pcs_info) {
			PCSInfoActivity.navigation();
		} else if (i == R.id.tv_pcs_data) {
			PCSDataActivity.navigation();
		} else if (i == R.id.tv_pcs_warning) {
			PCSWarningActivity.navigation();
		} else if (i == R.id.tv_bms_info) {
			BMSInfoActivity.navigation();
		} else if (i == R.id.tv_bms_warning) {
			BMSWarningActivity.navigation();
		} else if (i == R.id.tv_bms_list) {
			BMSListActivity.navigation();
		}
	}
}
