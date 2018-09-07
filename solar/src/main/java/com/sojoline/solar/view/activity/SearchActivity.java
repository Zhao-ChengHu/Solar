package com.sojoline.solar.view.activity;

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
import android.widget.ImageView;
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
import com.sojoline.presenter.solar.collect.CollectSolarContract;
import com.sojoline.presenter.solar.collect.CollectSolarPresenter;
import com.sojoline.presenter.solar.station.SolarStationContract;
import com.sojoline.presenter.solar.station.SolarStationPresenter;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;
import com.sojoline.solar.adapter.CollectionAdapter;
import com.trello.rxlifecycle.ActivityEvent;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * <pre>
 *     @author : zhaochenghu
 *     time   : 2018/09/07
 *     desc   : 搜索电站
 *     version: 1.0
 * </pre>
 */
@Route(path = "/solar/login/search")
public class SearchActivity extends BaseCompatActivity implements CollectSolarContract.View, SolarStationContract.View{

	@BindView(R2.id.et_search)
	EditText etSearch;
	@BindView(R2.id.iv_1)
	ImageView imageView;
	@BindView(R2.id.iv_delete)
	ImageButton ivDelete;
	@BindView(R2.id.recycler_view)
	RecyclerView recyclerView;

	private CollectionAdapter adapter;
	private SolarStationPresenter stationPresenter;
	private CollectSolarPresenter collectPresenter;
	private List<SolarStation> list;
	private int position;

	public static void navigation() {
		ARouter.getInstance().build("/solar/login/search").navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.solar_activity_search);
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
							collectPresenter.collectSolarStation(id);
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
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String s = etSearch.getText().toString();
				if (TextUtils.isEmpty(s)) {
					showToast("请输入电站名称");
				} else {
					searchStation(s);
					//隐藏软键盘
					KeyboardUtils.hideSoftInput(SearchActivity.this);
				}
			}
		});
		etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH){
					String s = etSearch.getText().toString();
//					showToast("点击了搜索");
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

	}

	@OnClick({R2.id.bt_cancel})
	public void onViewCancel(View view){
		int i = view.getId();
		if (i == R.id.bt_cancel) {
			String s = etSearch.getText().toString();
			if (TextUtils.isEmpty(s)) {
				showToast("请输入电站名称");
			} else {
				searchStation(s);
				//隐藏软键盘
				KeyboardUtils.hideSoftInput(SearchActivity.this);
			}
		}
	}

	@OnClick({R2.id.iv_delete/*, R2.id.bt_cancel*/})
	public void onViewClicked(View view) {
		int i = view.getId();
		if (i == R.id.iv_delete) {
			etSearch.setText("");
			etSearch.requestFocus();
		} /*else if (i == R.id.bt_cancel) {
			finish();
		}*/
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		stationPresenter.detachView();
		collectPresenter.detachView();
	}

	/**
	 * 搜索电站
	 * @param name 电站名字
	 */
	private void searchStation(String name){
		//发起请求：
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
		this.list = list;
		if (list != null && list.size() > 0){
			if (adapter == null) {
				recyclerView.setHasFixedSize(true);
				recyclerView.addItemDecoration(new SpaceItemDecoration(5));
				recyclerView.setLayoutManager(new LinearLayoutManager(this));
				adapter = new CollectionAdapter();
				adapter.setShowAdd(true);
				adapter.setList(list);
				recyclerView.setAdapter(adapter);

			}else {
				adapter = (CollectionAdapter) recyclerView.getAdapter();
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
}
