package com.sojoline.model.response;

import com.google.gson.annotations.SerializedName;
import com.sojoline.model.bean.solar.SolarStation;

import java.util.List;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarStationResponse extends BaseResponse {
	@SerializedName("content")
	private List<SolarStation> list;

	public List<SolarStation> getList() {
		return list;
	}

	public void setList(List<SolarStation> list) {
		this.list = list;
	}
}
