package com.sojoline.solar.view.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.model.bean.solar.CombinerInfo;
import com.sojoline.model.bean.solar.InverterInfo;
import com.sojoline.model.bean.solar.TransformerInfo;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;
import com.sojoline.solar.adapter.DeviceListAdapter;
import com.sojoline.model.bean.solar.MonitorInfo;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Route(path = "/solar/login/device_list")
public class DeviceListActivity extends BaseCompatActivity {
	@BindView(R2.id.recycler_view)
	RecyclerView recyclerView;

	private DeviceListAdapter adapter;
	private int device;
	private Object object;
	public static final int DEVICE_TRANSFORMER = 0x0;
	public static final int DEVICE_INVERTER = 0x1;
	public static final int DEVICE_COMBINER = 0x2;
	public static final int DEVICE_MONITOR = 0x3;

	public static void navigation(int device, ArrayList<? extends Parcelable> object){
		ARouter.getInstance().build("/solar/login/device_list")
				.withInt("device",device)
				.withParcelableArrayList("object", object)
				.navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.solar_activity_device_list);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		device = getIntent().getIntExtra("device",0);
		object = getIntent().getParcelableArrayListExtra("object");
		if (device == DEVICE_TRANSFORMER){
			initToolbarNav("箱变列表");
			ArrayList<TransformerInfo> list = (ArrayList<TransformerInfo>) object;
			adapter = new DeviceListAdapter(list, device);
		}else if (device == DEVICE_INVERTER){
			initToolbarNav("逆变器列表");
			ArrayList<InverterInfo> list = (ArrayList<InverterInfo>) object;
			adapter = new DeviceListAdapter(list, device);
		}else if (device == DEVICE_COMBINER){
			initToolbarNav("汇流箱列表");
			ArrayList<CombinerInfo> list = (ArrayList<CombinerInfo>) object;
			adapter = new DeviceListAdapter(list, device);
		}else if(device == DEVICE_MONITOR){
			initToolbarNav("环境检测仪");
			ArrayList<MonitorInfo> list = (ArrayList <MonitorInfo>) object;
			adapter = new DeviceListAdapter(list, device);
		}

		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
		recyclerView.setAdapter(adapter);
	}
}
