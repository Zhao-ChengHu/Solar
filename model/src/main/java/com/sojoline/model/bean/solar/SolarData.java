package com.sojoline.model.bean.solar;

import com.google.gson.annotations.SerializedName;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarData {

	/**
	 * Address : 怀柔
	 * CO2TotalEmission : 0
	 * CompanyCode : 0001
	 * CompanyName : 北京双杰电气股份有限公司
	 * DPStationID : 0000000020
	 * DPStationMark : sub
	 * DPStationName : 北京双杰电气
	 * DPStationType : pvs
	 * Email : 123456789@163.com
	 * GateID :
	 * InstalledCapacity : 3600
	 * Latitude : 39.908
	 * Longitude : 116.4023
	 * TeleNum : 12345678910
	 * b_actived : 1
	 * createTime : 2017-11-15 19:47:58
	 * currentElectric : 0
	 * currentPower : 0
	 * disable : false
	 * id : 76
	 * joinTime : 2017-11-15 16:03:00
	 * pvTotalElectric : 0
	 * pvTotalIncome : 0
	 * remark : 1234
	 * s_id :
	 * treePlant : 0
	 * updateTime : 2018-01-10 11:37:32
	 * userCode : L8DNUY4M8iqpKN5QFYKeXV
	 */
	@SerializedName("Address")
	private String address;
	//累计减排
	private double CO2TotalEmission;
	@SerializedName("CompanyCode")
	private String companyCode;
	@SerializedName("CompanyName")
	private String companyName;
	@SerializedName("DPStationID")
	private String stationId;
	@SerializedName("DPStationMark")
	private String stationMark;
	@SerializedName("DPStationName")
	private String stationName;
	@SerializedName("DPStationType")
	private String stationType;
	@SerializedName("Email")
	private String email;
	@SerializedName("GateID")
	private String gateId;
	@SerializedName("InstalledCapacity")
	private String installedCapacity;
	@SerializedName("Latitude")
	private String latitude;
	@SerializedName("Longitude")
	private String longitude;
	@SerializedName("TeleNum")
	private String telephone;
	private String currentIncome;
	@SerializedName("b_actived")
	private String actived;
	private String createTime;
	private int electricityPower;
	//当前发电量
	private String currentElectric;
	//当前功率
	private String currentPower;
	private boolean disable;
	private int id;
	private String joinTime;
	//累计发电量
	private String pvTotalElectric;
	//累计收益
	private String pvTotalIncome;
	private String remark;
	private String s_id;
	//累计种植
	private int treePlant;
	private String updateTime;
	private String userCode;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getCO2TotalEmission() {
		return CO2TotalEmission;
	}

	public void setCO2TotalEmission(double CO2TotalEmission) {
		this.CO2TotalEmission = CO2TotalEmission;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationMark() {
		return stationMark;
	}

	public void setStationMark(String stationMark) {
		this.stationMark = stationMark;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationType() {
		return stationType;
	}

	public void setStationType(String stationType) {
		this.stationType = stationType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGateId() {
		return gateId;
	}

	public String getCurrentIncome() {
		return currentIncome;
	}

	public void setCurrentIncome(String currentIncome) {
		this.currentIncome = currentIncome;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	public String getInstalledCapacity() {
		return installedCapacity;
	}

	public void setInstalledCapacity(String installedCapacity) {
		this.installedCapacity = installedCapacity;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getActived() {
		return actived;
	}

	public void setActived(String actived) {
		this.actived = actived;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setElectricityPower(int electricityPower) {
		this.electricityPower = electricityPower;
	}
	public int getElectricityPower(){
		return electricityPower;
	}
	public String getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}

	public String getCurrentElectric() {
		return currentElectric;
	}

	public void setCurrentElectric(String currentElectric) {
		this.currentElectric = currentElectric;
	}

	public String getCurrentPower() {
		return currentPower;
	}

	public void setCurrentPower(String currentPower) {
		this.currentPower = currentPower;
	}

	public String getPvTotalElectric() {
		return pvTotalElectric;
	}

	public void setPvTotalElectric(String pvTotalElectric) {
		this.pvTotalElectric = pvTotalElectric;
	}

	public String getPvTotalIncome() {
		return pvTotalIncome;
	}

	public void setPvTotalIncome(String pvTotalIncome) {
		this.pvTotalIncome = pvTotalIncome;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getS_id() {
		return s_id;
	}

	public void setS_id(String s_id) {
		this.s_id = s_id;
	}

	public int getTreePlant() {
		return treePlant;
	}

	public void setTreePlant(int treePlant) {
		this.treePlant = treePlant;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
