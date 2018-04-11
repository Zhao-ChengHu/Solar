package com.sojoline.model.bean.solar;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/10
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
	private int transformerAlarmNum;
	private int transformerFaultNum;
	private int transformerRunningNum;
	@SerializedName("CombinerList")
	private List<CombinerInfo> combinerList;
	@SerializedName("InverterList")
	private List<InverterInfo> inverterList;
	@SerializedName("TransformerList")
	private List<TransformerInfo> transformerList;

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
}
