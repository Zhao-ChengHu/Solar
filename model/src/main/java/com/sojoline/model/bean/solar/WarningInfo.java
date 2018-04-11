package com.sojoline.model.bean.solar;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class WarningInfo {

	/**
	 * AlarmID : 0024
	 * AlarmMark : Alarm
	 * AlarmName : PV过压
	 * CleanTime : 2018-01-21 06:13:00
	 * CompanyCode : 0030
	 * DPStationID : 0000000018
	 * DeviceID : 002010010005
	 * DeviceType : Inverter
	 * DeviceUnitID : 0
	 * FailureTime : 2018-01-21 12:55:40
	 * GateID : 1
	 * ProtocolID : 001
	 * createTime : 2018-01-21 18:13:42
	 * id : 797
	 * remark :
	 * updateTime : 2018-01-21 18:13:42
	 */

	private String AlarmID;
	private String AlarmMark;
	private String AlarmName;
	private String CleanTime;
	private String CompanyCode;
	private String DPStationID;
	private String DeviceID;
	private String DeviceType;
	private String DeviceUnitID;
	private String FailureTime;
	private String GateID;
	private String ProtocolID;
	private String createTime;
	private int id;
	private String remark;
	private String updateTime;

	public String getAlarmID() {
		return AlarmID;
	}

	public void setAlarmID(String AlarmID) {
		this.AlarmID = AlarmID;
	}

	public String getAlarmMark() {
		return AlarmMark;
	}

	public void setAlarmMark(String AlarmMark) {
		this.AlarmMark = AlarmMark;
	}

	public String getAlarmName() {
		return AlarmName;
	}

	public void setAlarmName(String AlarmName) {
		this.AlarmName = AlarmName;
	}

	public String getCleanTime() {
		return CleanTime;
	}

	public void setCleanTime(String CleanTime) {
		this.CleanTime = CleanTime;
	}

	public String getCompanyCode() {
		return CompanyCode;
	}

	public void setCompanyCode(String CompanyCode) {
		this.CompanyCode = CompanyCode;
	}

	public String getDPStationID() {
		return DPStationID;
	}

	public void setDPStationID(String DPStationID) {
		this.DPStationID = DPStationID;
	}

	public String getDeviceID() {
		return DeviceID;
	}

	public void setDeviceID(String DeviceID) {
		this.DeviceID = DeviceID;
	}

	public String getDeviceType() {
		return DeviceType;
	}

	public void setDeviceType(String DeviceType) {
		this.DeviceType = DeviceType;
	}

	public String getDeviceUnitID() {
		return DeviceUnitID;
	}

	public void setDeviceUnitID(String DeviceUnitID) {
		this.DeviceUnitID = DeviceUnitID;
	}

	public String getFailureTime() {
		return FailureTime;
	}

	public void setFailureTime(String FailureTime) {
		this.FailureTime = FailureTime;
	}

	public String getGateID() {
		return GateID;
	}

	public void setGateID(String GateID) {
		this.GateID = GateID;
	}

	public String getProtocolID() {
		return ProtocolID;
	}

	public void setProtocolID(String ProtocolID) {
		this.ProtocolID = ProtocolID;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
