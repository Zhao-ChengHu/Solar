package com.sojoline.model.request;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarEnergyRequest {
	private String DPStationID;
	private String queryTime;
	private String queryType;

	public SolarEnergyRequest() {
	}

	public SolarEnergyRequest(String DPStationID, String queryTime, String queryType) {
		this.DPStationID = DPStationID;
		this.queryTime = queryTime;
		this.queryType = queryType;
	}

	public String getDPStationID() {
		return DPStationID;
	}

	public void setDPStationID(String DPStationID) {
		this.DPStationID = DPStationID;
	}

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
}
