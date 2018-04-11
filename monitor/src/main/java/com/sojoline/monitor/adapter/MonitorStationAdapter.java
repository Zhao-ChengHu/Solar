package com.sojoline.monitor.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sojoline.basiclib.event.CollectionEvent;
import com.sojoline.base.toast.IToast;
import com.sojoline.base.toast.ToastUtils;
import com.sojoline.base.util.Utils;
import com.sojoline.basiclib.rx.RxBus;
import com.sojoline.model.bean.solar.SolarStation;
import com.sojoline.model.storage.AppInfoPreferences;
import com.sojoline.monitor.R;
import com.sojoline.monitor.R2;
import com.sojoline.monitor.view.activity.MonitorMainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MonitorStationAdapter extends RecyclerView.Adapter<MonitorStationAdapter.ViewHolder>{
	private boolean showAdd;
	private List<SolarStation> list;

	public void setShowAdd(boolean showAdd){
		this.showAdd = showAdd;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_monitor_station, parent,false);
		return new ViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, final int position) {
		final SolarStation station = list.get(position);
		holder.tvStationName.setText(station.getDPStationName());
		holder.tvStationAddress.setText("地址：" + station.getAddress());
		holder.tvStationPower.setText("装机容量：" + station.getInstalledCapacity() + "kW");
		if (station.isFavorites()){
			holder.tvAdd.setText("已添加");
		}else {
			holder.tvAdd.setText("添加");
		}
		holder.tvAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if ("添加".equals(holder.tvAdd.getText())){
					RxBus.getInstance().postEvent(new CollectionEvent(true, position));
				}else {

					ToastUtils.getInstance(Utils.getContext()).makeTextShow("已添加", IToast.LENGTH_SHORT);
					//RxBus.getInstance().postEvent(new CollectionEvent(false, position));
				}
			}
		});

		holder.rlStation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AppInfoPreferences.get().setStationId(station.getDPStationID());
				MonitorMainActivity.navigation();
			}
		});
	}

	@Override
	public int getItemCount() {
		return list == null ? 0 : list.size();
	}

	public void setList(List<SolarStation> list) {
		this.list = list;
	}

	class ViewHolder extends RecyclerView.ViewHolder{
		@BindView(R2.id.rl_station)
		RelativeLayout rlStation;
		@BindView(R2.id.tv_station_name)
		TextView tvStationName;
		@BindView(R2.id.tv_station_address)
		TextView tvStationAddress;
		@BindView(R2.id.tv_station_power)
		TextView tvStationPower;
		@BindView(R2.id.ll_add)
		LinearLayout llAdd;
		@BindView(R2.id.tv_add)
		TextView tvAdd;

		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);

			if (showAdd){
				llAdd.setVisibility(View.VISIBLE);
			}else {
				llAdd.setVisibility(View.GONE);
			}
		}
	}
}
