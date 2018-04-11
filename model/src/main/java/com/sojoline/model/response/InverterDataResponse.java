package com.sojoline.model.response;

import com.google.gson.annotations.SerializedName;
import com.sojoline.model.bean.solar.InverterData;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class InverterDataResponse extends BaseResponse {
	@SerializedName("content")
	private InverterData data;

	public InverterData getData() {
		return data;
	}

	public void setData(InverterData data) {
		this.data = data;
	}
}
