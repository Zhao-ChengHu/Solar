package com.sojoline.solar.view.fragment;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMapOptions;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.Projection;
import com.amap.api.maps2d.SupportMapFragment;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.sojoline.base.util.ScreenUtils;
import com.sojoline.base.view.BaseFragment;
import com.sojoline.basiclib.rx.RxBus;
import com.sojoline.model.bean.solar.SolarStation;
import com.sojoline.solar.R;
import com.sojoline.solar.R2;
import com.sojoline.solar.event.SolarViewEvent;
import com.sojoline.solar.view.activity.StationsActivity;

import java.util.List;

import butterknife.OnClick;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/10/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarMapFragment extends BaseFragment implements LocationSource, AMapLocationListener{
	String TAG = SolarMapFragment.class.getSimpleName();
	private AMap mMap;
	private AMapLocation mapLocation;
	private OnLocationChangedListener mListener;
	private AMapLocationClient locationClient;
	private AMapLocationClientOption locationOption;
	private boolean isFirst;

	public static final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
	public static final int FILL_COLOR   = Color.argb(10, 0, 0, 180);

	/**
	 * 默认地图的缩放级别 3-19
	 * 比例尺:13----1km
	 */
	public static final float DEFAULT_ZOOM_LEVEL = 13f;

	public static SolarMapFragment newInstance(){
		Bundle args = new Bundle();
		SolarMapFragment fragment = new SolarMapFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.solar_fragment_map, container, false);
	}

	@Override
	protected void initView(@Nullable Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		isFirst = true;
		setUpMapIfNeeded();
		initMapListener();
	}

	@Override
	public void onSupportVisible() {
		super.onSupportVisible();
	}

	private void setUpMapIfNeeded() {
		if (mMap == null) {
			mMap = ((SupportMapFragment) getChildFragmentManager()
					.findFragmentById(R.id.map)).getMap();
		}

		initMap();
		setLocationStyle();
	}

	/**
	 * 初始化Map
	 */
	private void initMap(){
		UiSettings uiSettings = mMap.getUiSettings();
		// 设置高德地图logo放置位置
		uiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);
		//设置比例尺功能可用
		uiSettings.setScaleControlsEnabled(true);
		//设置地图不允许显示缩放按钮。
		uiSettings.setZoomControlsEnabled(false);
		//设置定位资源
		mMap.setLocationSource(this);
		//设置定位按钮不显示
		uiSettings.setMyLocationButtonEnabled(false);
		//设置定位层显示
		mMap.setMyLocationEnabled(true);
		//设置地图允许通过手势来移动。
		uiSettings.setScrollGesturesEnabled(true);
	}

	/**
	 * 初始化比例尺
	 */
	private void scaleMap() {
		CameraUpdate cameraUpdate = CameraUpdateFactory.zoomTo(DEFAULT_ZOOM_LEVEL);
		mMap.animateCamera(cameraUpdate, 500, null);
	}

	/**
	 * 自定义系统定位蓝点
	 */
	private void setLocationStyle(){
		// 自定义系统定位蓝点
		MyLocationStyle myLocationStyle = new MyLocationStyle();
		// 自定义定位蓝点图标
		myLocationStyle.myLocationIcon(
				BitmapDescriptorFactory.fromResource(R.mipmap.ic_location_marker));
		// 自定义精度范围的圆形边框颜色
		myLocationStyle.strokeColor(STROKE_COLOR);
		// 自定义精度范围的圆形边框宽度
		myLocationStyle.strokeWidth(5);
		// 设置圆形的填充颜色 - 外圆填充颜色值
		myLocationStyle.radiusFillColor(FILL_COLOR);
		// 将自定义的 myLocationStyle 对象添加到地图上
		mMap.setMyLocationStyle(myLocationStyle);

	}

	private Marker addMarkerToMap(LatLng var0){
		MarkerOptions options = new MarkerOptions()
				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
				.position(var0)
				.draggable(true);
		return mMap.addMarker(options);
	}

	private void initMapListener(){
		mMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() {
			@Override
			public void onMapLoaded() {
				scaleMap();
			}
		});

		mMap.setOnMapTouchListener(new AMap.OnMapTouchListener() {
			@Override
			public void onTouch(MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
					RxBus.getInstance().postEvent(new SolarViewEvent(false));
				}
			}
		});

		mMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(Marker marker) {
				LatLng position = marker.getPosition();
				Projection projection = mMap.getProjection();
				Point point =  projection.toScreenLocation(position);
				StationsActivity activity = (StationsActivity) getActivity();
				//将marker移动到弹窗正上方
				if (activity != null) {
					Log.i(TAG, "height: " + activity.getSolarViewHeight());
					float distY = ScreenUtils.getScreenHeight() - activity.getSolarViewHeight() - ScreenUtils.dp2px(100);
					float distX = ScreenUtils.getScreenWidth() / 2;
					CameraUpdate cameraUpdate = CameraUpdateFactory.scrollBy(point.x - distX, point.y - distY);
					mMap.animateCamera(cameraUpdate);
				}
				SolarStation station = (SolarStation) marker.getObject();
				//发送显示地图底部弹窗事件
				RxBus.getInstance().postEvent(new SolarViewEvent(true, station));
				return true;
			}
		});
	}

	@OnClick({R2.id.iv_map_add, R2.id.iv_map_minus, R2.id.iv_location})
	public void onClick(View view){
		int id = view.getId();
		if (id == R.id.iv_map_add){
			if (mMap != null) {
				mMap.animateCamera(CameraUpdateFactory.zoomIn(), 1000, null);
			}
		}else if (id == R.id.iv_map_minus){
			if (mMap != null) {
				mMap.animateCamera(CameraUpdateFactory.zoomOut(), 1000, null);
			}
		}else if (id == R.id.iv_location){
			if (mapLocation != null) {
				animationLatLng(new LatLng(mapLocation.getLatitude(), mapLocation.getLongitude()));
			}
		}
	}

	private void animationLatLng(LatLng var0){
		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(var0, DEFAULT_ZOOM_LEVEL);
		mMap.animateCamera(cameraUpdate, 300, null);
	}

	// 激活定位
	@Override
	public void activate(OnLocationChangedListener onLocationChangedListener) {
		mListener = onLocationChangedListener;
		if (locationClient == null){
			locationClient = new AMapLocationClient(getContext());
			locationOption = new AMapLocationClientOption();

			// 设置定位回调监听
			locationClient.setLocationListener(this);
			// 设置定位模式：高精度模式
			locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
			// 设置发起定位请求的时间间隔: 10s
			locationOption.setInterval(10 * 1000);
			// 设置定位参数
			locationClient.setLocationOption(locationOption);
			// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
			// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
			// 在定位结束后，在合适的生命周期调用onDestroy()方法
			// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
			locationClient.startLocation();
		}
	}

	// 停止定位
	@Override
	public void deactivate() {
		mListener = null;
		if (locationClient != null) {
			locationClient.stopLocation();
			locationClient.onDestroy();
		}
		locationClient = null;
	}

	// 定位监听
	@Override
	public void onLocationChanged(AMapLocation aMapLocation) {
		if (mListener != null && aMapLocation != null) {
			if (aMapLocation.getErrorCode() == 0) {
				mapLocation = aMapLocation;
				// 显示系统小蓝点
				mListener.onLocationChanged(aMapLocation);
			} else {
				if (isFirst) {
					isFirst = false;
					showToast("定位失败");
				}
				String errText = "定位失败," + aMapLocation.getErrorCode()+ ": " + aMapLocation.getErrorInfo();
				Log.e("AmapErr",errText);
			}
		}
	}

	public void transferData(List<SolarStation> list){
		for (SolarStation station : list){
			double latitude = Double.parseDouble(station.getLatitude());
			double longitude = Double.parseDouble(station.getLongitude());
			LatLng latLng = new LatLng(latitude, longitude);
			Marker marker = addMarkerToMap(latLng);
			marker.setObject(station);
		}
	}

}
