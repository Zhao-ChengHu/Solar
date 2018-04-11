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

public class TransformerInfo implements Parcelable {

	/**
	 * Capacity : 0
	 * ProtocolID : 006
	 * Ratio : 0
	 * TransFormerID : 002010050062
	 */

	private int Capacity;
	private String ProtocolID;
	private String Ratio;
	private String TransFormerID;
	private String transformerName;

	public int getCapacity() {
		return Capacity;
	}

	public void setCapacity(int Capacity) {
		this.Capacity = Capacity;
	}

	public String getProtocolID() {
		return ProtocolID;
	}

	public void setProtocolID(String ProtocolID) {
		this.ProtocolID = ProtocolID;
	}

	public String getRatio() {
		return Ratio;
	}

	public void setRatio(String Ratio) {
		this.Ratio = Ratio;
	}

	public String getTransFormerID() {
		return TransFormerID;
	}

	public void setTransFormerID(String TransFormerID) {
		this.TransFormerID = TransFormerID;
	}

	public String getTransformerName() {
		return transformerName;
	}

	public void setTransformerName(String transformerName) {
		this.transformerName = transformerName;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.Capacity);
		dest.writeString(this.ProtocolID);
		dest.writeString(this.Ratio);
		dest.writeString(this.TransFormerID);
		dest.writeString(this.transformerName);
	}

	public TransformerInfo() {
	}

	protected TransformerInfo(Parcel in) {
		this.Capacity = in.readInt();
		this.ProtocolID = in.readString();
		this.Ratio = in.readString();
		this.TransFormerID = in.readString();
		this.transformerName = in.readString();
	}

	public static final Parcelable.Creator<TransformerInfo> CREATOR = new Parcelable.Creator<TransformerInfo>() {
		@Override
		public TransformerInfo createFromParcel(Parcel source) {
			return new TransformerInfo(source);
		}

		@Override
		public TransformerInfo[] newArray(int size) {
			return new TransformerInfo[size];
		}
	};
}
