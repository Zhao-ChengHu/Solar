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

public class CombinerInfo implements Parcelable {

	/**
	 * CombinerID : 002010040044
	 * CombinerType : Combiner4
	 * MaxDCInputV : 0
	 * MaxInputNum : 0
	 */

	private String CombinerID;
	private String CombinerType;
	private int MaxDCInputV;
	private int MaxInputNum;
	private String combinerName;

	public String getCombinerID() {
		return CombinerID;
	}

	public void setCombinerID(String CombinerID) {
		this.CombinerID = CombinerID;
	}

	public String getCombinerType() {
		return CombinerType;
	}

	public void setCombinerType(String CombinerType) {
		this.CombinerType = CombinerType;
	}

	public int getMaxDCInputV() {
		return MaxDCInputV;
	}

	public void setMaxDCInputV(int MaxDCInputV) {
		this.MaxDCInputV = MaxDCInputV;
	}

	public int getMaxInputNum() {
		return MaxInputNum;
	}

	public void setMaxInputNum(int MaxInputNum) {
		this.MaxInputNum = MaxInputNum;
	}

	public String getCombinerName() {
		return combinerName;
	}

	public void setCombinerName(String combinerName) {
		this.combinerName = combinerName;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.CombinerID);
		dest.writeString(this.CombinerType);
		dest.writeInt(this.MaxDCInputV);
		dest.writeInt(this.MaxInputNum);
		dest.writeString(this.combinerName);
	}

	public CombinerInfo() {
	}

	protected CombinerInfo(Parcel in) {
		this.CombinerID = in.readString();
		this.CombinerType = in.readString();
		this.MaxDCInputV = in.readInt();
		this.MaxInputNum = in.readInt();
		this.combinerName = in.readString();
	}

	public static final Parcelable.Creator<CombinerInfo> CREATOR = new Parcelable.Creator<CombinerInfo>() {
		@Override
		public CombinerInfo createFromParcel(Parcel source) {
			return new CombinerInfo(source);
		}

		@Override
		public CombinerInfo[] newArray(int size) {
			return new CombinerInfo[size];
		}
	};
}
