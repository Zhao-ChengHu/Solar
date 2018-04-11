package com.sojoline.model.response;

import com.sojoline.model.db.DateElectric;

import java.util.List;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/02/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarElectricResponse extends BaseResponse {
	private List<DateElectric> list;

	public List<DateElectric> getList() {
		return list;
	}

	public void setList(List<DateElectric> list) {
		this.list = list;
	}
}
