package com.sojoline.solar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sojoline.model.bean.solar.WarningInfo;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class WarningListAdapter extends RecyclerView.Adapter<WarningListAdapter.ViewHolder> {

	private List<WarningInfo> list;

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.solar_item_warning, parent, false);
		return new ViewHolder(view);
	}

	public void setData(List<WarningInfo> list) {
		this.list = list;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		WarningInfo info = list.get(position);
		if ("Inverter".equals(info.getDeviceType())){
			holder.tvBatch.setText("逆变器：" + info.getDeviceID());
		}else if ("Combiner".equals(info.getDeviceType())){
			holder.tvBatch.setText("汇流箱：" + info.getDeviceID());
		}else if ("BoxTransfer".equals(info.getDeviceType())){
			holder.tvBatch.setText("箱变：" + info.getDeviceID());
		}else {
			holder.tvBatch.setText("未知设备：" + info.getDeviceID());
		}

		holder.tvWarningInfo.setText("告警信息：" + info.getAlarmName());
		holder.tvWarningDate.setText("告警时间：" + info.getFailureTime());

	}

	@Override
	public int getItemCount() {
		return list == null ? 0 : list.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder{
		@BindView(R2.id.tv_batch)
		TextView tvBatch;
		@BindView(R2.id.tv_warning_info)
		TextView tvWarningInfo;
		@BindView(R2.id.tv_warning_date)
		TextView tvWarningDate;
		@BindView(R2.id.tv_repair_info)
		TextView tvRepairInfo;
		@BindView(R2.id.tv_repair_date)
		TextView tvRepairDate;

		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
