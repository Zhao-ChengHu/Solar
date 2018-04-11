package com.sojoline.model.bean.solar;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class WarningBean {
	private String batch;
	private String warningInfo;
	private String warningDate;
	private boolean repair;
	private String repairDate;

	public WarningBean() {
	}

	public WarningBean(String batch, String warningInfo, String warningDate, boolean repair, String repairDate) {
		this.batch = batch;
		this.warningInfo = warningInfo;
		this.warningDate = warningDate;
		this.repair = repair;
		this.repairDate = repairDate;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getWarningInfo() {
		return warningInfo;
	}

	public void setWarningInfo(String warningInfo) {
		this.warningInfo = warningInfo;
	}

	public String getWarningDate() {
		return warningDate;
	}

	public void setWarningDate(String warningDate) {
		this.warningDate = warningDate;
	}

	public boolean isRepair() {
		return repair;
	}

	public void setRepair(boolean repair) {
		this.repair = repair;
	}

	public String getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(String repairDate) {
		this.repairDate = repairDate;
	}
}
