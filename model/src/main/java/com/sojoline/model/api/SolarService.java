package com.sojoline.model.api;

import com.sojoline.model.request.SolarEnergyRequest;
import com.sojoline.model.response.BaseResponse;
import com.sojoline.model.response.CombinerDataResponse;
import com.sojoline.model.response.InverterDataResponse;
import com.sojoline.model.response.MonitorDataResponse;
import com.sojoline.model.response.MonthElectricResponse;
import com.sojoline.model.response.PowermeterDataResponse;
import com.sojoline.model.response.SolarDataResponse;
import com.sojoline.model.response.SolarDeviceResponse;
import com.sojoline.model.response.DayElectricResponse;
import com.sojoline.model.response.SolarElectricResponse;
import com.sojoline.model.response.SolarStationResponse;
import com.sojoline.model.response.TotalElectricResponse;
import com.sojoline.model.response.TransformerDataResponse;
import com.sojoline.model.response.WarningResponse;
import com.sojoline.model.response.YearElectricResponse;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * <pre>
 *     author : 李小勇
 *     time   : 2017/09/19
 *     desc   : 光伏接口
 *     version: 1.0
 * </pre>
 */

public interface SolarService {
	/**
	 * 获取光伏电站列表
	 * @param type 类型：PVS
	 * @return
	 */
	@GET("v1/dpbstation/list")
	Observable<SolarStationResponse> getSolarStationList(@Query("DPStationType") String type);

	/**
	 * 搜索光伏电站
	 * @param stationName 电站名称
	 * @return
	 */
	@GET("v1/dpbstation/search")
	Observable<SolarStationResponse> searchSolarStation(@Query("DPStationName") String stationName);

	/**
	 * 收藏电站
	 * @param stationId 电站id
	 * @return
	 */
	@GET("v1/collect/dpstation")
	Observable<BaseResponse> collectStation(@Query("DPStationID") String stationId);

	/**
	 * 取消收藏电站
	 * @param stationId 电站id
	 * @return
	 */
	@GET("v1/uncollect/dpstation")
	Observable<BaseResponse> uncollectStation(@Query("DPStationID") String stationId);

	/**
	 * 获取光伏电站运营数据
	 * @param stationId 电站id
	 * @return
	 */
	@GET("v1/dpbstation/operation/info")
	Observable<SolarDataResponse> getStationData(@Query("DPStationID") String stationId);

	/**
	 * 获取光伏电站发电量
	 * 年、月、日
	 * @param request 查询参数
	 * @return
	 */
	@POST("v1/dpbstation/operation/statistic")
	Observable<SolarElectricResponse> getSolarEnergy(@Body SolarEnergyRequest request);

	/**
	 * 获取光伏电站发电量（天）
	 * @param request 查询参数
	 * @return
	 */
	@POST("v1/dpbstation/operation/statistic")
	Observable<DayElectricResponse> getDayEnergy(@Body SolarEnergyRequest request);

	/**
	 * 获取光伏电站发电量（月）
	 * @param request 查询参数
	 * @return
	 */
	@POST("v1/dpbstation/operation/statistic")
	Observable<MonthElectricResponse> getMonthEnergy(@Body SolarEnergyRequest request);

	/**
	 * 获取光伏电站发电量（年）
	 * @param request 查询参数
	 * @return
	 */
	@POST("v1/dpbstation/operation/statistic")
	Observable<YearElectricResponse> getYearEnergy(@Body SolarEnergyRequest request);

	/**
	 * 获取光伏电站发电量(总)
	 * @param request 查询参数
	 * @return
	 */
	@POST("v1/dpbstation/operation/statistic")
	Observable<TotalElectricResponse> getTotalEnergy(@Body SolarEnergyRequest request);

	/**
	 * 获取电站设备信息
	 * @param stationId 电站id
	 * @return
	 */
	@GET("v1/dbstation/general")
	Observable<SolarDeviceResponse> getStationDeviceInfo(@Query("DPStationID") String stationId);

	/**
	 * 获取设备实时数据
	 * @param deviceId 设备id
	 * @return
	 */
	@GET("v1/dbstation/device/realtime/data")
	Observable<BaseResponse> getDeviceData(@Query("DeviceID") String deviceId);

	/**
	 * 获取汇流箱实时数据
	 * @param deviceId 设备id
	 * @return
	 */
	@GET("v1/dbstation/device/realtime/data")
	Observable<CombinerDataResponse> getCombinerData(@Query("DeviceID") String deviceId);

	/**
	 * 获取逆变器实时数据
	 * @param deviceId 设备id
	 * @return
	 */
	@GET("v1/dbstation/device/realtime/data")
	Observable<InverterDataResponse> getInverterData(@Query("DeviceID") String deviceId);

	/**
	 * 获取箱变实时数据
	 * @param deviceId 设备id
	 * @return
	 */
	@GET("v1/dbstation/device/realtime/data")
	Observable<TransformerDataResponse> getTransformerData(@Query("DeviceID") String deviceId);

	/**
	 * 获取环境测试仪实时数据
	 * @param deviceId 设备id
	 * @return
	 */
	@GET("v1/dbstation/device/realtime/data")
	Observable<MonitorDataResponse> getMonitorData(@Query("DeviceID") String deviceId);
	/**
	 * 获取多功能电力仪实时数据
	 * @param deviceId 设备id
	 * @return
	 */
	@GET("v1/dbstation/device/realtime/data")
	Observable<PowermeterDataResponse> getPowermeterData(@Query("DeviceID") String deviceId);

	/**
	 * 获取告警信息
	 * @param request 查询参数
	 * @return
	 */
	@GET("v1/dpstation/device/alarm/records")
	Observable<WarningResponse> getWarningList(@QueryMap HashMap<String, Object> request);
}
