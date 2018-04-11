package com.sojoline.model.db;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/02/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Entity
public class DateElectric implements Comparable<DateElectric>{

	@Expose(deserialize = false)
	private Long electricId;

	@NotNull
	private float electric;

	private String date;

	private Integer num;

	@Generated(hash = 609794326)
	public DateElectric(Long electricId, float electric, String date, Integer num) {
		this.electricId = electricId;
		this.electric = electric;
		this.date = date;
		this.num = num;
	}

	@Generated(hash = 1054593052)
	public DateElectric() {
	}


	public float getElectric() {
		return electric;
	}

	public void setElectric(float electric) {
		this.electric = electric;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public int compareTo(@NonNull DateElectric o) {
		return this.getNum() - o.getNum();
	}

	public Long getElectricId() {
		return this.electricId;
	}

	public void setElectricId(Long electricId) {
		this.electricId = electricId;
	}

}
