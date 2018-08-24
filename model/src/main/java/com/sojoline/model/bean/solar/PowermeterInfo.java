package com.sojoline.model.bean.solar;

import android.os.Parcel;
import android.os.Parcelable;

public class PowermeterInfo implements Parcelable {
    /*
    "ElectricPowerMeterID": "004010120102",
"ProtocolID": "012",
"powerMeterName": "1-2#"
    */

    private String ElectricPowerMeterID;
    private String ProtocolID;
    private String powerMeterName;


    public String getElectricPowerMeterID() {
        return ElectricPowerMeterID;
    }

    public void setElectricPowerMeterID(String electricPowerMeterID) {
        ElectricPowerMeterID = electricPowerMeterID;
    }

    public String getProtocolID() {
        return ProtocolID;
    }

    public void setProtocolID(String protocolID) {
        ProtocolID = protocolID;
    }

    public String getPowerMeterName() {
        return powerMeterName;
    }

    public void setPowerMeterName(String powerMeterName) {
        this.powerMeterName = powerMeterName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ProtocolID);
        dest.writeString(this.ElectricPowerMeterID);
        dest.writeString(this.powerMeterName);
    }

    public PowermeterInfo() {
    }

    protected PowermeterInfo(Parcel in) {
        this.ProtocolID = in.readString();
        this.ElectricPowerMeterID = in.readString();
        this.powerMeterName = in.readString();
    }

    public static final Parcelable.Creator<PowermeterInfo> CREATOR = new Parcelable.Creator<PowermeterInfo>() {
        @Override
        public PowermeterInfo createFromParcel(Parcel source) {
            return new PowermeterInfo(source);
        }

        @Override
        public PowermeterInfo[] newArray(int size) {
            return new PowermeterInfo[size];
        }
    };
}
