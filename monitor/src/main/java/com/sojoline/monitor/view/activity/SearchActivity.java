package com.sojoline.monitor.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.basiclib.event.CollectionEvent;
import com.sojoline.base.util.KeyboardUtils;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.base.widget.SpaceItemDecoration;
import com.sojoline.basiclib.rx.RxBus;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.bean.solar.SolarStation;
import com.sojoline.monitor.R;
import com.sojoline.monitor.R2;
import com.sojoline.monitor.adapter.MonitorStationAdapter;
import com.sojoline.presenter.monitor.collect.CollectMonitorContract;
import com.sojoline.presenter.monitor.collect.CollectMonitorPresenter;
import com.sojoline.presenter.monitor.station.MonitorStationContract;
import com.sojoline.presenter.monitor.station.MonitorStationPresenter;
import com.trello.rxlifecycle.ActivityEvent;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/10/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Route(path = "/monitor/login/search")
public class SearchActivity extends BaseCompatActivity implements MonitorStationContract.View,
		CollectMonitorContract.View{

	@BindView(R2.id.et_search)
	EditText etSearch;
	@BindView(R2.id.iv_delete)
	ImageButton ivDelete;
	@BindView(R2.id.recycler_view)
	RecyclerView recyclerView;

	private MonitorStationAdapter adapter;
	private MonitorStationPresenter stationPresenter;
	private CollectMonitorPresenter collectPresenter;
	private List<SolarStation> list;
	private int position;

	public static void navigation() {
		ARouter.getInstance().build("/solar/login/search").navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.monitor_activity_search);
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
		RxBus.getInstance().toObservable(CollectionEvent.class)
				.compose(SchedulersCompat.<CollectionEvent>observeOnMainThread())
				.compose(this.<CollectionEvent>bindUntilEvent(ActivityEvent.DESTROY))
				.subscribe(new Action1<CollectionEvent>() {
					@Override
					public void call(CollectionEvent collectionEvent) {
						if (collectionEvent.isFavor()){
							position = collectionEvent.getPosition();
							String id = list.get(position).getDPStationID();
							//发起请求
							collectPresenter.collectMonitorStation(id);
						}
					}
				});

		etSearch.requestFocus();
		etSearch.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.length() > 0){
					ivDelete.setVisibility(View.VISIBLE);
				}else {
					ivDelete.setVisibility(View.GONE);

				}
			}
		});

		etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH){
					String s = etSearch.getText().toString();
					showToast("点击了搜索");
					if (TextUtils.isEmpty(s)){
						showToast("请输入电站名称");
					}else {
						searchStation(s);
						//隐藏软键盘
						KeyboardUtils.hideSoftInput(SearchActivity.this);
					}
				}
				return false;
			}
		});

		if (adapter == null) {
			recyclerView.setHasFixedSize(true);
			recyclerView.addItemDecoration(new SpaceItemDecoration(5));
			recyclerView.setLayoutManager(new LinearLayoutManager(this));
			adapter = new MonitorStationAdapter();
			adapter.setShowAdd(true);
			recyclerView.setAdapter(adapter);

		}else {
			adapter = (MonitorStationAdapter) recyclerView.getAdapter();
			adapter.notifyDataSetChanged();
		}
	}

	@OnClick({R2.id.iv_delete, R2.id.bt_cancel})
	public void onViewClicked(View view) {
		int i = view.getId();
		if (i == R.id.iv_delete) {
			etSearch.setText("");
			etSearch.requestFocus();
		} else if (i == R.id.bt_cancel) {
			finish();
		}
	}

	/**
	 * 搜索电站
	 * @param name
	 */
	private void searchStation(String name){
		stationPresenter.searchStation(name);
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
	public void success(List<SolarStation> list) {
		if (list != null && list.size() > 0){
			if (adapter == null) {
				recyclerView.setHasFixedSize(true);
				recyclerView.addItemDecoration(new SpaceItemDecoration(5));
				recyclerView.setLayoutManager(new LinearLayoutManager(this));
				adapter = new MonitorStationAdapter();
				adapter.setShowAdd(true);
				adapter.setList(list);
				recyclerView.setAdapter(adapter);

			}else {
				adapter = (MonitorStationAdapter) recyclerView.getAdapter();
				adapter.setList(list);
				adapter.notifyDataSetChanged();
			}
		}else {
			showToast("没有搜索到相关光伏电站");
		}
	}

	@Override
	public void collectSuccess() {
		SolarStation station = list.get(position);
		station.setIsFavorites(true);
		adapter.setList(list);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void uncollectSuccess() {

	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		stationPresenter.detachView();
		collectPresenter.detachView();
	}
}
