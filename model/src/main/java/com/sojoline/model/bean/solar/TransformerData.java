package com.sojoline.model.bean.solar;

import com.google.gson.annotations.SerializedName;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class TransformerData {

	/**
	 * ActPower : 471670303
	 * CompanyCode : 0030
	 * DPStationID : 0000000018
	 * DeviceRT : transformering
	 * DeviceState : fault
	 * F : 5141
	 * GateID : 1
	 * InputState3 : ,事故总信号,告警总信号
	 * InputState4 : ,油温高跳闸,油温高告警,油位低,一侧过压保护,一侧接地保护,一侧过流I段,二侧过压保护,二侧低压保护,二侧接地保护
	 * InputState5 : 0
	 * P1 : 5655
	 * ProtocolID : 006
	 * PutTime : 2018-01-21 18:38:43
	 * Q1 : 6169
	 * ReactPower : 539042339
	 * TransFormerID : 002010050062
	 * Version : 34439
	 * id : PLTtECQ3f8f9dGQwTrZpTefFMztG9kte
	 * illegalNum : 0
	 * intervals : 285529
	 * joinTime : 2018-01-21 18:38:43
	 * joinTimeStamp : 1516531123
	 * lastWorkState : fault
	 * remark :
	 */

	@SerializedName("ActPower")
	private float actPower;
	@SerializedName("CompanyCode")
	private String companyCode;
	@SerializedName("DPStationID")
	private String stationId;
	@SerializedName("DeviceRT")
	private String deviceRT;
	@SerializedName("DeviceState")
	private String deviceState;
	@SerializedName("F")
	private float f;
	@SerializedName("GateID")
	private String gateId;
	@SerializedName("InputState3")
	private String inputState3;
	@SerializedName("InputState4")
	private String inputState4;
	@SerializedName("InputState5")
	private String inputState5;
	@SerializedName("P1")
	private float p1;
	@SerializedName("ProtocolID")
	private String protocolId;
	@SerializedName("PutTime")
	private String putTime;
	@SerializedName("Q1")
	private float q1;
	@SerializedName("ReactPower")
	private float reactPower;
	@SerializedName("TransFormerID")
	private String transformerId;
	@SerializedName("Version")
	private String version;
	private String id;
	private int illegalNum;
	private int intervals;
	private String joinTime;
	private int joinTimeStamp;
	private String lastWorkState;
	private String remark;

	public float getActPower() {
		return actPower;
	}

	public void setActPower(float actPower) {
		this.actPower = actPower;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getDeviceRT() {
		return deviceRT;
	}

	public void setDeviceRT(String deviceRT) {
		this.deviceRT = deviceRT;
	}

	public String getDeviceState() {
		return deviceState;
	}

	public void setDeviceState(String deviceState) {
		this.deviceState = deviceState;
	}

	public float getF() {
		return f;
	}

	public void setF(float f) {
		this.f = f;
	}

	public String getGateId() {
		return gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	public String getInputState3() {
		return inputState3;
	}

	public void setInputState3(String inputState3) {
		this.inputState3 = inputState3;
	}

	public String getInputState4() {
		return inputState4;
	}

	public void setInputState4(String inputState4) {
		this.inputState4 = inputState4;
	}

	public String getInputState5() {
		return inputState5;
	}

	public void setInputState5(String inputState5) {
		this.inputState5 = inputState5;
	}

	public float getP1() {
		return p1;
	}

	public void setP1(float p1) {
		this.p1 = p1;
	}

	public String getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
	}

	public String getPutTime() {
		return putTime;
	}

	public void setPutTime(String putTime) {
		this.putTime = putTime;
	}

	public float getQ1() {
		return q1;
	}

	public void setQ1(float q1) {
		this.q1 = q1;
	}

	public float getReactPower() {
		return reactPower;
	}

	public void setReactPower(float reactPower) {
		this.reactPower = reactPower;
	}

	public String getTransformerId() {
		return transformerId;
	}

	public void setTransformerId(String transFormerId) {
		this.transformerId = transFormerId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIllegalNum() {
		return illegalNum;
	}

	public void setIllegalNum(int illegalNum) {
		this.illegalNum = illegalNum;
	}

	public int getIntervals() {
		return intervals;
	}

	public void setIntervals(int intervals) {
		this.intervals = intervals;
	}

	public String getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}

	public int getJoinTimeStamp() {
		return joinTimeStamp;
	}

	public void setJoinTimeStamp(int joinTimeStamp) {
		this.joinTimeStamp = joinTimeStamp;
	}

	public String getLastWorkState() {
		return lastWorkState;
	}

	public void setLastWorkState(String lastWorkState) {
		this.lastWorkState = lastWorkState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
