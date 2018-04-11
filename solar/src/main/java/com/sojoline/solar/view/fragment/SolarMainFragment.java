package com.sojoline.solar.view.fragment;

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
import com.sojoline.basiclib.util.DateUtils;
import com.sojoline.fragmentation.SupportFragment;
import com.sojoline.model.request.SolarEnergyRequest;
import com.sojoline.model.storage.AppInfoPreferences;
import com.sojoline.presenter.solar.data.SolarEnergyContract;
import com.sojoline.presenter.solar.data.SolarEnergyPresenter;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/10/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarMainFragment extends BaseMainFragment implements SolarEnergyContract.View{
	@BindView(R2.id.bottom_navigation)
	CommonTabLayout bottomNavigation;

	private static final int FIRST  = 0;
	private static final int SECOND = 1;
	private static final int THIRD  = 2;
	private static final int FOURTH   = 3;

	private SupportFragment[] fragments = new SupportFragment[4];
	private int prePosition = -1;
	private SolarEnergyPresenter presenter;

	public static SolarMainFragment newInstance() {
		Bundle args = new Bundle();
		SolarMainFragment fragment = new SolarMainFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.solar_fragment_main, container, false);
	}

	@Override
	protected void initPresenter() {
		super.initPresenter();
		presenter = new SolarEnergyPresenter();
		presenter.attachView(this);
	}

	@Override
	protected void initView(@Nullable Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		if (savedInstanceState == null) {
			fragments[FIRST] = SolarStationFragment.newInstance();
			fragments[SECOND] = SolarEnergyFragment.newInstance();
			fragments[THIRD] = SolarDeviceFragment.newInstance();
			fragments[FOURTH] = SolarMimeFragment.newInstance();

			loadMultipleRootFragment(R.id.sub_fragment_container, FIRST, fragments);
			prePosition = FIRST;
		}else {
			fragments[FIRST] = findChildFragment(SolarStationFragment.class);
			fragments[SECOND] = findChildFragment(SolarEnergyFragment.class);
			fragments[THIRD] = findChildFragment(SolarDeviceFragment.class);
			fragments[FOURTH] = findChildFragment(SolarMimeFragment.class);
			prePosition = savedInstanceState.getInt("prePosition", FIRST);
		}

		initBottomNavigation();
	}

	@Override
	public void onSupportVisible() {
		super.onSupportVisible();
		String id = AppInfoPreferences.get().getStationId();
		SolarEnergyRequest request = new SolarEnergyRequest();
		request.setDPStationID(id);
		request.setQueryType("day");
		int year = DateUtils.getYear();
		int month = DateUtils.getMonth();
		int day = DateUtils.getDay();
		request.setQueryTime(String.format(Locale.CHINA,"%d-%s-%s", year, getFormat(month), getFormat(day)));
		presenter.getSolarEnergy(request);
	}

	private void initBottomNavigation(){
		ArrayList<CustomTabEntity> list = new ArrayList<>();
		list.add(new SimpleTabEntity("电站", R.mipmap.ic_solar_nav1_selected, R.mipmap.ic_solar_nav1_normal));
		list.add(new SimpleTabEntity("发电量", R.mipmap.ic_solar_nav2_selected, R.mipmap.ic_solar_nav2_normal));
		list.add(new SimpleTabEntity("设备", R.mipmap.ic_solar_nav3_selected, R.mipmap.ic_solar_nav3_normal));
		list.add(new SimpleTabEntity("我的", R.mipmap.ic_solar_nav4_selected, R.mipmap.ic_solar_nav4_normal));
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

	/**
	 * 填充日期格式，把一位变成两位：“1”--“01”
	 * @param num 日期
	 * @return string
	 */
	private String getFormat(int num){
		if (num > 9){
			return num + "";
		}else {
			return "0" + num;
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("prePosition", prePosition);
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
	public void energySuccess(Object o) {
		SolarStationFragment solarFragment = (SolarStationFragment) fragments[FIRST];
		solarFragment.energySuccess(o);
		SolarEnergyFragment energyFragment = (SolarEnergyFragment) fragments[SECOND];
		energyFragment.energySuccess(o);
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
	}
}
