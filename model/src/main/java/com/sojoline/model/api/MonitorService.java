package com.sojoline.model.api;

import com.sojoline.model.request.SolarEnergyRequest;
import com.sojoline.model.response.BaseResponse;
import com.sojoline.model.response.SolarDataResponse;
import com.sojoline.model.response.SolarStationResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/15
 *     desc   : 能耗接口
 *     version: 1.0
 * </pre>
 */

public interface MonitorService {
	/**
	 * 获取能耗监测电站列表
	 * @param type 类型：ECS
	 * @return
	 */
	@GET("v1/dpbstation/list")
	Observable<SolarStationResponse> getMonitorStationList(@Query("DPStationType") String type);

	/**
	 * 搜索能耗监测电站
	 * @param stationName 电站名称
	 * @return
	 */
	@GET("v1/dpbstation/search")
	Observable<SolarStationResponse> searchMonitorStation(@Query("DPStationName") String stationName);

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
	 * 获取能耗监测电站运营数据
	 * @param stationId 电站id
	 * @return
	 */
	@GET("v1/dpbstation/operation/info")
	Observable<SolarDataResponse> getStationData(@Query("DPStationID") String stationId);

	/**
	 * 获取能耗监测电站发电量
	 * @param request 查询参数
	 * @return
	 */
	@POST("v1/dpbstation/operation/statistic")
	Observable<BaseResponse> getStationEnergy(@Body SolarEnergyRequest request);
}
