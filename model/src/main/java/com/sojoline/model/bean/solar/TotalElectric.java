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

public class TotalElectric implements Comparable<TotalElectric>{

	/**
	 * YearTotalElectric : 530.88
	 * years : 2018
	 */

	private float YearTotalElectric;
	private String years;

	public float getYearTotalElectric() {
		return YearTotalElectric;
	}

	public void setYearTotalElectric(float YearTotalElectric) {
		this.YearTotalElectric = YearTotalElectric;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	@Override
	public int compareTo(@NonNull TotalElectric o) {
		int i1 = Integer.parseInt(this.getYears());
		int i2 = Integer.parseInt(o.getYears());
		return i1 - i2;
	}
}
