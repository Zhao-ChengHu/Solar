package com.sojoline.solar.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sojoline.basiclib.event.CollectionEvent;
import com.sojoline.base.StartActivityEvent;
import com.sojoline.base.toast.IToast;
import com.sojoline.base.toast.ToastUtils;
import com.sojoline.base.util.Utils;
import com.sojoline.basiclib.rx.RxBus;
import com.sojoline.model.bean.solar.SolarStation;
import com.sojoline.model.storage.AppInfoPreferences;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;
import com.sojoline.solar.view.activity.SolarMainActivity;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/10/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {
	private List<SolarStation> list;
	private boolean showAdd;

	public CollectionAdapter() {
	}

	public CollectionAdapter(List<SolarStation> list) {
		this.list = list;
	}

	public List<SolarStation> getList() {
		return list;
	}

	public void setList(List<SolarStation> list) {
		this.list = list;
	}

	public void setShowAdd(boolean showAdd){
		this.showAdd = showAdd;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.solar_item_station, parent, false);
		return new ViewHolder(item);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, final int position) {
		final SolarStation station = list.get(position);
		holder.tvStationName.setText(station.getDPStationName());
		holder.tvStationAddress.setText(String.format(Locale.CHINA, "地址：%s", station.getAddress()));
		double capacity;
		if (!TextUtils.isEmpty(station.getInstalledCapacity())){
			capacity = Double.parseDouble(station.getInstalledCapacity());
		}else {
			capacity = 0l;
		}
		holder.tvStationPower.setText(String.format(Locale.CHINA, "装机容量：%.2fkW", capacity));
		holder.tvTotalEnergy.setText(String.format(Locale.CHINA, "累计发电量：%.2fkWh", station.getPvTotalElectric()));
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
		holder.clStation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AppInfoPreferences.get().setStationId(station.getDPStationID());
				AppInfoPreferences.get().setCreateTime(station.getCreateTime());
				RxBus.getInstance().postEvent(new StartActivityEvent(SolarMainActivity.class));
			}
		});
	}

	@Override
	public int getItemCount() {
		return list == null ? 0 : list.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		@BindView(R2.id.iv_solar)
		ImageView ivSolar;
		@BindView(R2.id.tv_station_name)
		TextView tvStationName;
		@BindView(R2.id.tv_station_address)
		TextView tvStationAddress;
		@BindView(R2.id.tv_total_energy)
		TextView tvTotalEnergy;
		@BindView(R2.id.tv_station_power)
		TextView tvStationPower;
		@BindView(R2.id.cl_station)
		ConstraintLayout clStation;
		@BindView(R2.id.ll_add)
		LinearLayout llAdd;
		@BindView(R2.id.tv_add)
		TextView tvAdd;

		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			if (showAdd){
				llAdd.setVisibility(View.VISIBLE);
				clStation.setClickable(false);
			}else {
				llAdd.setVisibility(View.GONE);
				clStation.setClickable(true);
			}
		}
	}
}
