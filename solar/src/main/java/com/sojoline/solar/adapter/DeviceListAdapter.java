package com.sojoline.solar.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sojoline.model.bean.solar.CombinerInfo;
import com.sojoline.model.bean.solar.InverterInfo;
import com.sojoline.model.bean.solar.TransformerInfo;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;
import com.sojoline.solar.view.activity.CombinerActivity;
import com.sojoline.solar.view.activity.DeviceListActivity;
import com.sojoline.solar.view.activity.InverterActivity;
import com.sojoline.solar.view.activity.TransformerActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.ViewHolder> {

	private ArrayList list;
	private int device;

	public DeviceListAdapter(ArrayList list, int device) {
		this.list = list;
		this.device = device;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.solar_item_device, parent, false);
		return new ViewHolder(item);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Object o = list.get(position);
		if (device == DeviceListActivity.DEVICE_COMBINER){
			final CombinerInfo info = (CombinerInfo) o;
			if (TextUtils.isEmpty(info.getCombinerName())){
				holder.tvName.setText(info.getCombinerID());
			}else {
				holder.tvName.setText(info.getCombinerName());
			}
			holder.tvName.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					CombinerActivity.navigation(info.getCombinerID());
				}
			});
		}else if (device == DeviceListActivity.DEVICE_TRANSFORMER){
			final TransformerInfo info = (TransformerInfo) o;
			if (TextUtils.isEmpty(info.getTransformerName())){
				holder.tvName.setText(info.getTransFormerID());
			}else {
				holder.tvName.setText(info.getTransformerName());
			}
			holder.tvName.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					TransformerActivity.navigation(info.getTransFormerID());
				}
			});
		}else {
			final InverterInfo info = (InverterInfo) o;
			if (TextUtils.isEmpty(info.getInverterName())){
				holder.tvName.setText(info.getInverterName());
			}else {
				holder.tvName.setText(info.getInverterID());
			}
			holder.tvName.setText(info.getInverterName());
			holder.tvName.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					InverterActivity.navigation(info.getInverterID());
				}
			});
		}
	}

	@Override
	public int getItemCount() {
		return list == null ? 0 : list.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder{
		@BindView(R2.id.tv_batch)
		TextView tvBatch;
		@BindView(R2.id.tv_name)
		TextView tvName;

		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
