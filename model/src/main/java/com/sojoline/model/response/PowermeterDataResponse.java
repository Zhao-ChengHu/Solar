package com.sojoline.model.response;

import com.google.gson.annotations.SerializedName;
import com.sojoline.model.bean.solar.PowermeterData;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class PowermeterDataResponse extends BaseResponse{
	@SerializedName("content")
	private PowermeterData data;

	public PowermeterData getData() {
		return data;
	}

	public void setData(PowermeterData data) {
		this.data = data;
	}
}
