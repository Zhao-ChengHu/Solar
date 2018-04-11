package com.sojoline.solar.event;

import com.sojoline.model.bean.solar.SolarStation;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarViewEvent {
	private boolean isShow;
	private SolarStation station;

	public SolarViewEvent(boolean isShow) {
		this.isShow = isShow;
	}

	public SolarViewEvent(boolean isShow, SolarStation station) {
		this.isShow = isShow;
		this.station = station;
	}

	public boolean isShow() {
		return isShow;
	}

	public SolarStation getStation() {
		return station;
	}
}
