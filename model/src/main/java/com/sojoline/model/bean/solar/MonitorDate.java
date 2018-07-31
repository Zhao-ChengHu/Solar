package com.sojoline.model.bean.solar;

import com.google.gson.annotations.SerializedName;

public class MonitorDate {
    /**
     * WindSpeedInstant : 3
     * NetRadiationInstant : 65098
     * PhoRadiationInstant : 17
     * CompanyCode : 0030
     * DayUVRadiation : 32738
     * lastWorkstate : 1
     * DPStationID : 0000000018
     * DayNetRadiation : 16675
     * GateID : 1
     * intervals : 66
     * id : vJT5LtVK58NdqxnnbhiCKY8QB94yz5MG
     * PutTime : 2018-07-25 09:48:39
     * AirPressure : 9987
     * DayDirectRadiation : 65535
     * ProtocolID : 008
     * joinTimeStamp : 1532483319
     * illegalNum : 0
     * TotalRadiation2Instant : 0
     * DayPhoRadiation : 25825
     * TotalRadiation1Instant : 131
     * EnvDetectorID : 002010070060
     * DewPoint : 2577
     * DayScatteredRadiation : 65535
     * DayRainfall : 0
     * UVRadiationInstant : 335
     * AmbTemperature : 286
     * WindInstant : 354
     * remark :
     * CO2 : 0
     * DeviceRT : envdetectoring
     * DayTotalRadiation1 : 3131
     * DirectRadiationInstant : 1998
     * DaySunshine : 558
     * DayTotalRadiation2 : 26029
     * Evaporation : 0
     * EnvHumidity : 847
     * SoilMoisture1 : 0
     * joinTime : 2018-07-25 09:48:39
     */
    @SerializedName("WindSpeedInstant")
    private float WindSpeedInstant;

    @SerializedName("NetRadiationInstant")
    private int NetRadiationInstant;

    @SerializedName("PhoRadiationInstant")
    private int PhoRadiationInstant;

    @SerializedName("CompanyCode")
    private String CompanyCode;

    @SerializedName("DayUVRadiation")
    private int DayUVRadiation;

    @SerializedName("DPStationID")
    private String DPStationID;

    @SerializedName("DayNetRadiation")
    private int DayNetRadiation;

    @SerializedName("GateID")
    private String GateID;

    @SerializedName("PutTime")
    private String PutTime;

    @SerializedName("AirPressure")
    private int AirPressure;

    @SerializedName("DayDirectRadiation")
    private int DayDirectRadiation;

    @SerializedName("ProtocolID")
    private String ProtocolID;

    @SerializedName("TotalRadiation2Instant")
    private int TotalRadiation2Instant;

    @SerializedName("DayPhoRadiation")
    private int DayPhoRadiation;

    @SerializedName("TotalRadiation1Instant")
    private int TotalRadiation1Instant;

    @SerializedName("EnvDetectorID")
    private String EnvDetectorID;

    @SerializedName("DewPoint")
    private int DewPoint;

    @SerializedName("DayScatteredRadiation")
    private float DayScatteredRadiation;

    @SerializedName("DayRainfall")
    private int DayRainfall;

    @SerializedName("UVRadiationInstant")
    private int UVRadiationInstant;

    @SerializedName("AmbTemperature")
    private float AmbTemperature;

    @SerializedName("WindInstant")
    private float WindInstant;

    @SerializedName("CO2")
    private int CO2;

    @SerializedName("DeviceRT")
    private String DeviceRT;

    @SerializedName("DayTotalRadiation1")
    private float DayTotalRadiation1;

    @SerializedName("DirectRadiationInstant")
    private float DirectRadiationInstant;

    @SerializedName("DaySunshine")
    private float DaySunshine;

    @SerializedName("DayTotalRadiation2")
    private float DayTotalRadiation2;

    @SerializedName("Evaporation")
    private int Evaporation;

    @SerializedName("EnvHumidity")
    private float EnvHumidity;

    @SerializedName("SoilMoisture1")
    private int SoilMoisture1;

    private String id;
    private int illegalNum;
    private int intervals;
    private String joinTime;
    private int joinTimeStamp;
    private String lastWorkstate;
    private String remark;

    public float getWindSpeedInstant() {
        return WindSpeedInstant;
    }

    public void setWindSpeedInstant(float WindSpeedInstant) {
        this.WindSpeedInstant = WindSpeedInstant;
    }

    public int getNetRadiationInstant() {
        return NetRadiationInstant;
    }

    public void setNetRadiationInstant(int NetRadiationInstant) {
        this.NetRadiationInstant = NetRadiationInstant;
    }

    public int getPhoRadiationInstant() {
        return PhoRadiationInstant;
    }

    public void setPhoRadiationInstant(int PhoRadiationInstant) {
        this.PhoRadiationInstant = PhoRadiationInstant;
    }

    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String CompanyCode) {
        this.CompanyCode = CompanyCode;
    }

    public int getDayUVRadiation() {
        return DayUVRadiation;
    }

    public void setDayUVRadiation(int DayUVRadiation) {
        this.DayUVRadiation = DayUVRadiation;
    }

    public String getLastWorkstate() {
        return lastWorkstate;
    }

    public void setLastWorkstate(String lastWorkstate) {
        this.lastWorkstate = lastWorkstate;
    }

    public String getDPStationID() {
        return DPStationID;
    }

    public void setDPStationID(String DPStationID) {
        this.DPStationID = DPStationID;
    }

    public int getDayNetRadiation() {
        return DayNetRadiation;
    }

    public void setDayNetRadiation(int DayNetRadiation) {
        this.DayNetRadiation = DayNetRadiation;
    }

    public String getGateID() {
        return GateID;
    }

    public void setGateID(String GateID) {
        this.GateID = GateID;
    }

    public int getIntervals() {
        return intervals;
    }

    public void setIntervals(int intervals) {
        this.intervals = intervals;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPutTime() {
        return PutTime;
    }

    public void setPutTime(String PutTime) {
        this.PutTime = PutTime;
    }

    public int getAirPressure() {
        return AirPressure;
    }

    public void setAirPressure(int AirPressure) {
        this.AirPressure = AirPressure;
    }

    public int getDayDirectRadiation() {
        return DayDirectRadiation;
    }

    public void setDayDirectRadiation(int DayDirectRadiation) {
        this.DayDirectRadiation = DayDirectRadiation;
    }

    public String getProtocolID() {
        return ProtocolID;
    }

    public void setProtocolID(String ProtocolID) {
        this.ProtocolID = ProtocolID;
    }

    public int getJoinTimeStamp() {
        return joinTimeStamp;
    }

    public void setJoinTimeStamp(int joinTimeStamp) {
        this.joinTimeStamp = joinTimeStamp;
    }

    public int getIllegalNum() {
        return illegalNum;
    }

    public void setIllegalNum(int illegalNum) {
        this.illegalNum = illegalNum;
    }

    public int getTotalRadiation2Instant() {
        return TotalRadiation2Instant;
    }

    public void setTotalRadiation2Instant(int TotalRadiation2Instant) {
        this.TotalRadiation2Instant = TotalRadiation2Instant;
    }

    public int getDayPhoRadiation() {
        return DayPhoRadiation;
    }

    public void setDayPhoRadiation(int DayPhoRadiation) {
        this.DayPhoRadiation = DayPhoRadiation;
    }

    public int getTotalRadiation1Instant() {
        return TotalRadiation1Instant;
    }

    public void setTotalRadiation1Instant(int TotalRadiation1Instant) {
        this.TotalRadiation1Instant = TotalRadiation1Instant;
    }

    public String getEnvDetectorID() {
        return EnvDetectorID;
    }

    public void setEnvDetectorID(String EnvDetectorID) {
        this.EnvDetectorID = EnvDetectorID;
    }

    public int getDewPoint() {
        return DewPoint;
    }

    public void setDewPoint(int DewPoint) {
        this.DewPoint = DewPoint;
    }

    public float getDayScatteredRadiation() {
        return DayScatteredRadiation;
    }

    public void setDayScatteredRadiation(float DayScatteredRadiation) {
        this.DayScatteredRadiation = DayScatteredRadiation;
    }

    public int getDayRainfall() {
        return DayRainfall;
    }

    public void setDayRainfall(int DayRainfall) {
        this.DayRainfall = DayRainfall;
    }

    public int getUVRadiationInstant() {
        return UVRadiationInstant;
    }

    public void setUVRadiationInstant(int UVRadiationInstant) {
        this.UVRadiationInstant = UVRadiationInstant;
    }

    public float getAmbTemperature() {
        return AmbTemperature;
    }

    public void setAmbTemperature(float AmbTemperature) {
        this.AmbTemperature = AmbTemperature;
    }

    public float getWindInstant() {
        return WindInstant;
    }

    public void setWindInstant(float WindInstant) {
        this.WindInstant = WindInstant;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCO2() {
        return CO2;
    }

    public void setCO2(int CO2) {
        this.CO2 = CO2;
    }

    public String getDeviceRT() {
        return DeviceRT;
    }

    public void setDeviceRT(String DeviceRT) {
        this.DeviceRT = DeviceRT;
    }

    public float getDayTotalRadiation1() {
        return DayTotalRadiation1;
    }

    public void setDayTotalRadiation1(float DayTotalRadiation1) {
        this.DayTotalRadiation1 = DayTotalRadiation1;
    }

    public float getDirectRadiationInstant() {
        return DirectRadiationInstant;
    }

    public void setDirectRadiationInstant(float DirectRadiationInstant) {
        this.DirectRadiationInstant = DirectRadiationInstant;
    }

    public float getDaySunshine() {
        return DaySunshine;
    }

    public void setDaySunshine(float DaySunshine) {
        this.DaySunshine = DaySunshine;
    }

    public float getDayTotalRadiation2() {
        return DayTotalRadiation2;
    }

    public void setDayTotalRadiation2(float DayTotalRadiation2) {
        this.DayTotalRadiation2 = DayTotalRadiation2;
    }

    public int getEvaporation() {
        return Evaporation;
    }

    public void setEvaporation(int Evaporation) {
        this.Evaporation = Evaporation;
    }

    public float getEnvHumidity() {
        return EnvHumidity;
    }

    public void setEnvHumidity(float EnvHumidity) {
        this.EnvHumidity = EnvHumidity;
    }

    public int getSoilMoisture1() {
        return SoilMoisture1;
    }

    public void setSoilMoisture1(int SoilMoisture1) {
        this.SoilMoisture1 = SoilMoisture1;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }
    /**
     * 编号：EnvDetectorID
     采集时间
     运行状态
     环境温度 AmbTemperature AmbTemperature
     湿度、EnvHumidity  EnvHumidity
     风速、WindSpeedInstant
     风向、WindInstant
     总辐照量、DayTotalRadiation
     直射辐照量、DirectRadiationInstant
     散射辐照量、DayScatteredRadiation
     日累计辐照量 DayPhoRadiation
     "WindSpeedInstant": 3,
     "joinTimeStamp": 1532483319,
     "DayPhoRadiation": 25825,
     "EnvDetectorID": "002010070060",
     "DayScatteredRadiation": 65535,
     "AmbTemperature": 286,
     "WindInstant": 354,
     "DirectRadiationInstant": 1998,
     "EnvHumidity": 847,
     "joinTime": "2018-07-25 09:48:39"
     */

}
