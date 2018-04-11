package com.sojoline.solar.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sojoline.base.view.BaseFragment;
import com.sojoline.model.bean.solar.SolarStation;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;
import com.sojoline.solar.adapter.CollectionAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/10/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarListFragment extends BaseFragment {
	@BindView(R2.id.recycler_view)
	RecyclerView recyclerView;

	private CollectionAdapter adapter;

	public static SolarListFragment newInstance(){
		Bundle args = new Bundle();
		SolarListFragment fragment = new SolarListFragment();
		fragment.setArguments(args);
		return fragment;
	}
	@Override
	protected View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.solar_fragment_list, container, false);
	}

	@Override
	protected void initView(@Nullable Bundle savedInstanceState) {
		super.initView(savedInstanceState);
	}

	public void transferData(List<SolarStation> list){
		if (adapter == null){
			recyclerView.setHasFixedSize(true);
			recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
			recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
			adapter = new CollectionAdapter();
			adapter.setShowAdd(false);
			adapter.setList(list);
			recyclerView.setAdapter(adapter);
		}else {
			adapter = (CollectionAdapter) recyclerView.getAdapter();
			adapter.setList(list);
			adapter.notifyDataSetChanged();
		}
	}
}
