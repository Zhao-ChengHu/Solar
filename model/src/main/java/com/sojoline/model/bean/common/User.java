package com.sojoline.model.bean.common;

import com.google.gson.annotations.SerializedName;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class User {

	/**
	 * updateTime : 2017-11-27 13:12:49
	 * TeleNum :
	 * header :
	 * orgBy :
	 * id : 114
	 * password : 0f9efc63af9e8f7b19f1c2ff6c4d57ab1b062f03c9dc5bed16c4e17d25c6a415786a5095
	 * createBy :
	 * lastLoginIp : 3232251116
	 * Email :
	 * Address :
	 * lastLoginTime : 2017-11-27 11:21:25
	 * username : 15611519891
	 * thisLoginTime : 2017-11-27 13:48:53
	 * mobileNum :
	 * disable : false
	 * b_actived :
	 * thisLoginIp : 3232251116
	 * nickname :
	 * createTime : 2017-07-21 10:18:18
	 * lastPassword :
	 * remark :
	 * userCode : L8DNUY4M8iqpKN5QFYKeXV
	 * userType : consumer
	 * gender :
	 */

	private String updateTime;
	@SerializedName("TeleNum")
	private String telephone;
	private String header;
	private int id;
	private long lastLoginIp;
	@SerializedName("Email")
	private String email;
	@SerializedName("Address")
	private String address;
	private String lastLoginTime;
	private String username;
	private String thisLoginTime;
	private String mobileNum;
	private long thisLoginIp;
	private String nickname;
	private String userType;
	private String gender;

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(long lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getThisLoginTime() {
		return thisLoginTime;
	}

	public void setThisLoginTime(String thisLoginTime) {
		this.thisLoginTime = thisLoginTime;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public long getThisLoginIp() {
		return thisLoginIp;
	}

	public void setThisLoginIp(long thisLoginIp) {
		this.thisLoginIp = thisLoginIp;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
