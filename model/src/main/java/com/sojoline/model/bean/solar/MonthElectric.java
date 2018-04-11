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

public class MonthElectric implements Comparable<MonthElectric>{

	/**
	 * MonthDayElectric : 530.88
	 * days : 15
	 * months : 2018-01
	 */

	private float MonthDayElectric;
	private int days;
	private String months;

	public float getMonthDayElectric() {
		return MonthDayElectric;
	}

	public void setMonthDayElectric(float MonthDayElectric) {
		this.MonthDayElectric = MonthDayElectric;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	@Override
	public int compareTo(@NonNull MonthElectric o) {
		return this.getDays() - o.getDays();
	}
}
