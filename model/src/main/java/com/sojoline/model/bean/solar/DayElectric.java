package com.sojoline.model.bean.solar;

import android.support.annotation.NonNull;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class DayElectric implements Comparable<DayElectric>{

	/**
	 * DayHourElectric : 100.01
	 * days : 2018-01-21
	 * hours : 18
	 */

	private float DayHourElectric;
	private String days;
	private int hours;

	public float getDayHourElectric() {
		return DayHourElectric;
	}

	public void setDayHourElectric(float DayHourElectric) {
		this.DayHourElectric = DayHourElectric;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	@Override
	public int compareTo(@NonNull DayElectric o) {
		return this.getHours() - o.getHours();
	}
}
