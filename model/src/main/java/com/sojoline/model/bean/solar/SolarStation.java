package com.sojoline.model.bean.solar;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarStation {

	/**
	 * Address : 怀柔
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
	 * currentPower : 0
	 * disable : false
	 * id : 76
	 * isFavorites : false
	 * joinTime : 2017-11-15 16:03:00
	 * pvTotalElectric : 0
	 * remark : 1234
	 * s_id :
	 * updateTime : 2017-12-07 10:28:59
	 * userCode :
	 */

	private String Address;
	private String CompanyCode;
	private String CompanyName;
	private String DPStationID;
	private String DPStationMark;
	private String DPStationName;
	private String DPStationType;
	private String Email;
	private String GateID;
	private String InstalledCapacity;
	private String Latitude;
	private String Longitude;
	private String TeleNum;
	private String b_actived;
	private String createTime;
	private double currentPower;
	private boolean disable;
	private int id;
	private boolean isFavorites;
	private String joinTime;
	private double pvTotalElectric;
	private String remark;
	private String s_id;
	private String updateTime;
	private String userCode;

	public String getAddress() {
		return Address;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}

	public String getCompanyCode() {
		return CompanyCode;
	}

	public void setCompanyCode(String CompanyCode) {
		this.CompanyCode = CompanyCode;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String CompanyName) {
		this.CompanyName = CompanyName;
	}

	public String getDPStationID() {
		return DPStationID;
	}

	public void setDPStationID(String DPStationID) {
		this.DPStationID = DPStationID;
	}

	public String getDPStationMark() {
		return DPStationMark;
	}

	public void setDPStationMark(String DPStationMark) {
		this.DPStationMark = DPStationMark;
	}

	public String getDPStationName() {
		return DPStationName;
	}

	public void setDPStationName(String DPStationName) {
		this.DPStationName = DPStationName;
	}

	public String getDPStationType() {
		return DPStationType;
	}

	public void setDPStationType(String DPStationType) {
		this.DPStationType = DPStationType;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}

	public String getGateID() {
		return GateID;
	}

	public void setGateID(String GateID) {
		this.GateID = GateID;
	}

	public String getInstalledCapacity() {
		return InstalledCapacity;
	}

	public void setInstalledCapacity(String installedCapacity) {
		InstalledCapacity = installedCapacity;
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public void setFavorites(boolean favorites) {
		isFavorites = favorites;
	}

	public String getTeleNum() {
		return TeleNum;
	}

	public void setTeleNum(String TeleNum) {
		this.TeleNum = TeleNum;
	}

	public String getB_actived() {
		return b_actived;
	}

	public void setB_actived(String b_actived) {
		this.b_actived = b_actived;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public double getCurrentPower() {
		return currentPower;
	}

	public void setCurrentPower(double currentPower) {
		this.currentPower = currentPower;
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

	public boolean isFavorites() {
		return isFavorites;
	}

	public void setIsFavorites(boolean isFavorites) {
		this.isFavorites = isFavorites;
	}

	public String getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}

	public double getPvTotalElectric() {
		return pvTotalElectric;
	}

	public void setPvTotalElectric(double pvTotalElectric) {
		this.pvTotalElectric = pvTotalElectric;
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
