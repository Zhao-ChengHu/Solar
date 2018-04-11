package com.sojoline.solar.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
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
import com.sojoline.presenter.solar.collect.CollectSolarContract;
import com.sojoline.presenter.solar.collect.CollectSolarPresenter;
import com.sojoline.presenter.solar.station.SolarStationContract;
import com.sojoline.presenter.solar.station.SolarStationPresenter;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;
import com.sojoline.solar.adapter.CollectionAdapter;
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
 *     time   : 2017/10/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Route(path = "/solar/login/collection")
public class CollectionActivity extends BaseCompatActivity implements SolarStationContract.View,
		CollectSolarContract.View{

	@BindView(R2.id.recycler_view)
	SwipeMenuRecyclerView recyclerView;
	@BindView(R2.id.ll_none)
	LinearLayout llNone;

	private CollectionAdapter adapter;
	private SolarStationPresenter stationPresenter;
	private CollectSolarPresenter collectPresenter;
	private List<SolarStation> list;
	private int closePosition;

	public static void navigation() {
		ARouter.getInstance().build("/solar/login/collection").navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.solar_activity_collection);
	}

	@Override
	protected void initPresenter() {
		super.initPresenter();
		stationPresenter = new SolarStationPresenter();
		stationPresenter.attachView(this);
		collectPresenter = new CollectSolarPresenter();
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
				SwipeMenuItem deleteItem = new SwipeMenuItem(CollectionActivity.this)
						.setBackgroundColorResource(R.color.primary_text_black)
						.setImage(R.mipmap.ic_delete1)
						.setWidth(width)
						.setHeight(height);
				swipeRightMenu.addMenuItem(deleteItem);
			}
		};
		recyclerView.setSwipeMenuCreator(creator);

		//这里需要使用ActivityEvent.DESTROY，防止添加电站返回后，点击无法跳转界面
		RxBus.getInstance().toObservable(StartActivityEvent.class)
				.compose(SchedulersCompat.<StartActivityEvent>observeOnMainThread())
				.compose(this.<StartActivityEvent>bindUntilEvent(ActivityEvent.DESTROY))
				.subscribe(new Action1<StartActivityEvent>() {
					@Override
					public void call(StartActivityEvent startActivityEvent) {
						Intent intent = new Intent(CollectionActivity.this,startActivityEvent.getActivity());
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);
						finish();
					}
				});

		//发起请求：
		stationPresenter.getStationList("PVS");

	}

	@Override
	protected void onRestart() {
		super.onRestart();
		//再次发起请求：
		stationPresenter.getStationList("PVS");
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		stationPresenter.detachView();
		collectPresenter.detachView();
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
		if (list != null && list.size() > 0){
			recyclerView.setVisibility(View.VISIBLE);
			llNone.setVisibility(View.GONE);
			this.list = list;
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
					collectPresenter.uncollectSolarStation(list.get(itemPosition).getDPStationID());
					closePosition = itemPosition;
				}
			});
			adapter = new CollectionAdapter();
			adapter.setList(list);
			recyclerView.setAdapter(adapter);
		}else {
			recyclerView.setVisibility(View.GONE);
			llNone.setVisibility(View.VISIBLE);
		}


	}

	@Override
	public void collectSuccess() {

	}

	@Override
	public void uncollectSuccess() {
		//更新UI
		Log.i("TAG", "uncollectSuccess: 更新UI");
		list.remove(closePosition);
		adapter = (CollectionAdapter) recyclerView.getOriginAdapter();
		adapter.setList(list);
		adapter.notifyDataSetChanged();
	}
}
