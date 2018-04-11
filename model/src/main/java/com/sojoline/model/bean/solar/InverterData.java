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

public class InverterData {

	/**
	 * ActivePower : 6683
	 * ApparentPower : 7711
	 * ArrayCurrent : 515
	 * ArrayPower : 1029
	 * ArrayVoltage : 1
	 * CO2Emission : 1212762699
	 * CompanyCode : 0030
	 * ControlMode : remote
	 * CurrentA : 3085
	 * CurrentB : 3599
	 * CurrentC : 4113
	 * DPStationID : 0000000018
	 * DayRunTime : 82250
	 * DeviceRT : invertering
	 * Frequency : 6169
	 * GateID : 1
	 * InverterID : 002010010006
	 * InverterUnitID : 000000000000
	 * PowFactorA : 4627
	 * PowFactorB : 5141
	 * PowFactorC : 5655
	 * ProtocolID : 001
	 * PutTime : 2018-01-21 18:16:50
	 * ReactivePower : 7197
	 * RunMode : stop
	 * TotalElectric : 1145390663
	 * VoltageAB : 1543
	 * VoltageBC : 2057
	 * VoltageCA : 0
	 * WorkMode : nolock
	 * id : dYQaxJS3e8NkxBnGhc7ABZYfYKCigJKh
	 * illegalNum : 2
	 * intervals : 4
	 * joinTime : 2018-01-21 18:16:50
	 * joinTimeStamp : 1516529810
	 * lastWorkstate : stop
	 * remark :
	 */
	@SerializedName("ActivePower")
	private float activePower;
	@SerializedName("ApparentPower")
	private float apparentPower;
	@SerializedName("ArrayCurrent")
	private float  arrayCurrent;
	@SerializedName("ArrayPower")
	private float  arrayPower;
	@SerializedName("ArrayVoltage")
	private float  arrayVoltage;
	@SerializedName("CO2Emission")
	private float  CO2Emission;
	@SerializedName("CompanyCode")
	private String companyCode;
	@SerializedName("ControlMode")
	private String controlMode;
	@SerializedName("CurrentA")
	private float  currentA;
	@SerializedName("CurrentB")
	private float  currentB;
	@SerializedName("CurrentC")
	private float  currentC;
	@SerializedName("DPStationID")
	private String stationId;
	@SerializedName("DayRunTime")
	private int dayRunTime;
	@SerializedName("DeviceRT")
	private String deviceRT;
	@SerializedName("Frequency")
	private float  frequency;
	@SerializedName("GateID")
	private String gateId;
	@SerializedName("InverterID")
	private String inverterId;
	@SerializedName("InverterUnitID")
	private String inverterUnitId;
	@SerializedName("PowFactorA")
	private float  powFactorA;
	@SerializedName("PowFactorB")
	private float  powFactorB;
	@SerializedName("PowFactorC")
	private float  powFactorC;
	@SerializedName("ProtocolID")
	private String protocolId;
	@SerializedName("PutTime")
	private String putTime;
	@SerializedName("ReactivePower")
	private float  reactivePower;
	@SerializedName("RunMode")
	private String runMode;
	@SerializedName("TotalElectric")
	private float  totalElectric;
	@SerializedName("VoltageAB")
	private float  voltageAB;
	@SerializedName("VoltageBC")
	private float  voltageBC;
	@SerializedName("VoltageCA")
	private float  voltageCA;
	@SerializedName("WorkMode")
	private String workMode;
	private String id;
	private int illegalNum;
	private int intervals;
	private String joinTime;
	private double  joinTimeStamp;
	@SerializedName("lastWorkstate")
	private String lastWorkState;
	private String remark;

	public String getRunMode() {
		if ("grid".equals(runMode)){
			return "并网";
		}else {
			return "离网";
		}
	}

	public void setRunMode(String runMode) {
		this.runMode = runMode;
	}

	public float getActivePower() {
		return activePower;
	}

	public void setActivePower(float activePower) {
		this.activePower = activePower;
	}

	public float getApparentPower() {
		return apparentPower;
	}

	public void setApparentPower(float apparentPower) {
		this.apparentPower = apparentPower;
	}

	public float getArrayCurrent() {
		return arrayCurrent;
	}

	public void setArrayCurrent(float arrayCurrent) {
		this.arrayCurrent = arrayCurrent;
	}

	public float getArrayPower() {
		return arrayPower;
	}

	public void setArrayPower(float arrayPower) {
		this.arrayPower = arrayPower;
	}

	public float getArrayVoltage() {
		return arrayVoltage;
	}

	public void setArrayVoltage(float arrayVoltage) {
		this.arrayVoltage = arrayVoltage;
	}

	public float getCO2Emission() {
		return CO2Emission;
	}

	public void setCO2Emission(float CO2Emission) {
		this.CO2Emission = CO2Emission;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getControlMode() {
		return controlMode;
	}

	public void setControlMode(String controlMode) {
		this.controlMode = controlMode;
	}

	public float getCurrentA() {
		return currentA;
	}

	public void setCurrentA(float currentA) {
		this.currentA = currentA;
	}

	public float getCurrentB() {
		return currentB;
	}

	public void setCurrentB(float currentB) {
		this.currentB = currentB;
	}

	public float getCurrentC() {
		return currentC;
	}

	public void setCurrentC(float currentC) {
		this.currentC = currentC;
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

	public float getFrequency() {
		return frequency;
	}

	public void setFrequency(float frequency) {
		this.frequency = frequency;
	}

	public String getGateId() {
		return gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	public String getInverterId() {
		return inverterId;
	}

	public void setInverterId(String inverterId) {
		this.inverterId = inverterId;
	}

	public String getInverterUnitId() {
		return inverterUnitId;
	}

	public void setInverterUnitId(String inverterUnitId) {
		this.inverterUnitId = inverterUnitId;
	}

	public float getPowFactorA() {
		return powFactorA;
	}

	public void setPowFactorA(float powFactorA) {
		this.powFactorA = powFactorA;
	}

	public float getPowFactorB() {
		return powFactorB;
	}

	public void setPowFactorB(float powFactorB) {
		this.powFactorB = powFactorB;
	}

	public float getPowFactorC() {
		return powFactorC;
	}

	public void setPowFactorC(float powFactorC) {
		this.powFactorC = powFactorC;
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

	public float getReactivePower() {
		return reactivePower;
	}

	public void setReactivePower(float reactivePower) {
		this.reactivePower = reactivePower;
	}

	public float getVoltageAB() {
		return voltageAB;
	}

	public void setVoltageAB(float voltageAB) {
		this.voltageAB = voltageAB;
	}

	public float getVoltageBC() {
		return voltageBC;
	}

	public void setVoltageBC(float voltageBC) {
		this.voltageBC = voltageBC;
	}

	public float getVoltageCA() {
		return voltageCA;
	}

	public void setVoltageCA(float voltageCA) {
		this.voltageCA = voltageCA;
	}

	public String getWorkMode() {
		return workMode;
	}

	public void setWorkMode(String workMode) {
		this.workMode = workMode;
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

	public double getJoinTimeStamp() {
		return joinTimeStamp;
	}

	public void setJoinTimeStamp(double joinTimeStamp) {
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

	public int getDayRunTime() {
		return dayRunTime;
	}

	public void setDayRunTime(int dayRunTime) {
		this.dayRunTime = dayRunTime;
	}

	public float getTotalElectric() {
		return totalElectric;
	}

	public void setTotalElectric(float totalElectric) {
		this.totalElectric = totalElectric;
	}
}
