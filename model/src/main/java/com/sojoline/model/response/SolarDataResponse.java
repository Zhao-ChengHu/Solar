package com.sojoline.model.response;

import com.google.gson.annotations.SerializedName;
import com.sojoline.model.bean.solar.SolarData;

import java.util.List;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarDataResponse extends BaseResponse {
	@SerializedName("content")
	private List<SolarData> list;

	public List<SolarData> getList() {
		return list;
	}

	public void setList(List<SolarData> list) {
		this.list = list;
	}
}
