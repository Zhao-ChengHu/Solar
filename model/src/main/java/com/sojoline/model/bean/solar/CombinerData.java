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

public class CombinerData {

	/**
	 * ArcAlarmOutput : 1
	 * ArcAlarmState : 0
	 * BranchLowestCurrent : 515
	 * BranchReverseMiniNum : 17991
	 * BranchReverseThreshold : 0
	 * BusArcAlarmTime : 1
	 * ChannelAlarm : 1
	 * CombinerID : 002010040022
	 * CompanyCode : 0030
	 * DPStationID : 0000000018
	 * DeviceRT : combinering
	 * ExternalTemp : 206
	 * GateID : 1
	 * HighestLeakage : 1
	 * HighestVoltage : 515
	 * InputSwitchDI1 : 0
	 * InputSwitchDI2 : 0
	 * LeakageCurrent : 5141
	 * LowestCurrent : 1
	 * LowestVoltage : 1
	 * MaxExternalTemp : 100
	 * NoteBusVoltage : 0
	 * NoteChannelCount : 0
	 * NoteExTemp : 0
	 * NoteInputState : 0
	 * NoteWirelessDistance : 4627
	 * ProtocolID : 005
	 * PutTime : 2018-01-21 18:34:38
	 * TotalCurrent : 30850
	 * TotalInverseCurrent : 35990
	 * TotalPower : 1799100
	 * TotalPowerHigh : 1616994915
	 * TotalPowerLow : 0
	 * TotalReverseThreshold : 18505
	 * Voltage : 1
	 * WirelessConnCount : 0
	 * WirelessCount : 0
	 * id : GAUwbQge5C4GzYStFH96JdLdmmPJknmP
	 * illegalNum : 2
	 * intervals : 284709
	 * joinTime : 2018-01-21 18:34:38
	 * joinTimeStamp : 1516530878
	 * lastWorkstate : 0
	 * remark :
	 */
	@SerializedName("ArcAlarmOutput")
	private String arcAlarmOutput;
	@SerializedName("ArcAlarmState")
	private String arcAlarmState;
	@SerializedName("BranchLowestCurrent")
	private int branchLowestCurrent;
	@SerializedName("BranchReverseMiniNum")
	private int branchReverseMiniNum;
	@SerializedName("BranchReverseThreshold")
	private int branchReverseThreshold;
	@SerializedName("BusArcAlarmTime")
	private int busArcAlarmTime;
	@SerializedName("ChannelAlarm")
	private int channelAlarm;
	@SerializedName("CombinerID")
	private String combinerId;
	@SerializedName("CompanyCode")
	private String companyCode;
	@SerializedName("DPStationID")
	private String stationId;
	@SerializedName("DeviceRT")
	private String deviceRT;
	@SerializedName("ExternalTemp")
	private int externalTemp;
	@SerializedName("GateID")
	private String gateId;
	@SerializedName("HighestLeakage")
	private int highestLeakage;
	@SerializedName("HighestVoltage")
	private int highestVoltage;
	@SerializedName("InputSwitchDI1")
	private String inputSwitchDI1;
	@SerializedName("InputSwitchDI2")
	private String inputSwitchDI2;
	@SerializedName("LeakageCurrent")
	private int leakageCurrent;
	@SerializedName("LowestCurrent")
	private int lowestCurrent;
	@SerializedName("LowestVoltage")
	private int lowestVoltage;
	@SerializedName("MaxExternalTemp")
	private int maxExternalTemp;
	@SerializedName("NoteBusVoltage")
	private int noteBusVoltage;
	@SerializedName("NoteChannelCount")
	private int noteChannelCount;
	@SerializedName("NoteExTemp")
	private int noteExTemp;
	@SerializedName("NoteInputState")
	private String noteInputState;
	@SerializedName("NoteWirelessDistance")
	private int noteWirelessDistance;
	@SerializedName("ProtocolID")
	private String protocolId;
	@SerializedName("PutTime")
	private String putTime;
	@SerializedName("TotalCurrent")
	private int totalCurrent;
	@SerializedName("TotalInverseCurrent")
	private int totalInverseCurrent;
	@SerializedName("TotalPower")
	private int totalPower;
	@SerializedName("TotalPowerHigh")
	private int totalPowerHigh;
	@SerializedName("TotalPowerLow")
	private int totalPowerLow;
	@SerializedName("TotalReverseThreshold")
	private int totalReverseThreshold;
	@SerializedName("Voltage")
	private int voltage;
	@SerializedName("WirelessConnCount")
	private int wirelessConnCount;
	@SerializedName("WirelessCount")
	private int wirelessCount;
	private String id;
	private int illegalNum;
	private int intervals;
	private String joinTime;
	private int joinTimeStamp;
	private String lastWorkstate;
	private String remark;

	public String getArcAlarmOutput() {
		return arcAlarmOutput;
	}

	public void setArcAlarmOutput(String arcAlarmOutput) {
		this.arcAlarmOutput = arcAlarmOutput;
	}

	public String getArcAlarmState() {
		return arcAlarmState;
	}

	public void setArcAlarmState(String arcAlarmState) {
		this.arcAlarmState = arcAlarmState;
	}

	public int getBranchLowestCurrent() {
		return branchLowestCurrent;
	}

	public void setBranchLowestCurrent(int branchLowestCurrent) {
		this.branchLowestCurrent = branchLowestCurrent;
	}

	public int getBranchReverseMiniNum() {
		return branchReverseMiniNum;
	}

	public void setBranchReverseMiniNum(int branchReverseMiniNum) {
		this.branchReverseMiniNum = branchReverseMiniNum;
	}

	public int getBranchReverseThreshold() {
		return branchReverseThreshold;
	}

	public void setBranchReverseThreshold(int branchReverseThreshold) {
		this.branchReverseThreshold = branchReverseThreshold;
	}

	public int getBusArcAlarmTime() {
		return busArcAlarmTime;
	}

	public void setBusArcAlarmTime(int busArcAlarmTime) {
		this.busArcAlarmTime = busArcAlarmTime;
	}

	public int getChannelAlarm() {
		return channelAlarm;
	}

	public void setChannelAlarm(int channelAlarm) {
		this.channelAlarm = channelAlarm;
	}

	public String getCombinerId() {
		return combinerId;
	}

	public void setCombinerId(String combinerId) {
		this.combinerId = combinerId;
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

	public int getExternalTemp() {
		return externalTemp;
	}

	public void setExternalTemp(int externalTemp) {
		this.externalTemp = externalTemp;
	}

	public String getGateId() {
		return gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	public int getHighestLeakage() {
		return highestLeakage;
	}

	public void setHighestLeakage(int highestLeakage) {
		this.highestLeakage = highestLeakage;
	}

	public int getHighestVoltage() {
		return highestVoltage;
	}

	public void setHighestVoltage(int highestVoltage) {
		this.highestVoltage = highestVoltage;
	}

	public String getInputSwitchDI1() {
		return inputSwitchDI1;
	}

	public void setInputSwitchDI1(String inputSwitchDI1) {
		this.inputSwitchDI1 = inputSwitchDI1;
	}

	public String getInputSwitchDI2() {
		return inputSwitchDI2;
	}

	public void setInputSwitchDI2(String inputSwitchDI2) {
		this.inputSwitchDI2 = inputSwitchDI2;
	}

	public int getLeakageCurrent() {
		return leakageCurrent;
	}

	public void setLeakageCurrent(int leakageCurrent) {
		this.leakageCurrent = leakageCurrent;
	}

	public int getLowestCurrent() {
		return lowestCurrent;
	}

	public void setLowestCurrent(int lowestCurrent) {
		this.lowestCurrent = lowestCurrent;
	}

	public int getLowestVoltage() {
		return lowestVoltage;
	}

	public void setLowestVoltage(int lowestVoltage) {
		this.lowestVoltage = lowestVoltage;
	}

	public int getMaxExternalTemp() {
		return maxExternalTemp;
	}

	public void setMaxExternalTemp(int maxExternalTemp) {
		this.maxExternalTemp = maxExternalTemp;
	}

	public int getNoteBusVoltage() {
		return noteBusVoltage;
	}

	public void setNoteBusVoltage(int noteBusVoltage) {
		this.noteBusVoltage = noteBusVoltage;
	}

	public int getNoteChannelCount() {
		return noteChannelCount;
	}

	public void setNoteChannelCount(int noteChannelCount) {
		this.noteChannelCount = noteChannelCount;
	}

	public int getNoteExTemp() {
		return noteExTemp;
	}

	public void setNoteExTemp(int noteExTemp) {
		this.noteExTemp = noteExTemp;
	}

	public String getNoteInputState() {
		return noteInputState;
	}

	public void setNoteInputState(String noteInputState) {
		this.noteInputState = noteInputState;
	}

	public int getNoteWirelessDistance() {
		return noteWirelessDistance;
	}

	public void setNoteWirelessDistance(int noteWirelessDistance) {
		this.noteWirelessDistance = noteWirelessDistance;
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

	public int getTotalCurrent() {
		return totalCurrent;
	}

	public void setTotalCurrent(int totalCurrent) {
		this.totalCurrent = totalCurrent;
	}

	public int getTotalInverseCurrent() {
		return totalInverseCurrent;
	}

	public void setTotalInverseCurrent(int totalInverseCurrent) {
		this.totalInverseCurrent = totalInverseCurrent;
	}

	public int getTotalPower() {
		return totalPower;
	}

	public void setTotalPower(int totalPower) {
		this.totalPower = totalPower;
	}

	public int getTotalPowerHigh() {
		return totalPowerHigh;
	}

	public void setTotalPowerHigh(int totalPowerHigh) {
		this.totalPowerHigh = totalPowerHigh;
	}

	public int getTotalPowerLow() {
		return totalPowerLow;
	}

	public void setTotalPowerLow(int totalPowerLow) {
		this.totalPowerLow = totalPowerLow;
	}

	public int getTotalReverseThreshold() {
		return totalReverseThreshold;
	}

	public void setTotalReverseThreshold(int totalReverseThreshold) {
		this.totalReverseThreshold = totalReverseThreshold;
	}

	public int getVoltage() {
		return voltage;
	}

	public void setVoltage(int voltage) {
		this.voltage = voltage;
	}

	public int getWirelessConnCount() {
		return wirelessConnCount;
	}

	public void setWirelessConnCount(int wirelessConnCount) {
		this.wirelessConnCount = wirelessConnCount;
	}

	public int getWirelessCount() {
		return wirelessCount;
	}

	public void setWirelessCount(int wirelessCount) {
		this.wirelessCount = wirelessCount;
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

	public String getLastWorkstate() {
		return lastWorkstate;
	}

	public void setLastWorkstate(String lastWorkstate) {
		this.lastWorkstate = lastWorkstate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
