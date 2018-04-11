package com.sojoline.solar.view.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.base.widget.DeviceType;
import com.sojoline.base.widget.WarningType;
import com.sojoline.basiclib.DebugLog;
import com.sojoline.model.bean.solar.WarningInfo;
import com.sojoline.model.response.WarningResponse;
import com.sojoline.model.storage.AppInfoPreferences;
import com.sojoline.presenter.solar.data.SolarWarningContract;
import com.sojoline.presenter.solar.data.SolarWarningPresenter;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;
import com.sojoline.solar.adapter.WarningListAdapter;
import com.sojoline.solar.widget.DeviceFilterDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Route(path = "/solar/login/warning_list")
public class WarningListActivity extends BaseCompatActivity implements SolarWarningContract.View{
	@BindView(R2.id.recycler_view)
	RecyclerView recyclerView;

	private WarningListAdapter adapter;
	private DeviceType deviceType;
	private WarningType warningType;
	private SolarWarningPresenter presenter;
	private LinearLayoutManager manager;
	private HashMap<String, Object> map;
	private int currentPage;
	private int minItem = 2;
	private boolean isLoadMore;
	private WarningResponse.Content content;
	private List<WarningInfo> list;

	public static void navigation(){
		ARouter.getInstance().build("/solar/login/warning_list").navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.solar_activity_warning_list);
	}

	@Override
	protected void initPresenter() {
		super.initPresenter();
		presenter = new SolarWarningPresenter();
		presenter.attachView(this);
	}

	@TargetApi(Build.VERSION_CODES.M)
	@Override
	protected void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		initToolbarNav("告警列表");
		isLoadMore = false;
		list = new ArrayList<>();
		String id = AppInfoPreferences.get().getStationId();
		map = new HashMap<>();
		map.put("DPStationID", id);
		currentPage = 1;
		map.put("page", currentPage);
		map.put("prePage", 20);
		map.put("warningType", "All");
		//发起请求
		presenter.getWarningList(map);

		deviceType = DeviceType.NONE;
		warningType = WarningType.ALL;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.solar_menu_warning, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_filter){
			final DeviceFilterDialog dialog = new DeviceFilterDialog(WarningListActivity.this, deviceType, warningType);
			dialog.setOnFilterTypeChangedListener(new DeviceFilterDialog.OnFilterTypeChangedListener() {
				@Override
				public void filterTypeChanged(DeviceType deviceType, WarningType warningType) {
					WarningListActivity.this.deviceType = deviceType;
					WarningListActivity.this.warningType = warningType;
					if (deviceType == DeviceType.COMBINER){
						map.put("DeviceType", "com");
						DebugLog.log("com");
					}else if (deviceType == DeviceType.INVERTER){
						map.put("DeviceType", "inv");
						DebugLog.log("inv");
					}else if (deviceType == DeviceType.TRANSFORMER){
						map.put("DeviceType", "tra");
						DebugLog.log("tra");
					}else {
						map.remove("DeviceType");
						DebugLog.log("device");
					}

					if (warningType == WarningType.ALARM){
						map.put("warningType", "Alarm");
						DebugLog.log("告警");
					}else if (warningType == WarningType.FAULT){
						map.put("warningType", "Fault");
						DebugLog.log("故障");
					}else {
						map.put("warningType", "Alarm");
						DebugLog.log("warning");
					}
					list.clear();
					//这里需要把page改成1
					currentPage = 1;
					map.put("page", currentPage);
					//发起请求
					presenter.getWarningList(map);
				}
			});
			dialog.show();
			dialog.setCanceledOnTouchOutside(false);
		}
		return super.onOptionsItemSelected(item);
	}

	//RecyclerView 滑动监听：如果滑动到倒数第2个时，就加载更多
	RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			super.onScrolled(recyclerView, dx, dy);
			int count = manager.getItemCount();
			int lastPosition = manager.findLastVisibleItemPosition();
			if (!isLoadMore && lastPosition >= count - minItem){
				isLoadMore = true;
				loadMore();
			}
		}
	};

	/**
	 * 加载更多
	 */
	private void loadMore() {
		if (content != null){
			if (content.getTotal() > currentPage * 20){
				currentPage++;
				map.put("page", currentPage);
				presenter.getWarningList(map);
			}
		}
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
	}

	@Override
	public void showLoading(String msg) {

	}

	@Override
	public void showNormal() {

	}

	@Override
	public void requestFailed(String msg) {
		isLoadMore = false;
		if (msg != null) {
			showToast(msg);
		} else {
			showToast(R.string.network_not_available);
		}
	}

	@Override
	public void success(WarningResponse.Content content) {
		isLoadMore = false;
		this.content = content;
		if (content != null){
			list.addAll(content.getList());
			if (adapter == null) {
				adapter = new WarningListAdapter();
				adapter.setData(list);
				recyclerView.setHasFixedSize(true);
				manager = new LinearLayoutManager(this);
				recyclerView.setLayoutManager(manager);
				recyclerView.addOnScrollListener(listener);
				recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
				recyclerView.setAdapter(adapter);
			}else {
				adapter = (WarningListAdapter) recyclerView.getAdapter();
				adapter.setData(list);
				adapter.notifyDataSetChanged();
			}
		}

	}
}
