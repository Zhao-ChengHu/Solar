package com.sojoline.model.response;

import com.google.gson.annotations.SerializedName;
import com.sojoline.model.bean.solar.MeterData;

/**
 * <pre>
 *     @author : zhaochenghu
 *     date   : 2018/08/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MeterDataResponse extends BaseResponse{
	@SerializedName("content")
	private MeterData data;

	public MeterData getData() {
		return data;
	}

	public void setData(MeterData data) {
		this.data = data;
	}
}
