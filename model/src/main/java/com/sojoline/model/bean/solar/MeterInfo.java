package com.sojoline.model.bean.solar;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * <pre>
 *     @author : zhaochenghu
 *     date   : 2018/8/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MeterInfo implements Parcelable {
//           "ProtocolID": "007",
//          "MeterID": "004010060012",
//          "meterName": "2-6\u7535\u8868"

    private String MeterID;
    private String ProtocolID;
    private String meterName;


    public String getElectricMeterID() {
        return MeterID;
    }

    public void setElectricMeterID(String electricMeterID) {
        MeterID = electricMeterID;
    }

    public String getProtocolID() {
        return ProtocolID;
    }

    public void setProtocolID(String protocolID) {
        ProtocolID = protocolID;
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String powerMeterName) {
        this.meterName = powerMeterName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ProtocolID);
        dest.writeString(this.MeterID);
        dest.writeString(this.meterName);
    }

    public MeterInfo() {
    }

    protected MeterInfo(Parcel in) {
        this.ProtocolID = in.readString();
        this.MeterID = in.readString();
        this.meterName = in.readString();
    }

    public static final Creator<MeterInfo> CREATOR = new Creator<MeterInfo>() {
        @Override
        public MeterInfo createFromParcel(Parcel source) {
            return new MeterInfo(source);
        }

        @Override
        public MeterInfo[] newArray(int size) {
            return new MeterInfo[size];
        }
    };
}
