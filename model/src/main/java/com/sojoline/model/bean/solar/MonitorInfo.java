package com.sojoline.model.bean.solar;

import android.os.Parcel;
import android.os.Parcelable;

public class MonitorInfo implements Parcelable {
    /*
    EnvDetectorID
    EnvDetectorName
    ProtocolID
    */
    private String EnvDetectorID;
    private String EnvDetectorName;
    private String ProtocolID;



    public String getEnvDetectorID() {
        return EnvDetectorID;
    }

    public void setEnvDetectorID(String envDetectorID) {
        EnvDetectorID = envDetectorID;
    }

    public String getEnvDetectorName() {
        return EnvDetectorName;
    }

    public void setEnvDetectorName(String envDetectorName) {
        EnvDetectorName = envDetectorName;
    }

    public String getProtocolID() {
        return ProtocolID;
    }

    public void setProtocolID(String protocolID) {
        ProtocolID = protocolID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.EnvDetectorID);
        dest.writeString(this.EnvDetectorName);
        dest.writeString(this.ProtocolID);
    }

    public MonitorInfo() {
    }

    protected MonitorInfo(Parcel in) {
        this.EnvDetectorID = in.readString();
        this.EnvDetectorName = in.readString();
        this.ProtocolID = in.readString();
    }

    public static final Parcelable.Creator<MonitorInfo> CREATOR = new Parcelable.Creator<MonitorInfo>() {
        @Override
        public MonitorInfo createFromParcel(Parcel source) {
            return new MonitorInfo(source);
        }

        @Override
        public MonitorInfo[] newArray(int size) {
            return new MonitorInfo[size];
        }
    };
}
