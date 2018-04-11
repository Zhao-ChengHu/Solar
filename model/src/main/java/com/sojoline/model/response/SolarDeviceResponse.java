package com.sojoline.model.response;

import com.google.gson.annotations.SerializedName;
import com.sojoline.model.bean.solar.SolarDevice;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarDeviceResponse extends BaseResponse {
	@SerializedName("content")
	private SolarDevice data;

	public SolarDevice getData() {
		return data;
	}

	public void setData(SolarDevice data) {
		this.data = data;
	}
}
