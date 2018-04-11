package com.sojoline.monitor.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.StartActivityEvent;
import com.sojoline.base.util.ScreenUtils;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.basiclib.rx.RxBus;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.bean.solar.SolarStation;
import com.sojoline.monitor.adapter.MonitorStationAdapter;
import com.sojoline.monitor.R;
import com.sojoline.monitor.R2;
import com.sojoline.presenter.monitor.collect.CollectMonitorContract;
import com.sojoline.presenter.monitor.collect.CollectMonitorPresenter;
import com.sojoline.presenter.monitor.station.MonitorStationContract;
import com.sojoline.presenter.monitor.station.MonitorStationPresenter;
import com.trello.rxlifecycle.ActivityEvent;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Route(path = "/monitor/login/monitor_station")
public class MonitorStationActivity extends BaseCompatActivity implements MonitorStationContract.View,
		CollectMonitorContract.View{
	@BindView(R2.id.recycler_view)
	SwipeMenuRecyclerView recyclerView;
	@BindView(R2.id.ll_none)
	LinearLayout llNone;

	private MonitorStationAdapter adapter;
	private MonitorStationPresenter stationPresenter;
	private CollectMonitorPresenter collectPresenter;
	private List<SolarStation> list;
	private int closePosition;

	public static void navigation(){
		ARouter.getInstance().build("/monitor/login/monitor_station").navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.monitor_activity_station);
	}

	@Override
	protected void initPresenter() {
		super.initPresenter();
		stationPresenter = new MonitorStationPresenter();
		stationPresenter.attachView(this);
		collectPresenter = new CollectMonitorPresenter();
		collectPresenter.attachView(this);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		initToolbarNav("我的电站");

		recyclerView.setVisibility(View.VISIBLE);
		llNone.setVisibility(View.GONE);

		recyclerView.setHasFixedSize(true);
		recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		//创建menu
		SwipeMenuCreator creator = new SwipeMenuCreator() {
			@Override
			public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
				int width = ScreenUtils.dp2px(100);
				int height = ViewGroup.LayoutParams.MATCH_PARENT;
				SwipeMenuItem deleteItem = new SwipeMenuItem(MonitorStationActivity.this)
						.setBackgroundColorResource(R.color.primary_text_black)
						.setImage(R.mipmap.ic_delete1)
						.setWidth(width)
						.setHeight(height);
				swipeRightMenu.addMenuItem(deleteItem);
			}
		};
		recyclerView.setSwipeMenuCreator(creator);

		RxBus.getInstance().toObservable(StartActivityEvent.class)
				.compose(SchedulersCompat.<StartActivityEvent>observeOnMainThread())
				.compose(this.<StartActivityEvent>bindUntilEvent(ActivityEvent.DESTROY))
				.subscribe(new Action1<StartActivityEvent>() {
					@Override
					public void call(StartActivityEvent startActivityEvent) {
						Intent intent = new Intent(MonitorStationActivity.this,startActivityEvent.getActivity());
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);
						finish();
					}
				});

		//发起请求：
		stationPresenter.getStationList("ECS");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		//发起请求：
		stationPresenter.getStationList("ECS");
	}

	@OnClick({R2.id.iv_add, R2.id.bt_add})
	public void onViewClicked(View view) {
		SearchActivity.navigation();
	}

	@Override
	public void showLoading(String msg) {

	}

	@Override
	public void showNormal() {

	}

	@Override
	public void requestFailed(String msg) {
		if (msg != null) {
			showToast(msg);
		}else {
			showToast(R.string.network_not_available);
		}
	}

	@Override
	public void success(final List<SolarStation> list) {
		this.list = list;
		SolarStation station = new SolarStation();
		station.setId(123456);
		station.setDPStationName("AAAAA");
		station.setAddress("zzzzzz");
		station.setInstalledCapacity("234");
		list.add(station);
		//这里需要清除SwipeMenuRecyclerView的adapter，否则无法再次设置SwipeMenuItemClickListener
		recyclerView.setAdapter(null);
		//menu点击
		//再次设置SwipeMenuItemClickListener是为了保持list同步。
		recyclerView.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
			@Override
			public void onItemClick(SwipeMenuBridge menuBridge) {
				menuBridge.closeMenu();
				int itemPosition = menuBridge.getAdapterPosition();
				//发起取消添加请求
				collectPresenter.uncollectMonitorStation(list.get(itemPosition).getDPStationID());
				closePosition = itemPosition;
			}
		});

		adapter = new MonitorStationAdapter();
		adapter.setList(list);
		recyclerView.setAdapter(adapter);
	}

	@Override
	public void collectSuccess() {

	}

	@Override
	public void uncollectSuccess() {
		list.remove(closePosition);
		adapter = (MonitorStationAdapter) recyclerView.getOriginAdapter();
		adapter.setList(list);
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		stationPresenter.detachView();
		collectPresenter.detachView();
	}
}
