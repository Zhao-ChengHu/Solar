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

public class YearElectric implements Comparable<YearElectric>{

	/**
	 * YearMonthElectric : 530.88
	 * months : 1
	 * years : 2018
	 */

	private float YearMonthElectric;
	private int months;
	private String years;

	public float getYearMonthElectric() {
		return YearMonthElectric;
	}

	public void setYearMonthElectric(float YearMonthElectric) {
		this.YearMonthElectric = YearMonthElectric;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	@Override
	public int compareTo(@NonNull YearElectric o) {
		return this.getMonths() - o.getMonths();
	}
}
