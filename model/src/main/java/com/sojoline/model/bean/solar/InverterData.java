package com.sojoline.model.bean.solar;

import com.google.gson.annotations.SerializedName;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class InverterData {

	/**
	 * ActivePower : 6683
	 * ApparentPower : 7711
	 * ArrayCurrent : 515
	 * ArrayPower : 1029
	 * ArrayVoltage : 1
	 * CO2Emission : 1212762699
	 * CompanyCode : 0030
	 * ControlMode : remote
	 * CurrentA : 3085
	 * CurrentB : 3599
	 * CurrentC : 4113
	 * DPStationID : 0000000018
	 * DayRunTime : 82250
	 * DeviceRT : invertering
	 * Frequency : 6169
	 * GateID : 1
	 * InverterID : 002010010006
	 * InverterUnitID : 000000000000
	 * PowFactorA : 4627
	 * PowFactorB : 5141
	 * PowFactorC : 5655
	 * ProtocolID : 001
	 * PutTime : 2018-01-21 18:16:50
	 * ReactivePower : 7197
	 * RunMode : stop
	 * TotalElectric : 1145390663
	 * VoltageAB : 1543
	 * VoltageBC : 2057
	 * VoltageCA : 0
	 * WorkMode : nolock
	 * id : dYQaxJS3e8NkxBnGhc7ABZYfYKCigJKh
	 * illegalNum : 2
	 * intervals : 4
	 * joinTime : 2018-01-21 18:16:50
	 * joinTimeStamp : 1516529810
	 * lastWorkstate : stop
	 * remark :
	 */
	@SerializedName("ActivePower")
	private float activePower;
	@SerializedName("ApparentPower")
	private float apparentPower;
	@SerializedName("ArrayCurrent")
	private float  arrayCurrent;
	@SerializedName("ArrayPower")
	private float  arrayPower;
	@SerializedName("ArrayVoltage")
	private float  arrayVoltage;
	@SerializedName("CO2Emission")
	private float  CO2Emission;
	@SerializedName("CompanyCode")
	private String companyCode;
	@SerializedName("ControlMode")
	private String controlMode;
	@SerializedName("CurrentA")
	private float  currentA;
	@SerializedName("CurrentB")
	private float  currentB;
	@SerializedName("CurrentC")
	private float  currentC;
	@SerializedName("DPStationID")
	private String stationId;
	@SerializedName("DayRunTime")
	private int dayRunTime;
	@SerializedName("DeviceRT")
	private String deviceRT;
	@SerializedName("Frequency")
	private float  frequency;
	@SerializedName("GateID")
	private String gateId;
	@SerializedName("InverterID")
	private String inverterId;
	@SerializedName("InverterUnitID")
	private String inverterUnitId;
	@SerializedName("PowFactorA")
	private float  powFactorA;
	@SerializedName("PowFactorB")
	private float  powFactorB;
	@SerializedName("PowFactorC")
	private float  powFactorC;
	@SerializedName("ProtocolID")
	private String protocolId;
	@SerializedName("PutTime")
	private String putTime;
	@SerializedName("ReactivePower")
	private float  reactivePower;
	@SerializedName("RunMode")
	private String runMode;
	@SerializedName("TotalElectric")
	private float  totalElectric;
	@SerializedName("VoltageAB")
	private float  voltageAB;
	@SerializedName("VoltageBC")
	private float  voltageBC;
	@SerializedName("VoltageCA")
	private float  voltageCA;
	@SerializedName("WorkMode")
	private String workMode;
	private String id;
	private int illegalNum;
	private int intervals;
	private String joinTime;
	private double  joinTimeStamp;
	@SerializedName("lastWorkstate")
	private String lastWorkState;
	private String remark;

	public String getRunMode() {
		if ("grid".equals(runMode)){
			return "并网";
		}else {
			return "离网";
		}
	}

	public void setRunMode(String runMode) {
		this.runMode = runMode;
	}

	public float getActivePower() {
		return activePower;
	}

	public void setActivePower(float activePower) {
		this.activePower = activePower;
	}

	public float getApparentPower() {
		return apparentPower;
	}

	public void setApparentPower(float apparentPower) {
		this.apparentPower = apparentPower;
	}

	public float getArrayCurrent() {
		return arrayCurrent;
	}

	public void setArrayCurrent(float arrayCurrent) {
		this.arrayCurrent = arrayCurrent;
	}

	public float getArrayPower() {
		return arrayPower;
	}

	public void setArrayPower(float arrayPower) {
		this.arrayPower = arrayPower;
	}

	public float getArrayVoltage() {
		return arrayVoltage;
	}

	public void setArrayVoltage(float arrayVoltage) {
		this.arrayVoltage = arrayVoltage;
	}

	public float getCO2Emission() {
		return CO2Emission;
	}

	public void setCO2Emission(float CO2Emission) {
		this.CO2Emission = CO2Emission;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getControlMode() {
		return controlMode;
	}

	public void setControlMode(String controlMode) {
		this.controlMode = controlMode;
	}

	public float getCurrentA() {
		return currentA;
	}

	public void setCurrentA(float currentA) {
		this.currentA = currentA;
	}

	public float getCurrentB() {
		return currentB;
	}

	public void setCurrentB(float currentB) {
		this.currentB = currentB;
	}

	public float getCurrentC() {
		return currentC;
	}

	public void setCurrentC(float currentC) {
		this.currentC = currentC;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getDeviceRT() {
		return deviceRT;
	}

	public void setDeviceRT(String deviceRT) {
		this.deviceRT = deviceRT;
	}

	public float getFrequency() {
		return frequency;
	}

	public void setFrequency(float frequency) {
		this.frequency = frequency;
	}

	public String getGateId() {
		return gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	public String getInverterId() {
		return inverterId;
	}

	public void setInverterId(String inverterId) {
		this.inverterId = inverterId;
	}

	public String getInverterUnitId() {
		return inverterUnitId;
	}

	public void setInverterUnitId(String inverterUnitId) {
		this.inverterUnitId = inverterUnitId;
	}

	public float getPowFactorA() {
		return powFactorA;
	}

	public void setPowFactorA(float powFactorA) {
		this.powFactorA = powFactorA;
	}

	public float getPowFactorB() {
		return powFactorB;
	}

	public void setPowFactorB(float powFactorB) {
		this.powFactorB = powFactorB;
	}

	public float getPowFactorC() {
		return powFactorC;
	}

	public void setPowFactorC(float powFactorC) {
		this.powFactorC = powFactorC;
	}

	public String getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
	}

	public String getPutTime() {
		return putTime;
	}

	public void setPutTime(String putTime) {
		this.putTime = putTime;
	}

	public float getReactivePower() {
		return reactivePower;
	}

	public void setReactivePower(float reactivePower) {
		this.reactivePower = reactivePower;
	}

	public float getVoltageAB() {
		return voltageAB;
	}

	public void setVoltageAB(float voltageAB) {
		this.voltageAB = voltageAB;
	}

	public float getVoltageBC() {
		return voltageBC;
	}

	public void setVoltageBC(float voltageBC) {
		this.voltageBC = voltageBC;
	}

	public float getVoltageCA() {
		return voltageCA;
	}

	public void setVoltageCA(float voltageCA) {
		this.voltageCA = voltageCA;
	}

	public String getWorkMode() {
		return workMode;
	}

	public void setWorkMode(String workMode) {
		this.workMode = workMode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIllegalNum() {
		return illegalNum;
	}

	public void setIllegalNum(int illegalNum) {
		this.illegalNum = illegalNum;
	}

	public int getIntervals() {
		return intervals;
	}

	public void setIntervals(int intervals) {
		this.intervals = intervals;
	}

	public String getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}

	public double getJoinTimeStamp() {
		return joinTimeStamp;
	}

	public void setJoinTimeStamp(double joinTimeStamp) {
		this.joinTimeStamp = joinTimeStamp;
	}

	public String getLastWorkState() {
		return lastWorkState;
	}

	public void setLastWorkState(String lastWorkState) {
		this.lastWorkState = lastWorkState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getDayRunTime() {
		return dayRunTime;
	}

	public void setDayRunTime(int dayRunTime) {
		this.dayRunTime = dayRunTime;
	}

	public float getTotalElectric() {
		return totalElectric;
	}

	public void setTotalElectric(float totalElectric) {
		this.totalElectric = totalElectric;
	}

		private String DcCurrentTwo;
		private String DcCurrentOne;
		private String DcVoltageThree;
		private float InverterYearElec;
		private float PV4InputA;
		private String TotalDCPower;
		private Object InputTotalPower;
		private String DcCurrentThree;
		private String DcVoltageTwo;
		private float PV2InputA;
		private String PV8InputA;
		private float VoltageB;
		private float VoltageC;
		private float VoltageA;
		private float PV5InputA;
		private float PV1InputA;
		private String TotalOperatingTime;
		private float InverterDayElec;
		private float PV1InputV;
		private float InverterMonthElec;
		private float PV5InputV;
		private float InverterTem;
		private String NegativeTurnGroundVoltage;
		private float PowerFactor;
		private String StartTime;
		private float PV2InputV;
		private String DcVoltageOne;
		private float InverterEfficiency;
		private float PV4InputV;
		private String PV14InputA;
		private String InverterState;
		private String ShutdownTime;
		private String BusVoltage;
		private float PV3InputA;
		private String PV11InputA;
		private float PV6InputV;
		private String PV10InputA;
		private String PV12InputA;
		private String PV9InputA;
		private String PV13InputA;
		private float PV6InputA;
		private float PV3InputV;
		private float PV7InputA;
        private String OutputPower;
        private String InputPower;
		public String getDcCurrentTwo() {
			return DcCurrentTwo;
		}
	    public String getInverterState() {
		      if ("0".equals(InverterState)){
		      	return "运行";
		      }else if("8000".equals(InverterState)){
		      	return "停机";
			  } else if ("1300".equals(InverterState)) {
		      	return "按键关机";
			  } else if ("1500".equals(InverterState)) {
		      	return "紧急停机";
			  } else if ("1400".equals(InverterState)) {
		      	return "待机";
			  } else if ("1600".equals(InverterState)) {
		      	return "启动中";
			  } else if ("9100".equals(InverterState)) {
		      	return "告警运行";
			  } else if ("8100".equals(InverterState)) {
		      	return "降额运行";
			  } else if ("8200".equals(InverterState)) {
		      	return "调度运行";
			  } else if ("5500".equals(InverterState)) {
		      	return "故障停机";
			  } else if ("2500".equals(InverterState)) {
				  return "通讯故障";
			  } else {
		      	return "未知异常";
			  }
	    }
	    public String getInverterState13() {
		if ("0".equals(InverterState)){
			return "停机";
		}else if("15".equals(InverterState)){
			return "休眠";
		} else if ("31".equals(InverterState)) {
			return "待机";
		} else if ("47".equals(InverterState)) {
			return "发电";
		} else if ("79".equals(InverterState)) {
			return "告警";
		} else {
			return "未知异常";
		}
	}
	    public String getInverterState10() {
		if ("0".equals(InverterState)){
			return "待机：初始化";
		}else if("1".equals(InverterState)){
			return "待机：绝缘阻抗检测";
		} else if ("2".equals(InverterState)) {
			return "待机：光照检测";
		} else if ("3".equals(InverterState)) {
			return "待机：电网检测";
		} else if ("256".equals(InverterState)) {
			return "启动";
		} else if ("512".equals(InverterState)) {
			return "并网";
		} else if ("513".equals(InverterState)) {
			return "并网：限功率";
		} else if ("768".equals(InverterState)) {
			return "关机：异常关机";
		} else if ("769".equals(InverterState)) {
			return "关机：指令关机";
		} else if ("1025".equals(InverterState)) {
			return "电网调度";
		} else if ("40960".equals(InverterState)) {
			return "待机：无光照";
		} else if ("1026".equals(InverterState)) {
			return "电网调度";
		} else if ("1280".equals(InverterState)) {
			return "点检就绪";
		} else if ("1281".equals(InverterState)) {
			return "点检中";
		} else if ("1536".equals(InverterState)) {
			return "巡检中";
		} else {
			return "未知异常";
		}
	}
	public String getInputPower() {
		return InputPower;
	}

	public void setInputPower(String inputPower) {
		InputPower = inputPower;
	}

	public String getOutputPower() {
		return OutputPower;
	}

	public void setOutputPower(String outputPower) {
		OutputPower = outputPower;
	}

	public void setDcCurrentTwo(String DcCurrentTwo) {
			this.DcCurrentTwo = DcCurrentTwo;
		}

		public String getDcCurrentOne() {
			return DcCurrentOne;
		}

		public void setDcCurrentOne(String DcCurrentOne) {
			this.DcCurrentOne = DcCurrentOne;
		}

		public String getDcVoltageThree() {
			return DcVoltageThree;
		}

		public void setDcVoltageThree(String DcVoltageThree) {
			this.DcVoltageThree = DcVoltageThree;
		}

		public float getInverterYearElec() {
			return InverterYearElec;
		}

		public void setInverterYearElec(float InverterYearElec) {
			this.InverterYearElec = InverterYearElec;
		}

		public float getPV4InputA() {
			return PV4InputA;
		}

		public void setPV4InputA(float PV4InputA) {
			this.PV4InputA = PV4InputA;
		}

		public String getTotalDCPower() {
			return TotalDCPower;
		}

		public void setTotalDCPower(String TotalDCPower) {
			this.TotalDCPower = TotalDCPower;
		}

		public Object getInputTotalPower() {
			return InputTotalPower;
		}

		public void setInputTotalPower(Object InputTotalPower) {
			this.InputTotalPower = InputTotalPower;
		}
		public String getDcCurrentThree() {
			return DcCurrentThree;
		}

		public void setDcCurrentThree(String DcCurrentThree) {
			this.DcCurrentThree = DcCurrentThree;
		}

		public String getDcVoltageTwo() {
			return DcVoltageTwo;
		}

		public void setDcVoltageTwo(String DcVoltageTwo) {
			this.DcVoltageTwo = DcVoltageTwo;
		}

		public float getPV2InputA() {
			return PV2InputA;
		}

		public void setPV2InputA(int PV2InputA) {
			this.PV2InputA = PV2InputA;
		}

		public String getPV8InputA() {
			return PV8InputA;
		}

		public void setPV8InputA(String PV8InputA) {
			this.PV8InputA = PV8InputA;
		}

		public float getVoltageB() {
			return VoltageB;
		}

		public void setVoltageB(float VoltageB) {
			this.VoltageB = VoltageB;
		}

		public float getVoltageC() {
			return VoltageC;
		}

		public void setVoltageC(float VoltageC) {
			this.VoltageC = VoltageC;
		}

		public float getVoltageA() {
			return VoltageA;
		}

		public void setVoltageA(float VoltageA) {
			this.VoltageA = VoltageA;
		}


		public float getPV5InputA() {
			return PV5InputA;
		}

		public void setPV5InputA(float PV5InputA) {
			this.PV5InputA = PV5InputA;
		}

		public float getPV1InputA() {
			return PV1InputA;
		}

		public void setPV1InputA(float PV1InputA) {
			this.PV1InputA = PV1InputA;
		}

		public String getTotalOperatingTime() {
			return TotalOperatingTime;
		}

		public void setTotalOperatingTime(String TotalOperatingTime) {
			this.TotalOperatingTime = TotalOperatingTime;
		}


		public float getInverterDayElec() {
			return InverterDayElec;
		}

		public void setInverterDayElec(float InverterDayElec) {
			this.InverterDayElec = InverterDayElec;
		}



		public float getPV1InputV() {
			return PV1InputV;
		}

		public void setPV1InputV(float PV1InputV) {
			this.PV1InputV = PV1InputV;
		}


		public float getInverterMonthElec() {
			return InverterMonthElec;
		}

		public void setInverterMonthElec(float InverterMonthElec) {
			this.InverterMonthElec = InverterMonthElec;
		}

		public float getPV5InputV() {
			return PV5InputV;
		}

		public void setPV5InputV(float PV5InputV) {
			this.PV5InputV = PV5InputV;
		}

		public float getInverterTem() {
			return InverterTem;
		}

		public void setInverterTem(float InverterTem) {
			this.InverterTem = InverterTem;
		}



		public String getNegativeTurnGroundVoltage() {
			return NegativeTurnGroundVoltage;
		}

		public void setNegativeTurnGroundVoltage(String NegativeTurnGroundVoltage) {
			this.NegativeTurnGroundVoltage = NegativeTurnGroundVoltage;
		}

		public float getPowerFactor() {
			return PowerFactor;
		}

		public void setPowerFactor(float PowerFactor) {
			this.PowerFactor = PowerFactor;
		}



		public String getStartTime() {
			return StartTime;
		}

		public void setStartTime(String StartTime) {
			this.StartTime = StartTime;
		}


		public float getPV2InputV() {
			return PV2InputV;
		}

		public void setPV2InputV(float PV2InputV) {
			this.PV2InputV = PV2InputV;
		}




		public String getDcVoltageOne() {
			return DcVoltageOne;
		}

		public void setDcVoltageOne(String DcVoltageOne) {
			this.DcVoltageOne = DcVoltageOne;
		}

		public float getInverterEfficiency() {
			return InverterEfficiency;
		}

		public void setInverterEfficiency(float InverterEfficiency) {
			this.InverterEfficiency = InverterEfficiency;
		}


		public float getPV4InputV() {
			return PV4InputV;
		}

		public void setPV4InputV(float PV4InputV) {
			this.PV4InputV = PV4InputV;
		}

		public String getPV14InputA() {
			return PV14InputA;
		}

		public void setPV14InputA(String PV14InputA) {
			this.PV14InputA = PV14InputA;
		}

		public void setInverterState(String InverterState) {
			this.InverterState = InverterState;
		}

		public String getShutdownTime() {
			return ShutdownTime;
		}

		public void setShutdownTime(String ShutdownTime) {
			this.ShutdownTime = ShutdownTime;
		}

		public String getBusVoltage() {
			return BusVoltage;
		}

		public void setBusVoltage(String BusVoltage) {
			this.BusVoltage = BusVoltage;
		}

		public float getPV3InputA() {
			return PV3InputA;
		}

		public void setPV3InputA(int PV3InputA) {
			this.PV3InputA = PV3InputA;
		}


		public String getPV11InputA() {
			return PV11InputA;
		}
		public void setPV11InputA(String PV11InputA) {
			this.PV11InputA = PV11InputA;
		}
		public float getPV6InputV() {
			return PV6InputV;
		}

		public void setPV6InputV(float PV6InputV) {
			this.PV6InputV = PV6InputV;
		}

		public String getPV10InputA() {
			return PV10InputA;
		}

		public void setPV10InputA(String PV10InputA) {
			this.PV10InputA = PV10InputA;
		}


		public String getPV12InputA() {
			return PV12InputA;
		}

		public void setPV12InputA(String PV12InputA) {
			this.PV12InputA = PV12InputA;
		}

		public String getPV9InputA() {
			return PV9InputA;
		}

		public void setPV9InputA(String PV9InputA) {
			this.PV9InputA = PV9InputA;
		}

		public String getPV13InputA() {
			return PV13InputA;
		}

		public void setPV13InputA(String PV13InputA) {
			this.PV13InputA = PV13InputA;
		}

		public float getPV6InputA() {
			return PV6InputA;
		}

		public void setPV6InputA(int PV6InputA) {
			this.PV6InputA = PV6InputA;
		}

		public float getPV3InputV() {
			return PV3InputV;
		}

		public void setPV3InputV(float PV3InputV) {
			this.PV3InputV = PV3InputV;
		}

		public float getPV7InputA() {
			return PV7InputA;
		}

		public void setPV7InputA(float PV7InputA) {
			this.PV7InputA = PV7InputA;
		}
}
