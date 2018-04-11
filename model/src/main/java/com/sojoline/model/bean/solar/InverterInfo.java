package com.sojoline.model.bean.solar;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class InverterInfo implements Parcelable {

	/**
	 * ControlMode : REMOTE
	 * InverterID : 00010000
	 * RunMode : STANDBY
	 * WorkMode : LOCK
	 */

	private String ControlMode;
	private String InverterID;
	private String RunMode;
	private String WorkMode;
	private String inverterName;

	public String getControlMode() {
		return ControlMode;
	}

	public void setControlMode(String ControlMode) {
		this.ControlMode = ControlMode;
	}

	public String getInverterID() {
		return InverterID;
	}

	public void setInverterID(String InverterID) {
		this.InverterID = InverterID;
	}

	public String getRunMode() {
		return RunMode;
	}

	public void setRunMode(String RunMode) {
		this.RunMode = RunMode;
	}

	public String getWorkMode() {
		return WorkMode;
	}

	public void setWorkMode(String WorkMode) {
		this.WorkMode = WorkMode;
	}

	public String getInverterName() {
		return inverterName;
	}

	public void setInverterName(String inverterName) {
		this.inverterName = inverterName;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.ControlMode);
		dest.writeString(this.InverterID);
		dest.writeString(this.RunMode);
		dest.writeString(this.WorkMode);
		dest.writeString(this.inverterName);
	}

	public InverterInfo() {
	}

	protected InverterInfo(Parcel in) {
		this.ControlMode = in.readString();
		this.InverterID = in.readString();
		this.RunMode = in.readString();
		this.WorkMode = in.readString();
		this.inverterName = in.readString();
	}

	public static final Parcelable.Creator<InverterInfo> CREATOR = new Parcelable.Creator<InverterInfo>() {
		@Override
		public InverterInfo createFromParcel(Parcel source) {
			return new InverterInfo(source);
		}

		@Override
		public InverterInfo[] newArray(int size) {
			return new InverterInfo[size];
		}
	};
}
