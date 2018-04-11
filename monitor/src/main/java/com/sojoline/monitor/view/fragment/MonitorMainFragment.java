package com.sojoline.monitor.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.sojoline.base.view.BaseMainFragment;
import com.sojoline.base.widget.SimpleTabEntity;
import com.sojoline.fragmentation.SupportFragment;
import com.sojoline.monitor.R;
import com.sojoline.monitor.R2;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MonitorMainFragment extends BaseMainFragment {
	@BindView(R2.id.bottom_navigation)
	CommonTabLayout bottomNavigation;

	private static final int FIRST  = 0;
	private static final int SECOND = 1;
	private static final int THIRD  = 2;
	private static final int FOURTH   = 3;

	private SupportFragment[] fragments = new SupportFragment[3];
	private int prePosition = -1;

	public static MonitorMainFragment newInstance() {
		Bundle args = new Bundle();
		MonitorMainFragment fragment = new MonitorMainFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.monitor_fragment_main, container, false);
	}

	@Override
	protected void initView(@Nullable Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		if (savedInstanceState == null) {
			fragments[FIRST] = MonitorSolarFragment.newInstance();
			fragments[SECOND] = MonitorStorageFragment.newInstance();
//			fragments[THIRD] = MonitorLoadFragment.newInstance();
			fragments[THIRD] = MonitorMimeFragment.newInstance();

			loadMultipleRootFragment(R.id.sub_fragment_container, FIRST, fragments);
			prePosition = FIRST;
		}else {
			fragments[FIRST] = findChildFragment(MonitorSolarFragment.class);
			fragments[SECOND] = findChildFragment(MonitorStorageFragment.class);
//			fragments[THIRD] = findChildFragment(MonitorLoadFragment.class);
			fragments[THIRD] = findChildFragment(MonitorMimeFragment.class);
			prePosition = savedInstanceState.getInt("prePosition", FIRST);
		}

		initBottomNavigation();
	}

	private void initBottomNavigation(){
		ArrayList<CustomTabEntity> list = new ArrayList<>();
		list.add(new SimpleTabEntity("光伏", R.mipmap.ic_monitor_nav1_selected, R.mipmap.ic_monitor_nav1_normal));
		list.add(new SimpleTabEntity("储能", R.mipmap.ic_monitor_nav2_selected, R.mipmap.ic_monitor_nav2_normal));
//		list.add(new SimpleTabEntity("负荷", R.mipmap.ic_monitor_nav3_selected, R.mipmap.ic_monitor_nav3_normal));
		list.add(new SimpleTabEntity("我的", R.mipmap.ic_monitor_nav4_selected, R.mipmap.ic_monitor_nav4_normal));
		bottomNavigation.setTabData(list);

		bottomNavigation.setOnTabSelectListener(new OnTabSelectListener() {
			@Override
			public void onTabSelect(int position) {
				showHideFragment(fragments[position], fragments[prePosition]);
				prePosition = position;
			}

			@Override
			public void onTabReselect(int position) {

			}
		});
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("prePosition", prePosition);
	}
}
