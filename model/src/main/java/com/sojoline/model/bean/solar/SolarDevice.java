package com.sojoline.model.bean.solar;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * <pre>
 *     @author : zhaochenghu
 *     date   : 2018/08/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarDevice {

	/**
	 * CombinerList : []
	 * InverterList : []
	 * TransformerList : []
	 * combinerAlarmNum : 0
	 * combinerOffLineNum : 0
	 * combinerRunningNum : 0
	 * inverterAlarmNum : 0
	 * inverterOffLineNum : 0
	 * inverterRunningNum : 0
	 * totalCombinmer : 0
	 * totalInverters : 0
	 * totalTransformer : 0
	 * transformerAlarmNum : 0
	 * transformerFaultNum : 0
	 * transformerRunningNum : 0
	 * 'totalEnvDetector': total_result_env[0],
	 'envDetectorLineNum': stop_num_env,
	 'envDetectorRunningNum': run_num_env,
	 'envDetectorAlarmNum': alarm_num_env,
	 'envDetectorList': dict_items_env,

	 'totalMeter': total_result_env[0],
	 'meterLineNum': stop_num_env,
	 'meterRunningNum': run_num_env,
	 'meterAlarmNum': alarm_num_env,
	 'meterList': dict_items_env,

	 'totalPowerMeter': total_result_env[0],
	 'powerMeterLineNum': stop_num_env,
	 'powerMeterRunningNum': run_num_env,
	 'powerMeterAlarmNum': alarm_num_env,
	 'powerMeterList': dict_items_env,
	 */

	private int combinerAlarmNum;
	private int combinerOffLineNum;
	private int combinerRunningNum;
	private int inverterAlarmNum;
	private int inverterOffLineNum;
	private int inverterRunningNum;
	@SerializedName("totalCombinmer")
	private int totalCombiner;
	private int totalInverters;
	private int totalTransformer;
	private int totalEnvDetector ;
	private int totalPowerMeter;
	private int totalMeter;
	private int transformerAlarmNum;
	private int transformerFaultNum;
	private int transformerRunningNum;
	@SerializedName("CombinerList")
	private List<CombinerInfo> combinerList;
	@SerializedName("InverterList")
	private List<InverterInfo> inverterList;
	@SerializedName("TransformerList")
	private List<TransformerInfo> transformerList;

	private int envDetectorAlarmNum;
	private int envDetectorLineNum;
	private int envDetectorRunningNum;
	@SerializedName("envDetectorList")
	private List<MonitorInfo> monitorList;
	//多功能电力仪
	private int powerMeterAlarmNum;
	private int powerMeterLineNum;
	private int powerMeterRunningNum;
	@SerializedName("powerMeterList")
	private List<PowermeterInfo> powerMeterList;
   //电表
	private int meterAlarmNum;
	private int meterLineNum;
	private int meterRunningNum;
	@SerializedName("meterList")
	private List<MeterInfo> meterList;

	public int getTotalMeter() {
		return totalMeter;
	}

	public void setTotalMeter(int totalMeter) {
		this.totalMeter = totalMeter;
	}

	public int getMeterAlarmNum() {
		return meterAlarmNum;
	}

	public void setMeterAlarmNum(int meterAlarmNum) {
		this.meterAlarmNum = meterAlarmNum;
	}

	public int getMeterLineNum() {
		return meterLineNum;
	}

	public void setMeterLineNum(int meterLineNum) {
		this.meterLineNum = meterLineNum;
	}

	public int getMeterRunningNum() {
		return meterRunningNum;
	}

	public void setMeterRunningNum(int meterRunningNum) {
		this.meterRunningNum = meterRunningNum;
	}

	public List<MeterInfo> getMeterList() {
		return meterList;
	}

	public void setMeterList(List<MeterInfo> meterList) {
		this.meterList = meterList;
	}

	public int getTotalPowerMeter() {
		return totalPowerMeter;
	}

	public void setTotalPowerMeter(int totalPowerMeter) {
		this.totalPowerMeter = totalPowerMeter;
	}

	public int getPowerMeterAlarmNum() {
		return powerMeterAlarmNum;
	}

	public void setPowerMeterAlarmNum(int powerMeterAlarmNum) {
		this.powerMeterAlarmNum = powerMeterAlarmNum;
	}

	public int getPowerMeterLineNum() {
		return powerMeterLineNum;
	}

	public void setPowerMeterLineNum(int powerMeterLineNum) {
		this.powerMeterLineNum = powerMeterLineNum;
	}

	public int getPowerMeterRunningNum() {
		return powerMeterRunningNum;
	}

	public void setPowerMeterRunningNum(int powerMeterRunningNum) {
		this.powerMeterRunningNum = powerMeterRunningNum;
	}

	public List<PowermeterInfo> getPowerMeterList() {
		return powerMeterList;
	}

	public void setPowerMeterList(List<PowermeterInfo> powerMeterList) {
		this.powerMeterList = powerMeterList;
	}

	public int getMonitorAlarmNum() {
		return envDetectorAlarmNum;
	}

	public void setMonitorAlarmNum(int envDetectorAlarmNum) {
		this.envDetectorAlarmNum = envDetectorAlarmNum;
	}

	public int getMonitorOffLineNum() {
		return envDetectorLineNum;
	}

	public void setMonitorOffLineNum(int envDetectorLineNum) {
		this.envDetectorLineNum = envDetectorLineNum;
	}

	public int getMonitorRunningNum() {
		return envDetectorRunningNum;
	}

	public void setMonitorRunningNum(int envDetectorRunningNum) {
		this.envDetectorRunningNum = envDetectorRunningNum;
	}

	public int getMonitorInverters() {
		return totalEnvDetector;
	}

	public void setMonitorInverters(int totalEnvDetector) {
		this.totalEnvDetector = totalEnvDetector;
	}
	public int getCombinerAlarmNum() {
		return combinerAlarmNum;
	}

	public void setCombinerAlarmNum(int combinerAlarmNum) {
		this.combinerAlarmNum = combinerAlarmNum;
	}

	public int getCombinerOffLineNum() {
		return combinerOffLineNum;
	}

	public void setCombinerOffLineNum(int combinerOffLineNum) {
		this.combinerOffLineNum = combinerOffLineNum;
	}

	public int getCombinerRunningNum() {
		return combinerRunningNum;
	}

	public void setCombinerRunningNum(int combinerRunningNum) {
		this.combinerRunningNum = combinerRunningNum;
	}

	public int getInverterAlarmNum() {
		return inverterAlarmNum;
	}

	public void setInverterAlarmNum(int inverterAlarmNum) {
		this.inverterAlarmNum = inverterAlarmNum;
	}

	public int getInverterOffLineNum() {
		return inverterOffLineNum;
	}

	public void setInverterOffLineNum(int inverterOffLineNum) {
		this.inverterOffLineNum = inverterOffLineNum;
	}

	public int getInverterRunningNum() {
		return inverterRunningNum;
	}

	public void setInverterRunningNum(int inverterRunningNum) {
		this.inverterRunningNum = inverterRunningNum;
	}



	public int getTotalInverters() {
		return totalInverters;
	}

	public void setTotalInverters(int totalInverters) {
		this.totalInverters = totalInverters;
	}

	public int getTotalTransformer() {
		return totalTransformer;
	}

	public void setTotalTransformer(int totalTransformer) {
		this.totalTransformer = totalTransformer;
	}

	public int getTransformerAlarmNum() {
		return transformerAlarmNum;
	}

	public void setTransformerAlarmNum(int transformerAlarmNum) {
		this.transformerAlarmNum = transformerAlarmNum;
	}

	public int getTransformerFaultNum() {
		return transformerFaultNum;
	}

	public void setTransformerFaultNum(int transformerFaultNum) {
		this.transformerFaultNum = transformerFaultNum;
	}

	public int getTransformerRunningNum() {
		return transformerRunningNum;
	}

	public void setTransformerRunningNum(int transformerRunningNum) {
		this.transformerRunningNum = transformerRunningNum;
	}

	public int getTotalCombiner() {
		return totalCombiner;
	}

	public void setTotalCombiner(int totalCombiner) {
		this.totalCombiner = totalCombiner;
	}

	public List<CombinerInfo> getCombinerList() {
		return combinerList;
	}

	public void setCombinerList(List<CombinerInfo> combinerList) {
		this.combinerList = combinerList;
	}

	public List<InverterInfo> getInverterList() {
		return inverterList;
	}

	public void setInverterList(List<InverterInfo> inverterList) {
		this.inverterList = inverterList;
	}

	public List<TransformerInfo> getTransformerList() {
		return transformerList;
	}

	public void setTransformerList(List<TransformerInfo> transformerList) {
		this.transformerList = transformerList;
	}

	public List<MonitorInfo> getMonitorList() {
		return monitorList;
	}

	public void setMonitorList(List<MonitorInfo> monitorList) {
		this.monitorList = monitorList;
	}

}
